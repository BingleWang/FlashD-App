package cn.jeefast.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 */
@TableName("sys_order")
public class SysDingdan extends Model<SysDingdan> {

    private static final long serialVersionUID = 1L;

    /**
     * Primary keyid
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * ordertype
     */
    @TableField("goodstype")
	private String ddtype;

	/**
	 * ordertype
	 */
	@TableField(exist = false)
	private String typename;

	@TableField(exist = false)
	private String sfqrshname;
    /**
     * Amount
     */
	@TableField("orderamount")
	private Double ddje;
    /**
     *
     */
	@TableField("ordertime")
	private Date kssj;
    /**
     */
	private String parintid;
	private Date updatetime;
    /**
     */
	@TableField("customeraccount")
	private String ydry;
    /**
     */
	@TableField("endtime")
	private Date jssj;

//	@TableField(exist = false)
//	private String name;
	@TableField("receivingaddress")
	private String shdz;//Delivery address

	@TableField("totalordernumber")
	private String hjxdh;//

	@TableField("currentlytransportedtothetransferpoint")
	private String wlzt;//Logistics status

	@TableField("whethertoconfirmreceiptofgoods")
	private String sfqrsh;//

	@TableField("standby1")
	private String sqthzt;//

	@TableField("standby1")
	private String thyy;//


	@TableField("totalpaymentamount")
	private Double zongjiage;

	@TableField("numberofitems")
	private Integer sl;

	@TableField("Iteminformation")
	private String name;

	@TableField("customershippingaddress")
	private String fhdz;

	@TableField("coordinatex")
	private String shzbx;

	@TableField("coordinatey")
	private String shzby;

	@TableField("coordinatey")
	private Double zl;
	private Date createtime;
	private String createuser;
	private String updateuser;

	@TableField("shippersname")
	private String fhrxm;

	@TableField("senderscontactinformation")
	private String fhrlxfs;

	@TableField("consigneesname")
	private String shrxm;
	@TableField("consigneescontactinformation")
	private String shrlxfs;

	@TableField("remarks")
	private String bz;

	@TableField("personinchargeoflogistics")
	private String ddfzr;

	@TableField("sendingcity")
	private String fcdcs;

	@TableField("destinationcity")
	private String mddcs;

	@TableField("startpointcoordinatesx")
	private String qdhzbx;
	@TableField("startpointcoordinatesy")
	private String qdhzby;

	@TableField(exist = false)
	private String ysdh;

	@TableField("whethertheuserpaid")
	private String yhsfyfk;

	@TableField(exist = false)
	private String yhsfyfkname;

	@TableField(exist = false)
	private  Double ddzje;

	@TableField(exist = false)
	private  Double wdylje;

	@TableField(exist = false)
	private  Double ptcchjje;

	@TableField("postcode1")
	private String ybone;

	@TableField("postcode2")
	private String ybtwo;


	public String getYbone() {
		return ybone;
	}

	public void setYbone(String ybone) {
		this.ybone = ybone;
	}

	public String getYbtwo() {
		return ybtwo;
	}

	public void setYbtwo(String ybtwo) {
		this.ybtwo = ybtwo;
	}

	public Double getWdylje() {
		return wdylje;
	}

	public void setWdylje(Double wdylje) {
		this.wdylje = wdylje;
	}

	public Double getPtcchjje() {
		return ptcchjje;
	}

	public void setPtcchjje(Double ptcchjje) {
		this.ptcchjje = ptcchjje;
	}

	public Double getDdzje() {
		return ddzje;
	}

	public void setDdzje(Double ddzje) {
		this.ddzje = ddzje;
	}

	public String getYhsfyfkname() {
		return yhsfyfkname;
	}

	public void setYhsfyfkname(String yhsfyfkname) {
		this.yhsfyfkname = yhsfyfkname;
	}

	public String getYhsfyfk() {
		return yhsfyfk;
	}

	public void setYhsfyfk(String yhsfyfk) {
		this.yhsfyfk = yhsfyfk;
	}

	public String getYsdh() {
		return ysdh;
	}

	public void setYsdh(String ysdh) {
		this.ysdh = ysdh;
	}

	public String getQdhzbx() {
		return qdhzbx;
	}

	public void setQdhzbx(String qdhzbx) {
		this.qdhzbx = qdhzbx;
	}

	public String getQdhzby() {
		return qdhzby;
	}

	public void setQdhzby(String qdhzby) {
		this.qdhzby = qdhzby;
	}

	public String getFcdcs() {
		return fcdcs;
	}

	public void setFcdcs(String fcdcs) {
		this.fcdcs = fcdcs;
	}

	public String getMddcs() {
		return mddcs;
	}

	public void setMddcs(String mddcs) {
		this.mddcs = mddcs;
	}

	public String getDdfzr() {
		return ddfzr;
	}

	public void setDdfzr(String ddfzr) {
		this.ddfzr = ddfzr;
	}

	public String getSfqrshname() {
		return sfqrshname;
	}

	public void setSfqrshname(String sfqrshname) {
		this.sfqrshname = sfqrshname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getFhrxm() {
		return fhrxm;
	}

	public void setFhrxm(String fhrxm) {
		this.fhrxm = fhrxm;
	}

	public String getFhrlxfs() {
		return fhrlxfs;
	}

	public void setFhrlxfs(String fhrlxfs) {
		this.fhrlxfs = fhrlxfs;
	}

	public String getShrxm() {
		return shrxm;
	}

	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}

	public String getShrlxfs() {
		return shrlxfs;
	}

	public void setShrlxfs(String shrlxfs) {
		this.shrlxfs = shrlxfs;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFhdz() {
		return fhdz;
	}

	public void setFhdz(String fhdz) {
		this.fhdz = fhdz;
	}

	public String getShzbx() {
		return shzbx;
	}

	public void setShzbx(String shzbx) {
		this.shzbx = shzbx;
	}

	public String getShzby() {
		return shzby;
	}

	public void setShzby(String shzby) {
		this.shzby = shzby;
	}

	public Double getZl() {
		return zl;
	}

	public void setZl(Double zl) {
		this.zl = zl;
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

	public Double getZongjiage() {
		return zongjiage;
	}

	public void setZongjiage(Double zongjiage) {
		this.zongjiage = zongjiage;
	}

	public Integer getSl() {
		return sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}

	public String getSfqrsh() {
		return sfqrsh;
	}

	public void setSfqrsh(String sfqrsh) {
		this.sfqrsh = sfqrsh;
	}

	public String getSqthzt() {
		return sqthzt;
	}

	public void setSqthzt(String sqthzt) {
		this.sqthzt = sqthzt;
	}

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

	public String getWlzt() {
		return wlzt;
	}

	public void setWlzt(String wlzt) {
		this.wlzt = wlzt;
	}

	public String getHjxdh() {
		return hjxdh;
	}

	public void setHjxdh(String hjxdh) {
		this.hjxdh = hjxdh;
	}

	public String getShdz() {
		return shdz;
	}

	public void setShdz(String shdz) {
		this.shdz = shdz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDdtype() {
		return ddtype;
	}

	public void setDdtype(String ddtype) {
		this.ddtype = ddtype;
	}

	public Double getDdje() {
		return ddje;
	}

	public void setDdje(Double ddje) {
		this.ddje = ddje;
	}

	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	public String getParintid() {
		return parintid;
	}

	public void setParintid(String parintid) {
		this.parintid = parintid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getYdry() {
		return ydry;
	}

	public void setYdry(String ydry) {
		this.ydry = ydry;
	}

	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysDingdan{" +
				"id='" + id + '\'' +
				", ddtype='" + ddtype + '\'' +
				", typename='" + typename + '\'' +
				", sfqrshname='" + sfqrshname + '\'' +
				", ddje=" + ddje +
				", kssj=" + kssj +
				", parintid='" + parintid + '\'' +
				", updatetime=" + updatetime +
				", ydry='" + ydry + '\'' +
				", jssj=" + jssj +
				", shdz='" + shdz + '\'' +
				", hjxdh='" + hjxdh + '\'' +
				", wlzt='" + wlzt + '\'' +
				", sfqrsh='" + sfqrsh + '\'' +
				", sqthzt='" + sqthzt + '\'' +
				", thyy='" + thyy + '\'' +
				", zongjiage=" + zongjiage +
				", sl=" + sl +
				", name='" + name + '\'' +
				", fhdz='" + fhdz + '\'' +
				", shzbx='" + shzbx + '\'' +
				", shzby='" + shzby + '\'' +
				", zl=" + zl +
				", createtime=" + createtime +
				", createuser='" + createuser + '\'' +
				", updateuser='" + updateuser + '\'' +
				", fhrxm='" + fhrxm + '\'' +
				", fhrlxfs='" + fhrlxfs + '\'' +
				", shrxm='" + shrxm + '\'' +
				", shrlxfs='" + shrlxfs + '\'' +
				", bz='" + bz + '\'' +
				", ddfzr='" + ddfzr + '\'' +
				", fcdcs='" + fcdcs + '\'' +
				", mddcs='" + mddcs + '\'' +
				", qdhzbx='" + qdhzbx + '\'' +
				", qdhzby='" + qdhzby + '\'' +
				", ysdh='" + ysdh + '\'' +
				", yhsfyfk='" + yhsfyfk + '\'' +
				", yhsfyfkname='" + yhsfyfkname + '\'' +
				", ddzje=" + ddzje +
				", wdylje=" + wdylje +
				", ptcchjje=" + ptcchjje +
				", ybone='" + ybone + '\'' +
				", ybtwo='" + ybtwo + '\'' +
				'}';
	}
}
