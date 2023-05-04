package cn.jeefast.system.controller;


import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.*;
import cn.jeefast.system.service.SysDingdanService;
import cn.jeefast.system.service.SysYszydService;
import cn.jeefast.system.service.SysUserTokenService;
import cn.jeefast.system.service.TMaterialFileService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import cn.jeefast.common.base.BaseController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Transfer point front controller
 * </p>
 *
 */
@RestController
@RequestMapping("/sysYszyd")
public class SysYszydController extends BaseController {

    @Autowired
    private SysYszydService sysYszydService;

    @Autowired
    private SysDingdanService sysDingdanService;

    /**
     * info table
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:yszyd:list")
    public R list(@RequestParam Map<String, Object> params) throws UnknownHostException {
        //querylistdata
        Query query = new Query(params);
        Page<SysYszyd> pageUtil = new Page<SysYszyd>(query.getPage(), query.getLimit());
        Page<SysYszyd> page = sysYszydService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * info tableinfo
     */
    @RequestMapping("/info/{yszydId}")
    @RequiresPermissions("sys:yszyd:info")
    public R info(@PathVariable("yszydId") String yszydId) {
        SysYszyd yszyd = sysYszydService.selectById(yszydId);
        return R.ok().put("yszyd", yszyd);
    }

    /**
     * saveinfo table
     */
    @Log("saveinfo table")
    @RequestMapping("/save")
    @RequiresPermissions("sys:yszyd:save")
    public R save(@RequestBody SysYszyd yszyd) {
        ValidatorUtils.validateEntity(yszyd);
        SysDingdan sysDingdan = sysDingdanService.selectById(yszyd.getDingdanid());
        sysDingdan.setWlzt(yszyd.getDeptid());
        yszyd.setCreatetime(new Date());
        yszyd.setCreateuser(getUser().getUsername());
        yszyd.setUpdatetime(new Date());
        yszyd.setUpdateuser(getUser().getUsername());
        sysYszydService.insert(yszyd);
        return R.ok();
    }

    /**
     * updateinfo table
     */
    @Log("updateinfo table")
    @RequestMapping("/update")
    @RequiresPermissions("sys:yszyd:update")
    public R update(@RequestBody SysYszyd yszyd) {
        ValidatorUtils.validateEntity(yszyd);
        yszyd.setUpdatetime(new Date());
        yszyd.setUpdateuser(getUser().getUsername());
        sysYszydService.updateById(yszyd);
        return R.ok();
    }

    /**
     * deleteinfo table
     */
    @Log("deleteinfo table")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:yszyd:delete")
    public R delete(@RequestBody String[] yszydIds) {
        sysYszydService.deleteBatch(yszydIds);
        return R.ok();
    }
}
