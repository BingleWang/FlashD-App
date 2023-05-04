package cn.jeefast.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysMenu;

/**
 * <p>
 * menu management service
 * </p>
 *
 */
public interface SysMenuService extends IService<SysMenu> {
	/**
	 * according tomenu，query menu
	 * @param parentId menuID
	 * @param menuIdList   usermenuID
	 */
	List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * according to menu，query menu
	 * @param parentId  menuID
	 */
	List<SysMenu> queryListParentId(Long parentId);
	
	/**
	 * Get menulist without button
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * get usermenulist
	 */
	List<SysMenu> getUserMenuList(Long userId);
	
	/**
	 * querymenulist
	 */
	List<SysMenu> queryList(Map<String, Object> map);
	
	/**
	 * delete
	 */
	void deleteBatch(Long[] menuIds);
	
	/**
	 * query User's permission list
	 */
	List<SysMenu> queryUserList(Long userId);
}
