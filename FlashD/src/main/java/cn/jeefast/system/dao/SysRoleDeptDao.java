package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysRoleDept;

/**
 * <p>
  * Role and department correspondence Mapper interface
 * </p>
 *
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDept> {

	/**
	 * According to roleID，getdeptIdlist
	 */
	List<Long> queryDeptIdList(Long roleId);
	
	void save(Map<String, Object> map);
	
	void deleteByRoleId(Object roleId);
}