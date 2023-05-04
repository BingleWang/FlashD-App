package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysYszyd;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * Transfer pointmanage Mapper interface
 * </p>
 *
 * @author theodo
 * @since 2023-01-06
 */
public interface SysYszydDao extends BaseMapper<SysYszyd> {
    List<SysYszyd> queryPageList(Page<SysYszyd> page, Map<String, Object> map);

    int deleteBatch(Object[] id);
}