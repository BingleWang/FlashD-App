package cn.jeefast.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.jeefast.common.annotation.DataFilter;
import cn.jeefast.system.dao.SysRoleDao;
import cn.jeefast.system.entity.SysRole;
import cn.jeefast.system.service.SysRoleDeptService;
import cn.jeefast.system.service.SysRoleMenuService;
import cn.jeefast.system.service.SysRoleService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * role Service Implementation Class
 * </p>
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDeptService sysRoleDeptService;
	
	@Override
	public Page<SysRole> queryPageList(Page<SysRole> page, Map<String, Object> map) {
		page.setRecords(sysRoleDao.queryPageList(page, map));
		return page;
	}
	
	@Override
	@DataFilter(tableAlias = "r", user = false)
	public List<SysRole> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}
	
	@Override
	@Transactional
	public void save(SysRole role) {
		role.setCreateTime(new Date());
		sysRoleDao.insert(role);
		
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
	}

	@Override
	@Transactional
	public void update(SysRole role) {
		sysRoleDao.updateById(role);
		
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleDao.deleteBatch(roleIds);
	}
	
	
}
