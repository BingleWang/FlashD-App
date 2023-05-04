package cn.jeefast.system.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.exception.RRException;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.utils.Constant.MenuType;
import cn.jeefast.system.entity.SysMenu;
import cn.jeefast.system.service.ShiroService;
import cn.jeefast.system.service.SysMenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * systemmenu
 * 
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroService shiroService;

	/**
	 * Menu
	 */
	@RequestMapping("/nav")
	public R nav(){
		List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		return R.ok().put("menuList", menuList).put("permissions", permissions);
	}
	
	/**
	 * allmenulist
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenu> list(){
		List<SysMenu> menuList = sysMenuService.queryList(new HashMap<>());

		return menuList;
	}
	
	/**
	 * selectmenu(add、updatemenu)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		//querylistdata
		List<SysMenu> menuList = sysMenuService.queryNotButtonList();
		
		SysMenu root = new SysMenu();
		root.setMenuId(0L);
		root.setName("First level menu");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * menuinfo
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenu menu = sysMenuService.selectById(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * save
	 */
	@Log("savemenu")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenu menu){
		//datacheck
		verifyForm(menu);
		
		sysMenuService.insert(menu);
		
		return R.ok();
	}
	
	/**
	 * update
	 */
	@Log("updatemenu")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenu menu){
		//datacheck
		verifyForm(menu);
				
		sysMenuService.updateById(menu);
		
		return R.ok();
	}
	
	/**
	 * delete
	 */
	@Log("deletemenu")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public R delete(long menuId){
		if(menuId <= 31){
			return R.error("Systemmenu, cannot delete");
		}

		//Please delete the submenu or button first
		List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("Please delete the submenu or button first");
		}

		sysMenuService.deleteBatch(new Long[]{menuId});
		
		return R.ok();
	}
	
	/**
	 *
	 */
	private void verifyForm(SysMenu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("menu Name cannot be empty");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("up Menu cannot be empty");
		}
		
		//menu
		if(menu.getType() == MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("MenuURL cannot be empty");
			}
		}
		
		//up menutype
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//directory、menu
		if(menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()){
			if(parentType != MenuType.CATALOG.getValue()){
				throw new RRException("up menu only for directory type");
			}
			return ;
		}
		
		//button
		if(menu.getType() == MenuType.BUTTON.getValue()){
			if(parentType != MenuType.MENU.getValue()){
				throw new RRException("up Menu can only be a menu type");
			}
			return ;
		}
	}
}
