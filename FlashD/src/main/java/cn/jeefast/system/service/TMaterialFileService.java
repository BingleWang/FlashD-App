package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.entity.TMaterialFile;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  service
 * </p>
 *
 */
public interface TMaterialFileService extends IService<TMaterialFile> {

	void setTMaterialFilePrintId(JSONArray filesOld, String parentid);

	Page<TMaterialFile> queryPageList(Page<TMaterialFile> pageUtil, Map<String, Object> map);

	void deleteBatch(String[] ids);
	
	String uploadFile(MultipartFile file);

	void deletePreachFile(String filePath);

	void downFile(String id, HttpServletResponse response);

	Map<String, String> importPsot(HttpServletRequest request);
	Map<String, String> importPsotfiles(HttpServletRequest request);

	void deleteFile(String filePath);
	void deleteFileByid(String id);


	String getIsoldFilesname(String id, String smodeltype);

	String getIsoldFilespath(String id, String smodeltype);
	String mbfkljToName(String mbfklj);
	Boolean verification(String mbbjk, SysUser sysUser);
	
}
