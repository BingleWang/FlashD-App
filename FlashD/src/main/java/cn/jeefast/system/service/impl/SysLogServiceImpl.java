package cn.jeefast.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.jeefast.system.dao.SysLogDao;
import cn.jeefast.system.entity.SysLog;
import cn.jeefast.system.service.SysLogService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * system log Service Implementation Class
 * </p>
 *
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public Page<SysLog> selectPageList(Page<SysLog> page, Map<String, Object> map) {
		page.setRecords(sysLogDao.selectPageList(page, map));
		return page;
	}
	
}
