package cn.jeefast.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysUserRole;

/**
 * <p>
 *  Correspondence between user and role service
 * </p>
 *
 */
public interface SysUserRoleService extends IService<SysUserRole> {
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * according touserId，getroleIDlist
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void deleteByUserId(Long userId);
}
