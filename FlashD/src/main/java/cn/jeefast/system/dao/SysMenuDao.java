package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysMenu;

/**
 * <p>
  * menu management Mapper interface
 * </p>
 *
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {
	
	List<SysMenu> queryList(Map<String, Object> map);

	/**
	 * menu，query menu
	 * @param parentId menuID
	 */
	List<SysMenu> queryListParentId(Long parentId);
	
	/**
	 * Get menulist without button
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * query User's permission list
	 */
	List<SysMenu> queryUserList(Long userId);
	
	int deleteBatch(Object[] id);
	
}