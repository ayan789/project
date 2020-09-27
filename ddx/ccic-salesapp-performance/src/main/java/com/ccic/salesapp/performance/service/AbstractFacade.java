package com.ccic.salesapp.performance.service;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;


/**
 * @TitleAbstractFacade.java
 * @Description TODO
 * @company 广州易星信息科技有限公司
 * @author 李成炎
 * @date 2012-9-24下午05:49:23
 * @version v1.0	
 */



public class AbstractFacade {
	
	private  static Map<String,Object> clientmap;
	private  static AbstractApplicationContext applicationContext;
	public AbstractFacade(ServletContext sc) {
	}
	public AbstractFacade() {
	}
	//初始化
	static{
		clientmap=new ConcurrentHashMap<String,Object>();
		applicationContext =new ClassPathXmlApplicationContext("classpath:client.xml");
		applicationContext.registerShutdownHook();
	}
	
	public  Object getBean(String bean) { 
		Object object=clientmap.get(bean);
		if (object==null) {
			object=applicationContext.getBean(bean);
			clientmap.put(bean, object);
		}
		return object;
	}
	
	public RequestHeadDTO getConsumerID(){
		RequestHeadDTO requestHead = new RequestHeadDTO();
		requestHead.setConsumerID("mobile");
		return requestHead;
	}
			
	/**
	 * 格式化为格林时间
	 * @param year 年
	 * @param month 月
	 * @param day  天
	 * @return
	 */
	public XMLGregorianCalendar  getTime(int year,int month,int day){
		Calendar calendar=Calendar.getInstance(); 
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		
		GregorianCalendar cal = new GregorianCalendar();
		XMLGregorianCalendar value = null;
		
		try {
			cal.setTime(calendar.getTime());
			value = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * 获取当前时间，精确到毫秒
	 * @return
	 */
	public String getInstanceTime(){
		 Calendar cl = Calendar.getInstance(); 
		 SimpleDateFormat sdf ;
		  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 String operTime =sdf.format(cl.getTime());
		 return operTime;
	}
	/**
	 * 两个日期的比较
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	 public  int compare_date(String DATE1, String DATE2) {
	        
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	 
	//获得下个月第一天的日期
	    public String getNextMonthFirst(){  
	        String str = "";  
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	       Calendar lastDate = Calendar.getInstance();  
	      lastDate.add(Calendar.MONTH,3);//加一个月
	      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天
	       str=sdf.format(lastDate.getTime());  
	       return str;    
	       }  

	  //获得下个月最后一天的日期
	    public String getNextMonthEnd(){  
	        String str = "";  
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
	       Calendar lastDate = Calendar.getInstance();  
	      lastDate.add(Calendar.MONTH,3);//加一个月
	      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天
	      lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天
	       str=sdf.format(lastDate.getTime());  
	       return str;    
	    }  

	    /**
	     * 获得一年后的时间
	     * @return
	     */
	    public String getNextYear(String  date){  
	    	
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	       Date date1=null;
	       try {
	    	   if (date!=null&&!"".equals(date)) {
	    		   date1=sdf.parse(date);
			   }else{
				   date1=new Date();
			   }
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
	       Calendar calendar = Calendar.getInstance();  
	       calendar.setTime(date1);
	       calendar.add(Calendar.YEAR,1);//加一年	 
//	       //20140922 修改孟秀萍 
//	       calendar.add(Calendar.MONTH,-1);//加一个月
//	       calendar.roll(Calendar.DATE, -1);//日期回滚一天
	       return sdf.format(calendar.getTime());   
	    }  
	    /**
	     * 获得一年后前一天的时间
	     * @param date
	     * @return
	     */
	    public String getNextYear1(String  date){  
	    	
	    	String[] subString = date.split("-");
			   String thirdString = subString[2];
			   	   
		       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		       Date date1=null;
		       try {
		    	   if (date!=null&&!"".equals(date)) {
		    		   date1=sdf.parse(date);
				   }else{
					   date1=new Date();
				   }
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
		       Calendar calendar = Calendar.getInstance();  
		       calendar.setTime(date1);
		       calendar.add(Calendar.YEAR,1);//加一年
		      if ("01".equals(thirdString)||"1".equals(thirdString)) {
		    	   calendar.add(Calendar.MONTH,-1);//
			}    
		       calendar.roll(Calendar.DATE, -1);//日期回滚一天		       
		       return sdf.format(calendar.getTime());        
		       
		    }  
	    /**
	     * 获得前一天的时间
	     * @param date
	     * @return
	     */
	    public String getLastDay(String  date){ 
	    	
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		    Date date1=null;
		    try {
		    	
		    	if (date!=null&&!"".equals(date)) {
		    		 date1=sdf.parse(date);
				}else{
					 date1=new Date();
				}
		    }catch (ParseException e) {
					e.printStackTrace();
			}
		    Calendar calendar = Calendar.getInstance();  
		    calendar.setTime(date1);
		    calendar.add(Calendar.DATE, -1);//日期减一天
		    return sdf.format(calendar.getTime());        
	    }  
	 /**
	  * XMLGregorianCalendar -》 String "yyyy-MM-dd" 格式
	  * @param xml
	  * @return
	  */
	 public String converTofomatString(XMLGregorianCalendar xml){
		 String string = null;
		 if (xml==null) {
			string = "";
		}else {
			GregorianCalendar ca = xml.toGregorianCalendar();
			ca.getTime();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 string = formatter.format(ca.getTime());			  
		}
		 
		 return string;
	 }
	 
	 /**
	  * XMLGregorianCalendar对象格式化
	  * @param xml
	  * @param pattern
	  * @return
	  */
	 public String converTofomatString(XMLGregorianCalendar xml,String pattern){
		 String string = null;
		 if (xml==null) {
			string = "";
		}else {
			GregorianCalendar ca = xml.toGregorianCalendar();
			ca.getTime();
			 SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			 string = formatter.format(ca.getTime());			  
		}
		 
		 return string;
	 }
	 
	 /**
	  * date -》 XMLGregorianCalendar
	  * @param date
	  * @return
	  */
	 public XMLGregorianCalendar convertToXMLGregorianCalendar(Date date){
		 GregorianCalendar cal = new GregorianCalendar();
	        cal.setTime(date);
	        XMLGregorianCalendar gc = null;
	        try {
	            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	        } catch (Exception e) {

	             e.printStackTrace();
	        }		
		 return gc;
	 }
	 
	 /**
	  * String 日期到 XMLGregorianCalendar型的转换
	  * @param string
	  * @return
	  */
	 public XMLGregorianCalendar convertToXMLGregorianCalendar(String string) {

		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		 Date date = null;
		try {
			date = df.parse(string);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        GregorianCalendar cal = new GregorianCalendar();
	        cal.setTime(date);
	        XMLGregorianCalendar gc = null;
	        try {
	            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	        } catch (Exception e) {

	             e.printStackTrace();
	        }
	        return gc;
	    }
	 
	 /**
	  * 
	  * @param obj
	  * @return
	  */
	 public double stringToDouble(String obj) {
		String r="0";
		if (obj==null||"".equals(obj)) {
			r="0.00";
		}else {
			r=obj;
		}
		return Double.parseDouble(r);
	}

	 /**
	  * 
	  * @param obj
	  * @return
	  */
	 public Integer stringToInteger(String obj){
		 String string = "";
		 if (obj==null||"".equals(obj)) {
			string="0";
		}else {
			string = obj;
		}
		 Double doub = Double.valueOf(string);
		 Integer integer = doub.intValue();
		return integer;
	 }
	 
	 /**
	  * Double to String
	  * @param d
	  * @return
	  */
	 public String doubleToString(Double d){
		 Double double1;
		 if (d==null||"".equals(d)) {
			 double1 = 0.0;
		}else {
			double1 = d;
		}
		 String string = double1.toString();
		 return string;
	 }
	 
	 /**
	  * Double to BigDecimal
	  * @param d
	  * @return
	  */
	 public BigDecimal doubleToBigDecimal(Double d){
		 BigDecimal bg;
		 if (d==null) {
			 bg = null;
		}else {
			bg = new BigDecimal(String.valueOf(d));
		}
		 return bg;
	 }
	 
	 /**
	  * BigDecimal to String
	  * @param d
	  * @return
	  */
	 public String bigDecimalToString(BigDecimal d){
		 String s;
		 if (d==null) {
			 s = "";
		}else {
			s = d.toString();
		}
		 return s;
	 }
	 
	 /**
	  * 两个字符串年份的计算"yyyy-mm-dd"
	  * @param s1
	  * @param s2
	  * @return
	  */
	 public int YearMinus(String s1,String s2) {		
		 StringTokenizer token1 = new StringTokenizer(s1, "-");
			StringTokenizer token2 = new StringTokenizer(s2, "-");
			int year1 = Integer.parseInt(token1.nextToken());
			int month1 = Integer.parseInt(token1.nextToken());
			int year2 = Integer.parseInt(token2.nextToken());
			int month2 = Integer.parseInt(token2.nextToken());
			int day1 = Integer.parseInt(token1.nextToken());
			int day2 = Integer.parseInt(token2.nextToken());
			int total = 365 * (year1 - year2) + 30*(month1 - month2)+(day1-day2);
			int year = total / 365;
			return year;
		} 
	 
	 /**    
      * 得到几天后的时间    
      *     
      * @param d    
      * @param day    
      * @return    
      */     
     public static String getDateAfter(int day) {    
    	 String str = "";  
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar now = Calendar.getInstance();      
         now.getTime();      
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);   
        str= sdf.format(now.getTime());
         return str;      
     }    

		
		/**
		 * 新老核心代码转换
		 * 
		 * */
		public String transCodeCommon(String sourceCode, String[] codes) {
			String newCode="";
			for(String code:codes){
				if(sourceCode.equals(code.split("-")[1])){
					newCode=code.split("-")[0];
					break;
				}
			}
			return newCode;
		}
}
