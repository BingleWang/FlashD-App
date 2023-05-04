package cn.jeefast.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;

import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Constant;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysRole;
import cn.jeefast.system.service.SysRoleDeptService;
import cn.jeefast.system.service.SysRoleMenuService;
import cn.jeefast.system.service.SysRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Roles
 * 
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDeptService sysRoleDeptService;
	
	/**
	 * Role List
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		
		//querylistdata
		Query query = new Query(params);
		Page<SysRole> pageUtil = new Page<SysRole>(query.getPage(), query.getLimit());
		
		Page<SysRole> page = sysRoleService.queryPageList(pageUtil,query);
		
		return R.ok().put("page", page);
	}
	
	/**
	 * rolelist
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		//If you are not a super administrator, you will only query the list of roles you own
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<SysRole> list = sysRoleService.queryList(map);
				
		return R.ok().put("list", list);
	}
	
	/**
	 * roleinfo
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRole role = sysRoleService.selectById(roleId);
		
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(roleId);
		role.setDeptIdList(deptIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * saverole
	 */
	@Log("saverole")
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRole role){
		ValidatorUtils.validateEntity(role);
		
		sysRoleService.save(role);
		
		return R.ok();
	}
	
	/**
	 * updaterole
	 */
	@Log("updaterole")
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRole role){
		ValidatorUtils.validateEntity(role);
		
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	/**
	 * deleterole
	 */
	@Log("deleterole")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
}
