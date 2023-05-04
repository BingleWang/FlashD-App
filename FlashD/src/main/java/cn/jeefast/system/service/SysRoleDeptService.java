package cn.jeefast.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysRoleDept;

/**
 * <p>
 * Role and department correspondence service
 * </p>
 *
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {
	
	void saveOrUpdate(Long roleId, List<Long> deptIdList);
	/**
	 * According to roleID，getdeptIdlist
	 */
	List<Long> queryDeptIdList(Long roleId);
	
}
