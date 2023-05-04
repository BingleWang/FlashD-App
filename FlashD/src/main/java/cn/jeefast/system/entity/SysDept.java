package cn.jeefast.system.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * Department
 * </p>
 *
 */
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

	@TableId(value="dept_id", type= IdType.AUTO)
	private Long deptId;
    /**
     * Superior departmentID，First level department为0
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * Department Name
     */
	private String name;
    /**
     * sort
     */
	@TableField("order_num")
	private Integer orderNum;
    /**
     * isnodelete  -1：已delete  0：normal
     */
	@TableField("del_flag")
	private Integer delFlag;
	
	/**
	 * superiorDepartment Name
	 */
	@TableField(exist=false)
	private String parentName;
	/**
	 * ztreeattribute
	 */
	@TableField(exist=false)
	private Boolean open;
	
	@TableField(exist=false)
	private List<?> list;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}

	@Override
	public String toString() {
		return "SysDept{" +
			", deptId=" + deptId +
			", parentId=" + parentId +
			", name=" + name +
			", orderNum=" + orderNum +
			", delFlag=" + delFlag +
			"}";
	}
}
