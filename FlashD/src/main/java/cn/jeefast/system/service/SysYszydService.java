package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysYszyd;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * Transfer pointmanage service
 * </p>
 *
 * @author theodo
 * @since 2023-01-06
 */
public interface SysYszydService extends IService<SysYszyd> {
    Page<SysYszyd> queryPageList(Page<SysYszyd> page, Map<String, Object> map);
    void deleteBatch(String[] ids);
}
