package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysDept;

/**
 * <p>
  * Department Mapper
 * </p>
 *
 */
public interface SysDeptDao extends BaseMapper<SysDept> {
	
	List<SysDept> queryList(Map<String, Object> map);
	
	/**
     * query deptIdlist
     * @param parentId  Superior departmentID
     */
    List<Long> queryDetpIdList(Long parentId);

}