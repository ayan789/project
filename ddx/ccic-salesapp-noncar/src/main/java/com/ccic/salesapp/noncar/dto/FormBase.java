package com.ccic.salesapp.noncar.dto;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.ccic.salesapp.noncar.dto.request.VOBase;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FormBase {
	//页面List默认显示记录数；
	public static int DEFAULT_PAGE_SIZE = 10;
	
	public FormBase() {
	}

	private String userID;
	private String orderFid; //排序时的ID
	private String orderType; //排序时Type:asc/desc
	private String searchValue = "";
	private Pagination pagination = new Pagination(0); //分页对象
	private VOBase vo = null;
	private String mhd = ""; //方法类型
	private ActionParameter cfg; //参数类
	private String flag = null;
	private String succesFlag = "0";//操作成功标志；
	
	private int rowStart = 1;//分页起行
	private int rowEnd = 9999999;//分页终行
	private int rowCount = 0;//记录总数
	
	private String mid;	//菜单
	
	private String sysAction;	//add 新增，modify 修改,read 查看
	
	private String pk;  //用户查询，数据库对应的主键，ActionBase中使用，如果Action中不重构toView成函数，则必须设置该值
	
	public void set(String attributeName,Object value)
	{
		try{
		//this.getClass().getDeclaredField(arrtibuteName).set(this,value);
			String method = "set";
			attributeName = attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
			method = method + attributeName;   //拼接set函数名
		Object[] objs = new Object[]{value};   //set的值
		
		java.lang.Class[] cls = new java.lang.Class[]{value.getClass()};  //参数类型 
		this.getClass().getDeclaredMethod(method, cls).invoke(this,objs);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public Object get(String attributeName)
	{
		Object obj = null;
		try{
			//this.getClass().getDeclaredField(arrtibuteName).set(this,value);
				String method = "get";
				attributeName = attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
				method = method + attributeName;   //拼接set函数名
			 
			obj = this.getClass().getDeclaredMethod(method, null).invoke(this,null);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return obj;
	}
	
	
	public void trimSpaces(FormBase form) throws Exception
	{
		 Field[] field = form.getClass().getDeclaredFields(); 
		  for(int j=0 ; j<field.length ; j++){     
              String name = field[j].getName();   
              String type = field[j].getGenericType().toString();   
              if(type.equals("class java.lang.String")){   
                  Method m = form.getClass().getMethod("get"+name.substring(0,1).toUpperCase() +name.substring(1));
                  String value = (String) m.invoke(form);  
                  if(value != null){
                	 value = value.trim();
                	 form.set(name, value.trim());
                  }
              }
		  }
	}
	
	
	public static int getDEFAULT_PAGE_SIZE() {
		return DEFAULT_PAGE_SIZE;
	}

	public static void setDEFAULT_PAGE_SIZE(int default_page_size) {
		DEFAULT_PAGE_SIZE = default_page_size;
	}

	public String getSysAction() {
		return sysAction;
	}

	public void setSysAction(String sysAction) {
		this.sysAction = sysAction;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPk() {
		if(pk == null)
		{
			if(this.getVo() != null)
			{
				pk = this.getVo().getPk();
			}
		}
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	/**
	 * @return rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount 要设置的 rowCount
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return rowEnd
	 */
	public int getRowEnd() {
		return rowEnd;
	}

	/**
	 * @param rowEnd 要设置的 rowEnd
	 */
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	/**
	 * @return rowStart
	 */
	public int getRowStart() {
		return rowStart;
	}

	/**
	 * @param rowStart 要设置的 rowStart
	 */
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	/**
	 * @return succesFlag
	 */
	public String getSuccesFlag() {
		return succesFlag;
	}

	/**
	 * @param succesFlag 要设置的 succesFlag
	 */
	public void setSuccesFlag(String succesFlag) {
		this.succesFlag = succesFlag;
	}

	public String getOrderFid() {
		return orderFid;
	}

	public void setOrderFid(String orderFid) {
		this.orderFid = orderFid;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public VOBase getVo() {
		return vo;
	}

	public String getMhd() {
		return mhd;
	}

	public ActionParameter getCfg() {
		return cfg;
	}

	public void setVo(VOBase vo) {
		this.vo = vo;
	}

	public void setMhd(String mhd) {
		this.mhd = mhd;
	}

	public void setCfg(ActionParameter cfg) {
		this.cfg = cfg;
	}

	/**
	 * @return flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag 要设置的 flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
