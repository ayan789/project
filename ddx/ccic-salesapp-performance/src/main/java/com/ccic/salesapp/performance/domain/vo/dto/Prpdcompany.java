package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 机构
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:46:20
 */
@ApiModel("机构")
public class Prpdcompany implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("机构代码")
	private String comcode;
	@ApiModelProperty("机构名称")
	private String comcname;
	@ApiModelProperty("机构英文名")
	private String comename;
	@ApiModelProperty("地址中文名称")
	private String addresscname;
	@ApiModelProperty("地址英文名称")
	private String addressename;
	@ApiModelProperty("邮编")
	private String postcode;
	@ApiModelProperty("电话")
	private String phonenumber;
	@ApiModelProperty("移动电话")
	private String taxnumber;
	@ApiModelProperty("传真电话")
	private String faxnumber;
	@ApiModelProperty("上级机构代码")
	private String uppercomcode;
	@ApiModelProperty("保险公司全称")
	private String insurername;
	@ApiModelProperty("单位性质 [1]管理 [2]物业 [3]直销 [4]营销 [5]综合 [6]业务销售 [7]业务管理 [8]综合管理")
	private String comattribute;
	@ApiModelProperty("机构类型 [1]：1出单机构 [2]：1归属机构 [3]：1收付机构 [4]：1分保机构 [5]：1公估行类型")
	private String comtype;
	@ApiModelProperty("机构级别 1：总公司 2：省公司 3：分公司 4：地市 5：县区 6：科室 7：代理点")
	private String comlevel;
	@ApiModelProperty("机构主管")
	private String manager;
	@ApiModelProperty("财务主管")
	private String accountleader;
	@ApiModelProperty("出纳")
	private String cashier;
	@ApiModelProperty("会计师")
	private String accountant;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("新的机构代码（同comcode）")
	private String newcomcode;
	@ApiModelProperty("有效性")
	private String validstatus;
	@ApiModelProperty("财务帐套")
	private String acntunit;
	@ApiModelProperty("部门专项代码")
	private String articlecode;
	@ApiModelProperty("账簿代码")
	private String acccode;
	@ApiModelProperty("帐套标识 1：核算单位 2：基层单位 3：不详")
	private String centerflag;
	@ApiModelProperty("对外结付公司代码")
	private String outerpaycode;
	@ApiModelProperty("对内结付公司代码")
	private String innerpaycode;
	@ApiModelProperty("标志字段 1：不详 2：地区代码 3：不详")
	private String flag;
	@ApiModelProperty("单位专项")
	private String dwarticle;
	@ApiModelProperty("经营保险业务许可证号")
	private String licenseno;
	@ApiModelProperty("保险行业机构代码")
	private String circcomcode;
	@ApiModelProperty("许可证颁发日期")
	private String begindate;
	@ApiModelProperty("许可证有效期终止日期")
	private String licenceendate;
	@ApiModelProperty("经营区域代码")
	private String areacode;
	@ApiModelProperty("")
	private String businesstype;
	@ApiModelProperty("")
	private Date businessstartdate;
	@ApiModelProperty("")
	private Date changetime;
	@ApiModelProperty("")
	private String sorttime;

	/**
	 * 设置：机构代码
	 */
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	/**
	 * 获取：机构代码
	 */
	public String getComcode() {
		return comcode;
	}
	/**
	 * 设置：机构名称
	 */
	public void setComcname(String comcname) {
		this.comcname = comcname;
	}
	/**
	 * 获取：机构名称
	 */
	public String getComcname() {
		return comcname;
	}
	/**
	 * 设置：机构英文名
	 */
	public void setComename(String comename) {
		this.comename = comename;
	}
	/**
	 * 获取：机构英文名
	 */
	public String getComename() {
		return comename;
	}
	/**
	 * 设置：地址中文名称
	 */
	public void setAddresscname(String addresscname) {
		this.addresscname = addresscname;
	}
	/**
	 * 获取：地址中文名称
	 */
	public String getAddresscname() {
		return addresscname;
	}
	/**
	 * 设置：地址英文名称
	 */
	public void setAddressename(String addressename) {
		this.addressename = addressename;
	}
	/**
	 * 获取：地址英文名称
	 */
	public String getAddressename() {
		return addressename;
	}
	/**
	 * 设置：邮编
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * 获取：邮编
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * 设置：电话
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	/**
	 * 获取：电话
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	/**
	 * 设置：移动电话
	 */
	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}
	/**
	 * 获取：移动电话
	 */
	public String getTaxnumber() {
		return taxnumber;
	}
	/**
	 * 设置：传真电话
	 */
	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}
	/**
	 * 获取：传真电话
	 */
	public String getFaxnumber() {
		return faxnumber;
	}
	/**
	 * 设置：上级机构代码
	 */
	public void setUppercomcode(String uppercomcode) {
		this.uppercomcode = uppercomcode;
	}
	/**
	 * 获取：上级机构代码
	 */
	public String getUppercomcode() {
		return uppercomcode;
	}
	/**
	 * 设置：保险公司全称
	 */
	public void setInsurername(String insurername) {
		this.insurername = insurername;
	}
	/**
	 * 获取：保险公司全称
	 */
	public String getInsurername() {
		return insurername;
	}
	/**
	 * 设置：单位性质 [1]管理 [2]物业 [3]直销 [4]营销 [5]综合 [6]业务销售 [7]业务管理 [8]综合管理
	 */
	public void setComattribute(String comattribute) {
		this.comattribute = comattribute;
	}
	/**
	 * 获取：单位性质 [1]管理 [2]物业 [3]直销 [4]营销 [5]综合 [6]业务销售 [7]业务管理 [8]综合管理
	 */
	public String getComattribute() {
		return comattribute;
	}
	/**
	 * 设置：机构类型 [1]：1出单机构 [2]：1归属机构 [3]：1收付机构 [4]：1分保机构 [5]：1公估行类型
	 */
	public void setComtype(String comtype) {
		this.comtype = comtype;
	}
	/**
	 * 获取：机构类型 [1]：1出单机构 [2]：1归属机构 [3]：1收付机构 [4]：1分保机构 [5]：1公估行类型
	 */
	public String getComtype() {
		return comtype;
	}
	/**
	 * 设置：机构级别 1：总公司 2：省公司 3：分公司 4：地市 5：县区 6：科室 7：代理点
	 */
	public void setComlevel(String comlevel) {
		this.comlevel = comlevel;
	}
	/**
	 * 获取：机构级别 1：总公司 2：省公司 3：分公司 4：地市 5：县区 6：科室 7：代理点
	 */
	public String getComlevel() {
		return comlevel;
	}
	/**
	 * 设置：机构主管
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
	 * 获取：机构主管
	 */
	public String getManager() {
		return manager;
	}
	/**
	 * 设置：财务主管
	 */
	public void setAccountleader(String accountleader) {
		this.accountleader = accountleader;
	}
	/**
	 * 获取：财务主管
	 */
	public String getAccountleader() {
		return accountleader;
	}
	/**
	 * 设置：出纳
	 */
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	/**
	 * 获取：出纳
	 */
	public String getCashier() {
		return cashier;
	}
	/**
	 * 设置：会计师
	 */
	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}
	/**
	 * 获取：会计师
	 */
	public String getAccountant() {
		return accountant;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：新的机构代码（同comcode）
	 */
	public void setNewcomcode(String newcomcode) {
		this.newcomcode = newcomcode;
	}
	/**
	 * 获取：新的机构代码（同comcode）
	 */
	public String getNewcomcode() {
		return newcomcode;
	}
	/**
	 * 设置：有效性
	 */
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	/**
	 * 获取：有效性
	 */
	public String getValidstatus() {
		return validstatus;
	}
	/**
	 * 设置：财务帐套
	 */
	public void setAcntunit(String acntunit) {
		this.acntunit = acntunit;
	}
	/**
	 * 获取：财务帐套
	 */
	public String getAcntunit() {
		return acntunit;
	}
	/**
	 * 设置：部门专项代码
	 */
	public void setArticlecode(String articlecode) {
		this.articlecode = articlecode;
	}
	/**
	 * 获取：部门专项代码
	 */
	public String getArticlecode() {
		return articlecode;
	}
	/**
	 * 设置：账簿代码
	 */
	public void setAcccode(String acccode) {
		this.acccode = acccode;
	}
	/**
	 * 获取：账簿代码
	 */
	public String getAcccode() {
		return acccode;
	}
	/**
	 * 设置：帐套标识 1：核算单位 2：基层单位 3：不详
	 */
	public void setCenterflag(String centerflag) {
		this.centerflag = centerflag;
	}
	/**
	 * 获取：帐套标识 1：核算单位 2：基层单位 3：不详
	 */
	public String getCenterflag() {
		return centerflag;
	}
	/**
	 * 设置：对外结付公司代码
	 */
	public void setOuterpaycode(String outerpaycode) {
		this.outerpaycode = outerpaycode;
	}
	/**
	 * 获取：对外结付公司代码
	 */
	public String getOuterpaycode() {
		return outerpaycode;
	}
	/**
	 * 设置：对内结付公司代码
	 */
	public void setInnerpaycode(String innerpaycode) {
		this.innerpaycode = innerpaycode;
	}
	/**
	 * 获取：对内结付公司代码
	 */
	public String getInnerpaycode() {
		return innerpaycode;
	}
	/**
	 * 设置：标志字段 1：不详 2：地区代码 3：不详
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：标志字段 1：不详 2：地区代码 3：不详
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：单位专项
	 */
	public void setDwarticle(String dwarticle) {
		this.dwarticle = dwarticle;
	}
	/**
	 * 获取：单位专项
	 */
	public String getDwarticle() {
		return dwarticle;
	}
	/**
	 * 设置：经营保险业务许可证号
	 */
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	/**
	 * 获取：经营保险业务许可证号
	 */
	public String getLicenseno() {
		return licenseno;
	}
	/**
	 * 设置：保险行业机构代码
	 */
	public void setCirccomcode(String circcomcode) {
		this.circcomcode = circcomcode;
	}
	/**
	 * 获取：保险行业机构代码
	 */
	public String getCirccomcode() {
		return circcomcode;
	}
	/**
	 * 设置：许可证颁发日期
	 */
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	/**
	 * 获取：许可证颁发日期
	 */
	public String getBegindate() {
		return begindate;
	}
	/**
	 * 设置：许可证有效期终止日期
	 */
	public void setLicenceendate(String licenceendate) {
		this.licenceendate = licenceendate;
	}
	/**
	 * 获取：许可证有效期终止日期
	 */
	public String getLicenceendate() {
		return licenceendate;
	}
	/**
	 * 设置：经营区域代码
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	/**
	 * 获取：经营区域代码
	 */
	public String getAreacode() {
		return areacode;
	}
	/**
	 * 设置：
	 */
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	/**
	 * 获取：
	 */
	public String getBusinesstype() {
		return businesstype;
	}
	/**
	 * 设置：
	 */
	public void setBusinessstartdate(Date businessstartdate) {
		this.businessstartdate = businessstartdate;
	}
	/**
	 * 获取：
	 */
	public Date getBusinessstartdate() {
		return businessstartdate;
	}
	/**
	 * 设置：
	 */
	public void setChangetime(Date changetime) {
		this.changetime = changetime;
	}
	/**
	 * 获取：
	 */
	public Date getChangetime() {
		return changetime;
	}
	/**
	 * 设置：
	 */
	public void setSorttime(String sorttime) {
		this.sorttime = sorttime;
	}
	/**
	 * 获取：
	 */
	public String getSorttime() {
		return sorttime;
	}
}
