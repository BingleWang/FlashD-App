package cn.jeefast.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Constant;
import cn.jeefast.common.utils.R;
import cn.jeefast.system.entity.SysDept;
import cn.jeefast.system.service.SysDeptService;

import java.util.HashMap;
import java.util.List;


/**
 * Department
 * 
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public List<SysDept> list(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<>());

		return deptList;
	}

	/**
	 * Select Department(add、updatemenu)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:dept:select")
	public R select(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<>());

		if(getUserId() == Constant.SUPER_ADMIN){
			SysDept root = new SysDept();
			root.setDeptId(0L);
			root.setName("First level department");
			root.setParentId(-1L);
			root.setOpen(true);
			deptList.add(root);
		}

		return R.ok().put("deptList", deptList);
	}

	/**
	 * Superior departmentId
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public R info(){
		long deptId = 0;
		if(getUserId() != Constant.SUPER_ADMIN){
			SysDept dept = sysDeptService.selectById(getDeptId());
			deptId = dept.getParentId();
		}

		return R.ok().put("deptId", deptId);
	}
	
	/**
	 * info
	 */
	@RequestMapping("/info/{deptId}")
	@RequiresPermissions("sys:dept:info")
	public R info(@PathVariable("deptId") Long deptId){
		SysDept dept = sysDeptService.selectById(deptId);
		
		return R.ok().put("dept", dept);
	}
	
	/**
	 * save
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public R save(@RequestBody SysDept dept){
		sysDeptService.insert(dept);
		
		return R.ok();
	}
	
	/**
	 * update
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public R update(@RequestBody SysDept dept){
		sysDeptService.updateById(dept);
		
		return R.ok();
	}
	
	/**
	 * delete
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	public R delete(long deptId){
		List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
		if(deptList.size() > 0){
			return R.error("Please delete the sub department first");
		}
		SysDept dept = new SysDept();
		dept.setDeptId(deptId);
		dept.setDelFlag(-1);
		sysDeptService.updateById(dept);

		return R.ok();
	}
	
}
