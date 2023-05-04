package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysRoleMenu;

/**
 * <p>
  * Corresponding relationship between role and menu Mapper interface
 * </p>
 *
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

	/**
	 * According to roleID，getmenuIDlist
	 */
	List<Long> queryMenuIdList(Long roleId);
	
	void save(Map<String, Object> map);

	void deleteByRoleId(Long roleId);
	
}