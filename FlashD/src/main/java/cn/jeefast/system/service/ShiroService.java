package cn.jeefast.system.service;

import java.util.Set;

import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.entity.SysUserToken;

/**
 * Shiro related interfaces
 */
public interface ShiroService {
    /**
     * get User permission list
     */
    Set<String> getUserPermissions(long userId);

    SysUserToken queryByToken(String token);

    /**
     * according touserId，query user
     * @param userId
     */
    SysUser queryUser(Long userId);
}
