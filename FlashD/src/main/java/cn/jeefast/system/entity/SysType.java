package cn.jeefast.system.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * type table
 * </p>
 *
 */
@TableName("sys_type")
public class SysType extends Model<SysType> {

    private static final long serialVersionUID = 1L;

    /**
     * Primary keyid
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * Classification name
     */
	private String name;
    /**
     * createTime
     */
	private Date createtime;
    /**
     * createuser
     */
	private String createuser;
    /**
     * updateuser
     */
	private String updateuser;
    /**
     * updatetime
     */
	private Date updatetime;
    /**
     * sort
     */
	private Integer xh;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysType{" +
			", id=" + id +
			", name=" + name +
			", createtime=" + createtime +
			", createuser=" + createuser +
			", updateuser=" + updateuser +
			", updatetime=" + updatetime +
			", xh=" + xh +
			"}";
	}
}
