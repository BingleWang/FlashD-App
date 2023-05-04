package cn.jeefast.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.system.entity.SysLog;
import cn.jeefast.system.service.SysLogService;

import java.util.Map;


/**
 * system log
 * 
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * list
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params){
		//querylistdata
		Query query = new Query(params);
		Page<SysLog> pageUtil = new Page<SysLog>(query.getPage(), query.getLimit());
		
		Page<SysLog> page = sysLogService.selectPageList(pageUtil,query);
		
		return R.ok().put("page", page);
	}
	
}
