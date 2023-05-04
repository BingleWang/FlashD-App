package cn.jeefast.system.controller;

import cn.jeefast.system.entity.*;
import cn.jeefast.system.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;

import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.Assert;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.common.validator.group.AddGroup;
import cn.jeefast.common.validator.group.UpdateGroup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * system user
 * 
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysUserTokenService sysUserTokenService;

	@Autowired
	private TMaterialFileService tMaterialFileService;

	@Autowired
	private SysRoleService sysRoleService;

	@Value("${server.port}")
	private String serverport;

	@Value("${server.context-path}")
	private String servercontextpath;


	
	/**
	 * all userlist
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params) throws UnknownHostException {
		//querylistdata
		SysUser dluser = getUser();
//		if(dluser.getUsername().equals("admin")){
//
//		}else{
//
//		}



		Query query = new Query(params);

		Page<SysUser> pageUtil = new Page<SysUser>(query.getPage(), query.getLimit());
		Page<SysUser> page = sysUserService.queryPageList(pageUtil,query);
		if(page.getRecords().size()>0){
			for(int i=0;i<page.getRecords().size();i++){
				SysUser sysUser = page.getRecords().get(i);
				List<SysUserRole> sysUserRoles = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id",sysUser.getUserId()));
				String roalArraystr = "";
				if(sysUserRoles != null && sysUserRoles.size()>0){
					for(int j=0;j<sysUserRoles.size();j++){
						SysRole sysRole = sysRoleService.selectById(sysUserRoles.get(j).getRoleId());
						if(sysRole != null){
							roalArraystr = sysRole.getRoleName()+","+roalArraystr;
						}
					}
				}
				sysUser.setRoalArraystr(roalArraystr);// viewroleinfo

				/**
				 */
				List<TMaterialFile> tMaterialFileList = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",sysUser.getUserId()));
				SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("user_id", getUserId()));
				InetAddress address = InetAddress.getLocalHost();
				sysUser.setPhotopath(tMaterialFileList != null && tMaterialFileList.size()>0?"http://"+address.getHostAddress() +":"+serverport+"/"+servercontextpath+"/upload/"+tMaterialFileList.get(0).getSfilename()+"?token="+sysUserToken.getToken():"img/usermm.jpg");

			}
		}
		return R.ok().put("page", page);
	}
	
	/**
	 * getlogin userinfo
	 */
	@RequestMapping("/info")
	public R info() throws UnknownHostException {
		SysUser user = getUser();
		SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("user_id", user.getUserId()));
		List<TMaterialFile> tMaterialFileList = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",user.getUserId()));
		InetAddress address = InetAddress.getLocalHost();
		return R.ok().put("user", getUser()).put("lj",tMaterialFileList != null && tMaterialFileList.size()>0?"http://"+address.getHostAddress() +":"+serverport+"/"+servercontextpath+"/upload/"+tMaterialFileList.get(0).getSfilename()+"?token="+sysUserToken.getToken():"img/usermm.jpg");
	}
	
	/**
	 * updatelogin userpassword
	 */
	@Log("updatepassword")
	@RequestMapping("/password")
	public R password(String password, String newPassword){
		Assert.isBlank(newPassword, "new Password cannot be empty");

		password = new Sha256Hash(password, getUser().getSalt()).toHex();
		newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();
		
		SysUser user = new SysUser();
		user.setUserId(getUserId());
		user.setPassword(newPassword);
		//new password
		boolean bFlag = sysUserService.updateById(user);
		if(!bFlag){
			return R.error("old password error");
		}
		
		return R.ok();
	}
	
	/**
	 *  userinfo
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUser user = sysUserService.selectById(userId);
		
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",userId));
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(!tMaterialFiles.isEmpty()){
			for(TMaterialFile tMaterialFile:tMaterialFiles){
				Map<String,Object> map =new HashMap<>();
				map.put("id",tMaterialFile.getId());
				map.put("filePath",tMaterialFile.getSfilename());
				map.put("fileName",tMaterialFile.getSaccessoryname());
				mapList.add(map);
			}
		}
		JSONArray json = (JSONArray) JSONArray.toJSON(mapList);
		user.setFiles(json);
		return R.ok().put("user", user);
	}
	
	/**
	 * save user
	 */
	@Log("save user")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUser user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		user.setCreateTime(new Date());
		user.setCreateUserId(getUserId());
		sysUserService.save(user);
		tMaterialFileService.setTMaterialFilePrintId(user.getFiles(),user.getUserId()+"");
		return R.ok();
	}


	
	/**
	 * update user
	 */
	@Log("update user")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUser user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		tMaterialFileService.setTMaterialFilePrintId(user.getFiles(),user.getUserId()+"");
		return R.ok();
	}
	
	/**
	 * delete user
	 */
	@Log("delete user")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("System administrator cannot delete");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("The current user cannot be deleted");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
