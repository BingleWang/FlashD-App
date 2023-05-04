package cn.jeefast.system.controller;

import cn.jeefast.system.entity.SysUserRole;
import cn.jeefast.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import cn.jeefast.common.utils.R;
import cn.jeefast.common.utils.ShiroUtils;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.service.SysUserService;
import cn.jeefast.system.service.SysUserTokenService;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * login
 * 
 */
@RestController
public class SysLoginController {
	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		String text = producer.createText();
		BufferedImage image = producer.createImage(text);
		//save shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * register
	 */
	@RequestMapping(value = "/sys/regsave", method = RequestMethod.POST)
	public Map<String, Object> regsave(String sxh, String sname,
									   String xzraol, String susername, String spassword, String zcspassword
			, String college, String major, String classinfo, String grade,String role)throws IOException {
		if(!spassword.equals(zcspassword)){
			return R.error().put("msg","Password is different");
		}
		if(role == null || role.trim().equals("")){
			return R.error().put("msg","Please select register role");
		}

		SysUser sysUser = new SysUser();
		sysUser.setUsername(susername);
		sysUser.setPassword(spassword);
		sysUser.setRealname(sname);
		sysUser.setStadynum(sxh);
		sysUser.setCollege(college);
		sysUser.setMajor(major);
		sysUser.setClassinfo(classinfo);
		sysUser.setGrade(grade);
		sysUser.setStatus(1);

		List<Long> a = new ArrayList<>();
		a.add(Long.parseLong(role));
		sysUser.setType(role);
		sysUser.setEmail(sysUser.getUsername());

		sysUser.setRoleIdList(a);
		sysUser.setEmail(sysUser.getUsername());

		sysUserService.save(sysUser);

		return R.ok().put("msg","register success");
	}

	/**
	 * login
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password, String captcha)throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("Code error");
		}

		// userinfo
		SysUser user = sysUserService.queryByUserName(username);
		System.out.println("user"+user);
		if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
			return R.error("Username or password error");
		}

		if(user.getStatus() == 0){
			return R.error("Username has been locked, please contact the administrator");
		}

		System.out.println("getUserId"+user.getUserId());
		R r = sysUserTokenService.createToken(user.getUserId());
		System.out.println(user.getUserId()+"rrrrrrrrrrrrrr"+r);
		return r;
	}
	
}
