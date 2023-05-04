package cn.jeefast.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.jeefast.common.utils.R;
import cn.jeefast.system.dao.SysUserTokenDao;
import cn.jeefast.system.entity.SysUserToken;
import cn.jeefast.system.oauth2.TokenGenerator;
import cn.jeefast.system.service.SysUserTokenService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * system userToken Service Implementation Class
 * </p>
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserToken> implements SysUserTokenService {
	@Autowired
	private SysUserTokenDao sysUserTokenDao;
	private final static int EXPIRE = 3600 * 12;
	
	@Override
	public SysUserToken queryByToken(String token) {
		return sysUserTokenDao.queryByToken(token);
	}
	
	@Override
	public R createToken(long userId) {
		String token = TokenGenerator.generateValue();

		Date now = new Date();
		//expireTime
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		System.out.println("userIduserIduserId"+userId);
//		SysUserToken tokenEntity = sysUserTokenDao.selectById(userId);
		SysUserToken tokenEntityOld = new SysUserToken();
		tokenEntityOld.setUserId(userId);
		SysUserToken tokenEntity = sysUserTokenDao.selectOne(tokenEntityOld);
		if(tokenEntity == null){
			tokenEntity = new SysUserToken();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			sysUserTokenDao.insert(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			sysUserTokenDao.updateById(tokenEntity);
		}

		R r = R.ok().put("token", token).put("expire", EXPIRE);

		return r;
	}
	
}
