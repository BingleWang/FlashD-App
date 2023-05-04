package cn.jeefast.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysDept;

/**
 * <p>
 * Department service
 * </p>
 *
 */
public interface SysDeptService extends IService<SysDept> {
	List<SysDept> queryList(Map<String, Object> map);
	
	/**
	 * query deptIdlist
	 * @param parentId  Superior departmentID
	 */
	public List<Long> queryDetpIdList(Long parentId);
	
	/**
	 */
	String getSubDeptIdList(Long deptId);
}
