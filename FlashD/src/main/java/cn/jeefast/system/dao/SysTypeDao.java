package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * type table Mapper interface
 * </p>
 *
 */
public interface SysTypeDao extends BaseMapper<SysType> {
    List<SysType> queryPageList(Page<SysType> page, Map<String, Object> map);

    int deleteBatch(Object[] id);
}