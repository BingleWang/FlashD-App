package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.TMaterialFileDao;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.entity.TMaterialFile;
import cn.jeefast.system.service.TMaterialFileService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  Service Implementation Class
 * </p>
 *
 */
@Service
public class TMaterialFileServiceImpl extends ServiceImpl<TMaterialFileDao, TMaterialFile> implements TMaterialFileService {

	@Autowired
	private TMaterialFileDao tMaterialFileDao;
	
	@Autowired
	private TMaterialFileService tMaterialFileService;

	/**
	 * @param filesOld
	 * @param parentid
	 */
	@Override
	public void setTMaterialFilePrintId(JSONArray filesOld, String parentid){
		if(!filesOld.isEmpty()){
			for(int i=0;i<filesOld.size();i++) {
				Map<String,Object> files = filesOld.getJSONObject(i);
				TMaterialFile tMaterialFile = getTMaterialFile(files.get("filePath")+"");
				tMaterialFile.setParentid(parentid);
				if(tMaterialFile != null){
					tMaterialFileService.updateById(tMaterialFile);
				}
			}
		}
	}


	public TMaterialFile getTMaterialFile(String filePath){
		TMaterialFile tMaterialFile = tMaterialFileService.selectOne(new EntityWrapper<TMaterialFile>().eq("sfilename",filePath));
		return tMaterialFile;
	}

	@Override
	public Page<TMaterialFile> queryPageList(Page<TMaterialFile> page, Map<String, Object> map) {
		page.setRecords(tMaterialFileDao.queryPageList(page, map));
		return page;
	}

	@Override
	public void deleteBatch(String[] ids) {
		for(String id : ids) {
			deletePreachFile(id);
		}
		
		tMaterialFileDao.deleteBatch(ids);
	}

	@Override
	public String uploadFile(MultipartFile file) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
//		return FileUpLoadUtil.handleFileUpload(dataPath, file);
		return null;
	}

	@Override
	public void deletePreachFile(String id) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "";
		try {
			TMaterialFile tMaterialFile = tMaterialFileDao.selectById(id);
			String path = tMaterialFile.getSfilename();

			File file = new File(dataPath + "/" + path);
			
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downFile(String id, HttpServletResponse response) {
////		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
//		String dataPath ="";
////		SysSwitch addressParaDownload = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>());
////		String plainpass = addressParaDownload.getPlainpass();//密路径address
//		TMaterialFile tMaterialFile = tMaterialFileDao.selectById(id);
//		if(!StringUtils.isEmpty(tMaterialFile.getSfilename())) {
//			try {
//				FileUpLoadUtil.downloadAttachments(plainpass,dataPath, tMaterialFile.getSfilename(), tMaterialFile.getSfilename(),tMaterialFile, response,addressParaDownload);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public Map<String, String> importPsot(HttpServletRequest request) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "D:\\upload";
		Map<String, String> map = null;
		boolean ff = false;
		String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File dir = new File(dataPath + "/" + prefix);
		File saveFileDir = new File(dataPath);
		if (!saveFileDir.exists()) {
			saveFileDir.mkdirs();
			if (!dir.exists()) {
				dir.mkdirs();
			}
		} else {
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multipartFiles = multiRequest.getFiles("fileList");
				for(MultipartFile file : multipartFiles) {
					if (file != null) {
						String fileName = file.getOriginalFilename();
						String fileid = UUID.randomUUID().toString().replace("-", "");
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						String suffix  = makeFileName(fileName);
						String saveFileName = UUID.randomUUID() + suffix;
						String path = prefix + "/" + saveFileName;
						file.transferTo(new File(dir + "/" + saveFileName));
						map = new HashMap<>();
						map.put("fileName", fileName);
						map.put("path", path);
						map.put("ids", saveFileName);
						map.put("id", fileid);
						map.put("dir", dir+"");
						map.put("mbfklj", mbfkljToName(request.getParameter("fileList111")));
						setFiles(path, dataPath + "/", file,request.getParameter("fileList111"),fileid );

						ff = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
//			map.put("mibiaotest", "The encryption program did not start");
			return map;
		}
		return map;
	}
	
	private String makeFileName(String fileName) {
		int lastIndex = fileName.lastIndexOf(".");

        if (lastIndex != -1) {
            return fileName.substring(lastIndex);
        } else {
            return "";
        }
	}
	
	private void setFiles(String sfilename,String sfilelength,MultipartFile files,String mbfklj,String fileid) throws Exception {
		TMaterialFile file =new TMaterialFile();
		file.setId(fileid);
		file.setSfilename(sfilename);
		file.setSfilelength(sfilelength);
		file.setSaccessoryname(files.getOriginalFilename());
		Integer a = tMaterialFileDao.insert(file);

	}

	@Override
	public void deleteFile(String filePath) {
		String dataPath = "";
		try {
			
			Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
			wrapper.eq("sfilename", filePath);
			tMaterialFileDao.delete(wrapper);
			
			File file = new File(dataPath + "/" + filePath);
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteFileByid(String id) {
		String dataPath = "";
		TMaterialFile tMaterialFile = tMaterialFileService.selectById(id);
		if(tMaterialFile != null) {
			try {
				
				Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
				wrapper.eq("id", id);
				tMaterialFileDao.delete(wrapper);
				
				File file = new File(dataPath + "/" + tMaterialFile.getSfilename());
				if (file.exists() && file.isFile()) {
					file.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public Map<String, String> importPsotfiles(HttpServletRequest request) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "";
		Map<String, String> map = null;
		boolean ff = false;
		String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File dir = new File(dataPath + "/" + prefix);
		File saveFileDir = new File(dataPath);
		if (!saveFileDir.exists()) {
			// 创建directory
			saveFileDir.mkdirs();
			if (!dir.exists()) {
				dir.mkdirs();
			}
		} else {
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multipartFiles = multiRequest.getFiles("fileListfiles");
				for(MultipartFile file : multipartFiles) {
					if (file != null) {
						String fileName = file.getOriginalFilename();
						String fileid = UUID.randomUUID().toString().replace("-", "");
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						String suffix  = makeFileName(fileName);
						String saveFileName = UUID.randomUUID() + suffix;
						String path = prefix + "/" + saveFileName;
						file.transferTo(new File(dir + "/" + saveFileName));
						map = new HashMap<>();
						map.put("fileName", fileName);
						map.put("path", path);
						map.put("ids", saveFileName);
						map.put("id", fileid);
						map.put("dir", dir+"");
						setFiles(path, dataPath + "/", file,request.getParameter("fileList111"),fileid );
					
						ff = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public String getIsoldFilesname(String id,String smodeltype) {
		String oldfeilsname = "";
		Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
		wrapper.eq("smodelid", id);
		wrapper.eq("smodeltype", smodeltype);//
		List<TMaterialFile> tbscmsfiles = selectList(wrapper);
		if(tbscmsfiles.size() >0) {
			for(int i=0;i<tbscmsfiles.size();i++) {
				String strname = tbscmsfiles.get(i).getSaccessoryname();//
				oldfeilsname += strname+',';
			}
		}
		return oldfeilsname;
	}
	@Override
	public String getIsoldFilespath(String id,String smodeltype) {
		String oldfilespath = ""; 
		Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
		wrapper.eq("smodelid", id);
		wrapper.eq("smodeltype", smodeltype);//
		List<TMaterialFile> tbscmsfiles = selectList(wrapper);
		if(tbscmsfiles.size() >0) {
			for(int i=0;i<tbscmsfiles.size();i++) {
				String strpath = tbscmsfiles.get(i).getSfilename();//
				oldfilespath += strpath+',';
			}
		}
		return oldfilespath;
	}
	
	/**
	 * @param mbfklj
	 * @return
	 */
	@Override
	public String mbfkljToName(String mbfklj){
		return null;
		
	}

	@Override
	public Boolean verification(String mbbjk,SysUser sysUser) {
		return false;
	}
	
	
	
	
	
	
	
}
