package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jeefast.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  * system user Mapper interface
 * </p>
 *
 */
public interface SysUserDao extends BaseMapper<SysUser> {
	
	List<SysUser> queryPageList(Page<SysUser> page, Map<String, Object> map);

	List<SysUser> queryList(Map<String, Object> map);
	
	/**
	 * query User's all permissions
	 * @param userId  userId
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * query user allmenuID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * according tousername，querysystem user
	 */
	SysUser queryByUserName(String username);
	
	int deleteBatch(Object[] id);


}