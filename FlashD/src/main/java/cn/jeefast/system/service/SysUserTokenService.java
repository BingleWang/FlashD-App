package cn.jeefast.system.service;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.common.utils.R;
import cn.jeefast.system.entity.SysUserToken;

/**
 * <p>
 * system userToken service
 * </p>
 *
 */
public interface SysUserTokenService extends IService<SysUserToken> {
	SysUserToken queryByToken(String token);
	
	/**
	 * token
	 * @param userId  userId
	 */
	R createToken(long userId);
}
