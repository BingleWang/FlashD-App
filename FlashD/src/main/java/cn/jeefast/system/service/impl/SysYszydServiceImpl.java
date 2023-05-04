package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.SysYszydDao;
import cn.jeefast.system.entity.SysYszyd;
import cn.jeefast.system.service.SysYszydService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * Transfer pointmanage Service Implementation Class
 * </p>
 *
 */
@Service
public class SysYszydServiceImpl extends ServiceImpl<SysYszydDao, SysYszyd> implements SysYszydService {

    @Autowired
    private SysYszydDao dao;

    @Override
    public Page<SysYszyd> queryPageList(Page<SysYszyd> page, Map<String, Object> map) {
        page.setRecords(dao.queryPageList(page, map));
        return page;
    }

    @Override
    public void deleteBatch(String[] ids) {
        dao.deleteBatch(ids);
    }

}
