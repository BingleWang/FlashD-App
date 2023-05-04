package cn.jeefast.system.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * Transfer pointmanage
 * </p>
 *
 */
@TableName("sys_yszyd")
public class SysYszyd extends Model<SysYszyd> {

    private static final long serialVersionUID = 1L;

    /**
     * Primary keyid
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * order
     */
	private String dingdanid;
    /**
     *
     */
	private Date wldd;
    /**
     * updatetime
     */
	private Date updatetime;
    /**
     * createTime
     */
	private Date createtime;
    /**
     * updateuser
     */
	private String updateuser;
    /**
     * createuser
     */
	private String createuser;
    /**
     */
	private String deptid;

	@TableField(exist = false)
	private String deptName;

	private String name;
	private String zydx;
	private String zydy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZydx() {
		return zydx;
	}

	public void setZydx(String zydx) {
		this.zydx = zydx;
	}

	public String getZydy() {
		return zydy;
	}

	public void setZydy(String zydy) {
		this.zydy = zydy;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDingdanid() {
		return dingdanid;
	}

	public void setDingdanid(String dingdanid) {
		this.dingdanid = dingdanid;
	}

	public Date getWldd() {
		return wldd;
	}

	public void setWldd(Date wldd) {
		this.wldd = wldd;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysYszyd{" +
				"id='" + id + '\'' +
				", dingdanid='" + dingdanid + '\'' +
				", wldd=" + wldd +
				", updatetime=" + updatetime +
				", createtime=" + createtime +
				", updateuser='" + updateuser + '\'' +
				", createuser='" + createuser + '\'' +
				", deptid='" + deptid + '\'' +
				", deptName='" + deptName + '\'' +
				", name='" + name + '\'' +
				", zydx='" + zydx + '\'' +
				", zydy='" + zydy + '\'' +
				'}';
	}
}
