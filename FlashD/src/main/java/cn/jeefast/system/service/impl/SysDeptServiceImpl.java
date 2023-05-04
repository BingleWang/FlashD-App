package cn.jeefast.system.service.impl;

import com.qiniu.util.StringUtils;

import cn.jeefast.common.annotation.DataFilter;
import cn.jeefast.system.dao.SysDeptDao;
import cn.jeefast.system.entity.SysDept;
import cn.jeefast.system.service.SysDeptService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Department Service Implementation Class
 * </p>
 *
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	@DataFilter(tableAlias = "d", user = false)
	public List<SysDept> queryList(Map<String, Object> map) {
		return sysDeptDao.queryList(map);
	}
	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return sysDeptDao.queryDetpIdList(parentId);
	}
	
	@Override
	public String getSubDeptIdList(Long deptId){
		List<Long> deptIdList = new ArrayList<>();

		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		deptIdList.add(deptId);

		String deptFilter = StringUtils.join(deptIdList, ",");
		return deptFilter;
	}

	/**
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}
	
}
