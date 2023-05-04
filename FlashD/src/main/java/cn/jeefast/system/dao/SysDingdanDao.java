package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysDingdan;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper interface
 * </p>
 *
 */
public interface SysDingdanDao extends BaseMapper<SysDingdan> {

    List<SysDingdan> queryPageList(Page<SysDingdan> page, Map<String, Object> map);
    List<SysDingdan> queryZdcxPageList(Page<SysDingdan> page, Map<String, Object> map);
    int deleteBatch(Object[] id);

    List<Map<String,Object>> queryPageListByYye(Map<String, Object> map);

}