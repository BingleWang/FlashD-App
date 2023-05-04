package cn.jeefast.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.jeefast.system.dao.SysRoleMenuDao;
import cn.jeefast.system.entity.SysRoleMenu;
import cn.jeefast.system.service.SysRoleMenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Corresponding relationship between role and menu Service Implementation Class
 * </p>
 *
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		sysRoleMenuDao.deleteByRoleId(roleId);

		if(menuIdList.size() == 0){
			return ;
		}

		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		map.put("menuIdList", menuIdList);
		sysRoleMenuDao.save(map);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}
}
