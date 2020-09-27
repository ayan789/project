package com.ccic.salesapp.noncar.dto.request;
import java.lang.reflect.Method;

/**
 * <p>Title:claim </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: </p>
 */

public class VOBase implements Cloneable{
	String pk;
	
	public String getPk() {
		return pk;
	}


	public void setPk(String pk) {
		this.pk = pk;
	}


	public VOBase clone() throws CloneNotSupportedException
	{
		VOBase fb = (VOBase)super.clone();
		return fb;
	}
	
	
	public void init() throws Exception
	{
		Method[] methods = this.getClass().getDeclaredMethods();  //ȡ�����з���
		try{
		for(int i=0;i<methods.length;i++)
		{
			if(methods[i].getName().indexOf("set") >= 0 && !methods[i].getName().equals("set"))  //set
			{
				Class[] cls = methods[i].getParameterTypes();
				if(cls.length > 0)  //�в���
				{
					Object[] objs = new Object[cls.length];
					for(int j=0;j<objs.length;j++)
					{
						objs[j] = null;
					}
					methods[i].invoke(this,objs);
				}
			}
		}
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
				method = method + attributeName;   //ƴ��set������
				if(hasMethod(method))  //�и÷���
				{
					obj = this.getClass().getMethod(method, null).invoke(this,null);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return obj;
	}
	
	/**
	 * �ж��Ƿ��и����ԣ�
	 * ��ȡ�����Ե�get������������ڣ����и�����
	 * @param attriName
	 * @return
	 */
	public boolean hasAttribute(String attriName)
	{
		boolean retBool = false;
		String method = "get";
		attriName = attriName.substring(0,1).toUpperCase() + attriName.substring(1);
		method = method + attriName;   //ƴ��set������
		try
		{
			Method[] mds = this.getClass().getMethods();
			for(Method md : mds)
			{
				if(md.getName().equals(method))
				{
					retBool = true;
				}
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return retBool;
	}
	/**
	 * �ж��Ƿ��и÷��������ظ÷����Ĳ�����������
	 * ��ȡ�����Ե�get������������ڣ����и�����
	 * @param attriName
	 * @return
	 */
	public Class[] hasMethodPrmType(String methodName)
	{
		Class[] cls = null;

		try
		{
			Method[] mds = this.getClass().getMethods();
			for(Method md : mds)
			{
				if(md.getName().equals(methodName))
				{
					cls = md.getParameterTypes();
					break;
				}
			}
//			if(this.getClass().getMethod(method, null) != null)  //�����Դ���
//			{
//				retBool = true;
//			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return cls;
	}
	
	/**
	 * �ж��Ƿ��и÷���
	 * ��ȡ�����Ե�get������������ڣ����и�����
	 * @param attriName
	 * @return
	 */
	public boolean hasMethod(String methodName)
	{
		boolean retBool = false;

		try
		{
			Method[] mds = this.getClass().getMethods();
			for(Method md : mds)
			{
				if(md.getName().equals(methodName))
				{
					retBool = true;
					break;
				}
			}
//			if(this.getClass().getMethod(method, null) != null)  //�����Դ���
//			{
//				retBool = true;
//			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return retBool;
	}
	
	public void set(String attributeName,Object value)
	{
		try{
			String method = "set";
			attributeName = attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
			method = method + attributeName;   //ƴ��set������
			Object[] objs = new Object[]{value};   //set��ֵ
			if(hasMethod(method))
			{
				java.lang.Class[] cls = hasMethodPrmType(method);
				if(hasMethod(method))  //�и÷���
				{
					this.getClass().getMethod(method, cls).invoke(this,objs);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public String[] getAllAttribute()
	{
		
		Method[] methods = this.getClass().getDeclaredMethods(); 
		String[] retStrs = new String[methods.length];
		String attri = "";
		int j = 0;
		try
		{
			for(int i=0;i<methods.length;i++)
			{
				if(methods[i].getName().indexOf("get") >= 0 && !methods[i].getName().equals("get"))  //set
				{
					attri = methods[i].getName();  
					attri = attri.substring(3);  
					attri = attri.substring(0,1).toLowerCase() + attri.substring(1);  
					retStrs[j] = attri;
					j ++;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//ȥ�������еĿ�ֵ
		String[] retStrs2 = null;
		if(j > 0)
		{
			retStrs2 = new String[j-1];
			for(int i=0;i<retStrs2.length;i++)
			{
				retStrs2[i] = retStrs[i];
			}
		}
		return retStrs2;
	}
	
}
