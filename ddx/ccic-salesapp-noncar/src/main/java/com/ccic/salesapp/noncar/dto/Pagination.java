package com.ccic.salesapp.noncar.dto;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import com.ccic.salesapp.noncar.config.SystemConfig;


public class Pagination
    implements Serializable {
	private final static SystemConfig fig = new SystemConfig();
	  public final static String CURRENT_TAG = "currentPage"; //当前页数
	  public final static String PAGINATION_ACTION_TAG = "paginationAction"; //缓存操作
	  public final static String FIRST_ACTION = "First"; //执行跳到第一页操作
	  public final static String NEXT_ACTION = "Next"; //执行跳到下一页操作
	  public final static String PREVIO_ACTION = "Previo"; //执行跳到上一页操作
	  public final static String LAST_ACTION = "Last"; //执行跳到最一页操作
	  public final static String GOTO_PAGE_ACTION = "gotoPage"; //执行跳到指定的某一页操作
	  public final static String PAGES_GOTO = "pageSelect"; //执行goto操作时,用户所指定的页数

	  //start表示当前页开始的记录行数,此行数是相对于totalCount而言的
	  //一般来说,start=每页行数*(当前页数-1)
	  //start的index为0;也就是说,当start=10时,为第11行记录
	  private int start;
	  private int end; //当前页结束的记录行数
	  private int totalCount; //总行数
	  private int rowsPerPage; //每页行数
	  private int currentPage; //当前页数,index从1开始，
	  private String orderByFields; //排序的字段,即hsql中的order by 字段1,字段2
	  private String orderType; //是降序(desc)还是升级(asc)
	  private List data = null;
  public Pagination() {
    start = 0;
    end = 0;
    try {
      rowsPerPage = Integer.parseInt(fig.getRowsPerPage());
    }
    catch (Exception e) {
      System.out.println("Pagination���ڹ���ÿҳ��¼������");
      e.printStackTrace();
    }
    currentPage = 1;
    orderByFields = "";
    orderType = "";
    this.totalCount = 0;
  }

  //���캯��1,����һ�ι��������õ�,
  public Pagination(int totalCount) {
    start = 0;
    end = 0;

    try {
        System.out.println("-----111----"+fig.getRowsPerPage());
      rowsPerPage = Integer.parseInt(fig.getRowsPerPage().equals("")?"0":fig.getRowsPerPage());
    }
    catch (Exception e) {
      System.out.println("Pagination���ڹ���ÿҳ��¼������");
      e.printStackTrace();
    }
    currentPage = 1;
    orderByFields = "";
    orderType = "";
    this.totalCount = totalCount;
  }

  //��ִ�з�ҳ����ʱ�õ�;��ʱ���캯�����ִ�й���
  public Pagination doPagination(String paginationAction, String currentPage) {
    int iCurrent = 0;
    try {
      iCurrent = Integer.parseInt(currentPage);
    }
    catch (NumberFormatException e) {
      iCurrent = getCurrentPage();
    }
    if (paginationAction != null) {
      if (paginationAction.equals(FIRST_ACTION))
        firstPage();
      if (paginationAction.equals(NEXT_ACTION))
        nextPage(iCurrent);
      if (paginationAction.equals(PREVIO_ACTION))
        previoPage(iCurrent);
      if (paginationAction.equals(LAST_ACTION))
        lastPage();
      if (paginationAction.equals(GOTO_PAGE_ACTION)) {
        gotoPage(iCurrent);
      }
    }
    return this;
  }

  public int getStart() {
    start = rowsPerPage * (currentPage - 1);
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  //������һҳ
  public void firstPage() {
    currentPage = 1;
  }

  //������һҳ
  public void nextPage(int iCurrent) {
    currentPage = iCurrent + 1;
    if (currentPage * rowsPerPage > totalCount) {
      lastPage();
    }
  }

  //������һҳ
  public void previoPage(int iCurrent) {
    currentPage = iCurrent - 1;
    if (currentPage <= 0)
      firstPage();
  }

  //�������һҳ
  public void lastPage() {

    if (totalCount % rowsPerPage == 0) {
      currentPage = totalCount / rowsPerPage;
    }
    else {
      currentPage = totalCount / rowsPerPage + 1;
    }
  }

  //����ָ����ĳһҳ
  protected void gotoPage(int pageNumber) {
    if (pageNumber <= 1 ||
        (pageNumber > 1 && getTotalCount() < this.getRowsPerPage()))
      firstPage();
    else if (pageNumber * rowsPerPage >= totalCount)
      lastPage();
    else
      currentPage = pageNumber;
  }

  //���õ�ǰҳ�����һ�е����������е�����,
  public void setEnd(int end) {
    this.end = end;
  }

  //�õ���ǰҳ�����һ�е����������е�����,
  public int getEnd() {
    if (rowsPerPage * currentPage > totalCount)
      end = totalCount;
    else
      end = rowsPerPage * currentPage;
    return end;
  }

  //����������
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  //�õ�������
  public int getTotalCount() {
    return totalCount;
  }

  //����ÿҳ����
  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }

  //�õ�ÿҳ����
  public int getRowsPerPage() {
    return rowsPerPage;
  }

  //�õ���ҳ��
  public int getPages() {
    if (totalCount % rowsPerPage == 0)
      return totalCount / rowsPerPage;
    else
      return totalCount / rowsPerPage + 1;
  }

  //�ж��ܷ񵽵�һҳ;ֻҪ�ܵ���һҳ���϶����е�һҳ.
  public boolean firstEnable() {
    return previoEnable();
  }

  //�ж��ܷ����һҳ��ֻҪ����һҳ���Ϳ϶������һҳ.
  public boolean lastEnable() {
    return nextEnable();
  }

  //�ж��ܷ���һҳ
  public boolean nextEnable() {
    return currentPage * rowsPerPage < totalCount;
  }

  //�ж��ܷ���һҳ
  public boolean previoEnable() {
    return currentPage > 1; //ֻҪ���ǵ�һҳ�����ܵ���һҳ
  }

  //�õ���ǰҳ��
  public int getCurrentPage() {
    return currentPage;
  }

  //���õ�ǰҳ��
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  //�õ���ǰҳ������
  public List getData() {
    return data;
  }

  //���õ�ǰҳ������
  public void setData(List data) {
    this.data = data;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public void setOrderByFields(String orderByFields) {
    this.orderByFields = orderByFields;
  }

  public String getOrderByFields() {
    return orderByFields;
  }
}
