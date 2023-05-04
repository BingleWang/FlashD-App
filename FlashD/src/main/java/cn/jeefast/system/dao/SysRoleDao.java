package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jeefast.system.entity.SysRole;

/**
 * <p>
  * role Mapper interface
 * </p>
 *
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

	List<SysRole> queryPageList(Page<SysRole> page, Map<String, Object> map);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int deleteBatch(Object[] id);

}