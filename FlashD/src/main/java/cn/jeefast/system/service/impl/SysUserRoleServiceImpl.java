package cn.jeefast.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.jeefast.system.dao.SysUserRoleDao;
import cn.jeefast.system.entity.SysUserRole;
import cn.jeefast.system.service.SysUserRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Correspondence between user and role Service Implementation Class
 * </p>
 *
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		sysUserRoleDao.deleteByUserId(userId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		sysUserRoleDao.save(map);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void deleteByUserId(Long userId) {
		sysUserRoleDao.deleteByUserId(userId);
	}
}
