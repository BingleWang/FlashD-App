package cn.jeefast.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysUserToken;

/**
 * <p>
  * system userToken Mapper interface
 * </p>
 *
 */
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {

	SysUserToken queryByToken(String token);
    
}