package com.ccic.salesapp.performance.config;
import java.util.*;

import javax.servlet.http.*;

import com.ccic.salesapp.performance.exception.GeneralException;
import com.ccic.salesapp.performance.exception.GeneralExceptionHandler;


/**
 * <p>Title: 系统配置</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 易星科技</p>
 * @author
 * @version 1.0
 */

public class SystemConfig {
  private ResourceBundle myResource = null;
  private String resourceName = null;
  public SystemConfig() {
    this.resourceName = "system";
  }

  public SystemConfig(String resourceName) {
    this.resourceName = resourceName;
  }

  private void getResourceBundle() throws GeneralException {
    try {
      myResource = ResourceBundle.getBundle(resourceName);
    }
    catch (Exception e) {
      throw GeneralExceptionHandler.handle(e);
    }
  }

  public String getUploadPath() throws GeneralException {
    try {
      if (myResource == null)
        getResourceBundle();
      return myResource.getString("uploadPath");
    }
    catch (Exception e) {
      throw GeneralExceptionHandler.handle(e);
    }
  }

  /**
   * 理赔图片资料路径
   * @return
   * @throws GeneralException
   */
  public String getImageDataPath() throws GeneralException {
	    try {
	      if (myResource == null)
	        getResourceBundle();
	      return myResource.getString("imageDataPath");
	    }
	    catch (Exception e) {
	      throw GeneralExceptionHandler.handle(e);
	    }
	  }

  
  public String getUploadPathByCache(HttpServletRequest request) throws
      GeneralException {
    try {
      Object obj = request.getSession().getServletContext().getAttribute(
          WebConstants.SYSTEM_PARAMETER);
      return ( (Properties) obj).getProperty("uploadPath");
    }
    catch (Exception ex) {
      throw GeneralExceptionHandler.handle(ex);
    }
  }

  /*  功能：获取配置得内部文件目录
   *  编写者：
   */
  public String getNbwjPath() throws GeneralException {
    try {
      if (myResource == null)
        getResourceBundle();
      return myResource.getString("intermanage.nbwj.path");
    }
    catch (Exception e) {
      throw GeneralExceptionHandler.handle(e);
    }

  }

  /*  功能：获取每页显示记录数
   *  编写者：
   */
  public String getRowsPerPage() throws GeneralException {
    try {
      if (myResource == null)
        getResourceBundle();
      return myResource.getString("page.rowsPerPage");
    }
    catch (Exception e) {
      throw GeneralExceptionHandler.handle(e);
    }

  }

  /**
   * 获取外部资源定义的值
   * @param resourceName String  外部资源名称
   * @return String
   * @throws GeneralException
   */
  public String getResourceValue(String resourceName) throws GeneralException {
    try {
      if (myResource == null)
        getResourceBundle();
      return myResource.getString(resourceName);
    }
    catch (Exception e) {
      throw GeneralExceptionHandler.handle(e);
    }
  }

  public static void main(String args[]) throws Exception {
    SystemConfig fig = new SystemConfig();
    System.out.println(fig.getRowsPerPage());
    System.out.println("this.osName = " + fig.getResourceValue("osName"));
  }
}