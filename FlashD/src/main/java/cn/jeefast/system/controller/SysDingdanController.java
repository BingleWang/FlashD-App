package cn.jeefast.system.controller;


import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysDingdan;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.service.SysDingdanService;
import cn.jeefast.system.service.SysUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  order management front controller
 * </p>
 *
 */
@RestController
@RequestMapping("/sysDingdan")
public class SysDingdanController extends BaseController {

    @Autowired
    private SysDingdanService sysDingdanService;

    /**
     * order managementlist
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dingdan:list")
    public R list(@RequestParam Map<String, Object> params) {
        System.out.println("paramsparamsparams"+params);
        if(getUser().getUsername() != "admin"){
            params.put("sssj",getUser().getUsername());
        }
        //querylistdata
        Query query = new Query(params);
        Page<SysDingdan> pageUtil = new Page<SysDingdan>(query.getPage(), query.getLimit());
        Page<SysDingdan> page = sysDingdanService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * list
     */
    @RequestMapping("/zdcxlist")
    @RequiresPermissions("sys:dingdan:zdcxlist")
    public R zdcxlist(@RequestParam Map<String, Object> params) {
        if(getUser().getUsername() != "admin"){
            params.put("sssj",getUser().getUsername());
        }
        System.out.println("paramsparamsparams"+params);
        //querylistdata
        Query query = new Query(params);
        Page<SysDingdan> pageUtil = new Page<SysDingdan>(query.getPage(), query.getLimit());
        Page<SysDingdan> page = sysDingdanService.queryZdcxPageList(pageUtil, query);
        System.out.println("pagepagepage"+page.getRecords());
        return R.ok().put("page", page);
    }



    /**
     * order managementlist
     */
    @RequestMapping("/yyelist")
    public R yyelist(@RequestParam Map<String, Object> params) {

        if(getUser().getUsername() != "admin"){
            params.put("sssj",getUser().getUsername());
        }
        if(params.get("kssj") != null && !params.get("kssj").equals("")){
            params.put("kssj",params.get("kssj").toString().substring(0,10));
        }
        System.out.println("paramsparamsparamsparamsparams"+params);
        //querylistdata
        Query query = new Query(params);
        Page<Map<String,Object>> pageUtil = new Page<Map<String,Object>>(query.getPage(), query.getLimit());
        Page<Map<String,Object>> page = sysDingdanService.queryPageListByYye(pageUtil, query);
        return R.ok().put("page", page);
    }

    @Autowired
    private SysUserService sysUserService;

    /**
     * order managementinfo
     */
    @RequestMapping("/info/{dingdanId}")
    @RequiresPermissions("sys:dingdan:info")
    public R info(@PathVariable("dingdanId") String dingdanId) {
        SysDingdan dingdan = sysDingdanService.selectById(dingdanId);
        SysUser sysUser = sysUserService.selectById(getUserId());
        Boolean ysgjb = false;
        String ysgjbinfo = "";
        if(sysUser.getJtysgj() == null){
            ysgjb = true;
            ysgjbinfo = "means of transportation";
        }else{
            if(sysUser.getJtysgj().equals("1")){
                if(dingdan.getZl() >15.00){
                    ysgjb = true;
                    ysgjbinfo = "No method of transportation for overweight vehicles";
                }
            }else if(sysUser.getJtysgj().equals("2")){
                if(dingdan.getZl() >30.00){
                    ysgjb = true;
                    ysgjbinfo = "No method of transportation for overweight vehicles";
                }
            }else if(sysUser.getJtysgj().equals("3")){
                if(dingdan.getZl() >30.00){
                    ysgjb = true;
                    ysgjbinfo = "No method of transportation for overweight vehicles";
                }
            }else if(sysUser.getJtysgj().equals("4")){
                if(dingdan.getZl() >1000.00){
                    ysgjb = true;
                }
            }else if(sysUser.getJtysgj().equals("4")){
                if(dingdan.getZl() >100001000.00){
                    ysgjb = true;
                    ysgjbinfo = "No method of transportation for overweight vehicles";
                }
            }

        }

        return R.ok().put("dingdan", dingdan).put("ysgjb",ysgjb).put("ysgjbinfo",ysgjbinfo);
    }

    /**
     * order managementinfo
     */
    @RequestMapping("/getDingdans")
    public R getDingdans() {
        List<SysDingdan> dingdans = sysDingdanService.selectList(new EntityWrapper<>());
        return R.ok().put("dingdans",dingdans);
    }


    /**
     * order managementinfo
     */
    @RequestMapping("/infoprent/{dingdanId}")
    @RequiresPermissions("sys:dingdan:infoprent")
    public R infoprent(@PathVariable("dingdanId") String dingdanId) {
        SysDingdan dingdan = sysDingdanService.selectById(dingdanId);
        List<SysDingdan> sysDingdanList = new ArrayList<>();

        return R.ok().put("dingdan", dingdan).put("allFiles",sysDingdanList);
    }

    /**
     * saveorder management
     */
    @Log("saveorder management")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dingdan:save")
    public R save(@RequestBody SysDingdan dingdan) {
        ValidatorUtils.validateEntity(dingdan);
        dingdan.setDdfzr(getUser().getUsername());
        dingdan.setSfqrsh("2");
        dingdan.setUpdatetime(new Date());
        sysDingdanService.insert(dingdan);
        return R.ok();
    }

    /**
     * updateorder management
     */
    @Log("updateorder management")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dingdan:update")
    public R update(@RequestBody SysDingdan dingdan) {
        ValidatorUtils.validateEntity(dingdan);
        dingdan.setDdfzr(getUser().getUsername());
        dingdan.setSfqrsh("2");
        dingdan.setUpdatetime(new Date());
        sysDingdanService.updateById(dingdan);

        return R.ok();
    }


    /**
     * deleteorder management
     */
    @Log("deleteorder management")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dingdan:delete")
    public R delete(@RequestBody String[] dingdanIds) {
        sysDingdanService.deleteBatch(dingdanIds);
        return R.ok();
    }
	
}
