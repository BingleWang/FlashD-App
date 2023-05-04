package cn.jeefast.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jeefast.common.validator.group.AddGroup;
import cn.jeefast.common.validator.group.UpdateGroup;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * system user
 * </p>
 *
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="user_id", type= IdType.AUTO)
	private Long userId;
    /**
     * username
     */
	@NotBlank(message="userName cannot be empty", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="Incorrect email format", groups = {AddGroup.class})
	private String username;
    /**
     * password
     */
	@NotBlank(message="Password cannot be empty", groups = AddGroup.class)
	private String password;
    /**
     */
	private String salt;
    /**
     * email
     */
//	@Email(message="Incorrect email format", groups = {AddGroup.class, UpdateGroup.class})
	private String email;
    /**
     * mobile
     */
	private String mobile;
    /**
     * state  0：Disable   1：normal
     */
	private Integer status;
    /**
     * deptId
     */
	@TableField("dept_id")
	private Long deptId;
	
	/**
	 * Department Name
	 */
	@TableField(exist=false)
	private String deptName;
	
	/**
	 * roleIDlist
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;
	
    /**
     * createTime
     */
	@TableField("create_time")
	private Date createTime;
	
	/**
	 * createUserID
	 */
	@TableField(exist=false)
	private Long createUserId;

	/**
	 * Userrole
	 */
	@TableField(exist=false)
	private String roalArraystr;

	/**
	 */
	@TableField(exist=false)
	private String photopath;
	
	/**
	 * qq
	 */
	private String qq;
	
	/**
	 * idnumb
	 */
	private String idnumb;
	
	/**
	 *
	 */
	private String wxnub;
	
	/**
	 * birthday
	 */
	private String birthday;
	
	/**
	 * political
	 */
	private String political;
	
	/**
	 */
	private String ramark;
	
	/**
	 */
	private BigDecimal membership;

	private String stadynum;//
	private String realname;//
	private String college;//
	private String major;//
	private String classinfo;//
	private String grade;//
	private String photo;//photo
	private String type;//Usertype

	private String address;

	private String xb;

	private String zzmm;

	private String jtzz;

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 附件info
	 */
	@TableField(exist = false)
	private JSONArray files;

	private String jtysgj;

	private String khh;
	private String yhkh;


	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getJtysgj() {
		return jtysgj;
	}

	public void setJtysgj(String jtysgj) {
		this.jtysgj = jtysgj;
	}

	public JSONArray getFiles() {
		return files;
	}

	public void setFiles(JSONArray files) {
		this.files = files;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStadynum() {
		return stadynum;
	}

	public void setStadynum(String stadynum) {
		this.stadynum = stadynum;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(String classinfo) {
		this.classinfo = classinfo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public BigDecimal getMembership() {
		return membership;
	}

	public void setMembership(BigDecimal membership) {
		this.membership = membership;
	}

	public String getRamark() {
		return ramark;
	}

	public void setRamark(String ramark) {
		this.ramark = ramark;
	}
	
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getWxnub() {
		return wxnub;
	}

	public void setWxnub(String wxnub) {
		this.wxnub = wxnub;
	}
	
	public String getIdnumb() {
		return idnumb;
	}

	public void setIdnumb(String idnumb) {
		this.idnumb = idnumb;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getRoalArraystr() {
		return roalArraystr;
	}

	public void setRoalArraystr(String roalArraystr) {
		this.roalArraystr = roalArraystr;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", status=" + status +
				", deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", roleIdList=" + roleIdList +
				", createTime=" + createTime +
				", createUserId=" + createUserId +
				", roalArraystr='" + roalArraystr + '\'' +
				", photopath='" + photopath + '\'' +
				", qq='" + qq + '\'' +
				", idnumb='" + idnumb + '\'' +
				", wxnub='" + wxnub + '\'' +
				", birthday='" + birthday + '\'' +
				", political='" + political + '\'' +
				", ramark='" + ramark + '\'' +
				", membership=" + membership +
				", stadynum='" + stadynum + '\'' +
				", realname='" + realname + '\'' +
				", college='" + college + '\'' +
				", major='" + major + '\'' +
				", classinfo='" + classinfo + '\'' +
				", grade='" + grade + '\'' +
				", photo='" + photo + '\'' +
				", type='" + type + '\'' +
				", address='" + address + '\'' +
				", xb='" + xb + '\'' +
				", zzmm='" + zzmm + '\'' +
				", jtzz='" + jtzz + '\'' +
				", files=" + files +
				", jtysgj='" + jtysgj + '\'' +
				", khh='" + khh + '\'' +
				", yhkh='" + yhkh + '\'' +
				'}';
	}
}
