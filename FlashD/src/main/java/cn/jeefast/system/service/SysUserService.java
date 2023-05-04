package cn.jeefast.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysUser;

/**
 * <p>
 * system user service
 * </p>
 *
 */
public interface SysUserService extends IService<SysUser> {
	
	Page<SysUser> queryPageList(Page<SysUser> pageUtil, Map<String, Object> map);
	
	/**
	 * query userlist
	 */
	List<SysUser> queryList(Map<String, Object> map);
	
	/**
	 * query User's all permissions
	 * @param userId  userId
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * query user的allmenuID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * according tousername，querysystem user
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * delete user
	 */
	void deleteBatch(Long[] userIds);

	void save(SysUser user);

	void update(SysUser user);


}
