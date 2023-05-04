package cn.jeefast.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysRoleMenu;

/**
 * <p>
 * Corresponding relationship between role and menu service
 * </p>
 *
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
	void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * According to roleID，getmenuIDlist
	 */
	List<Long> queryMenuIdList(Long roleId);
}
