package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jeefast.system.entity.SysLog;

/**
 * <p>
  * system log Mapper interface
 * </p>
 *
 */
public interface SysLogDao extends BaseMapper<SysLog> {

	/**
     * <p>
     * query : statestatequery userlist，paging view
     * </p>
     *
     * @param page
     * @param state
     *            state
     * @return
     */
    List<SysLog> selectPageList(Pagination page, Map<String, Object> map);
    
}