package cn.jeefast.system.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysLog;

/**
 * <p>
 * system log service
 * </p>
 *
 */
public interface SysLogService extends IService<SysLog> {
	public Page<SysLog> selectPageList(Page<SysLog> page, Map<String, Object> map);
}
