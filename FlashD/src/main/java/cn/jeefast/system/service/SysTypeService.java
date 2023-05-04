package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysType;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * type table service
 * </p>
 *
 */
public interface SysTypeService extends IService<SysType> {
    Page<SysType> queryPageList(Page<SysType> page, Map<String, Object> map);
    void deleteBatch(String[] ids);
}
