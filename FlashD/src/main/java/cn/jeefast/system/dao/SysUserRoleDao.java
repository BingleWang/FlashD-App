package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysUserRole;

/**
 * <p>
  *  Correspondence between user and role Mapper interface
 * </p>
 *
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

	List<SysUserRole> queryList(Map<String, Object> map);
	
	/**
	 * according touserId，getroleIDlist
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void save(Map<String, Object> map);
	
	int deleteByUserId(Long userId);
}