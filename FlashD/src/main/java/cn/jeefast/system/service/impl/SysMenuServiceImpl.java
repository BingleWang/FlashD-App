package cn.jeefast.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.jeefast.common.utils.Constant;
import cn.jeefast.common.utils.Constant.MenuType;
import cn.jeefast.system.dao.SysMenuDao;
import cn.jeefast.system.entity.SysMenu;
import cn.jeefast.system.service.SysMenuService;
import cn.jeefast.system.service.SysUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * menu management Service Implementation Class
 * </p>
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenu> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenu> queryListParentId(Long parentId) {
		return sysMenuDao.queryListParentId(parentId);
	}

	@Override
	public List<SysMenu> queryNotButtonList() {
		return sysMenuDao.queryNotButtonList();
	}

	@Override
	public List<SysMenu> getUserMenuList(Long userId) {
		if(userId == Constant.SUPER_ADMIN){
			return getAllMenuList(null);
		}
		
		// usermenulist
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	@Override
	public List<SysMenu> queryList(Map<String, Object> map) {
		return sysMenuDao.queryList(map);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		sysMenuDao.deleteBatch(menuIds);
	}
	
	@Override
	public List<SysMenu> queryUserList(Long userId) {
		return sysMenuDao.queryUserList(userId);
	}

	/**
	 * getallmenulist
	 */
	private List<SysMenu> getAllMenuList(List<Long> menuIdList){
		List<SysMenu> menuList = queryListParentId(0L, menuIdList);
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 */
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for(SysMenu entity : menuList){
			if(entity.getType() == MenuType.CATALOG.getValue()){//directory
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
	
}
