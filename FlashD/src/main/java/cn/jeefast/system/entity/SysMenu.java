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
 * menu management
 * </p>
 *
 */
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

	@TableId(value="menu_id", type= IdType.AUTO)
	private Long menuId;
    /**
     * menuID，First level menu 0
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * menu name
     */
	private String name;
    /**
     * menuURL
     */
	private String url;
    /**
     * empower(Multiple separated by commas, such as: user: list, user: create)
     */
	private String perms;
    /**
     * type   0：directory   1：menu   2：button
     */
	private Integer type;
    /**
     * menuicon
     */
	private String icon;
    /**
     * sort
     */
	@TableField("order_num")
	private Integer orderNum;

	/**
	 * ztreeattribute
	 */
	@TableField(exist=false)
	private Boolean open;
	
	@TableField(exist=false)
	private List<?> list;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
		return this.menuId;
	}

	@Override
	public String toString() {
		return "SysMenu{" +
			", menuId=" + menuId +
			", parentId=" + parentId +
			", name=" + name +
			", url=" + url +
			", perms=" + perms +
			", type=" + type +
			", icon=" + icon +
			", orderNum=" + orderNum +
			"}";
	}
}
