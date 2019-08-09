package com.neo;

import java.nio.charset.Charset;

/**
 * Created by liulei on 2017-04-19.
 */
public class Constant {
    private Constant() {

    }

    public static final Charset GBK                         = Charset.forName("GBK");
    public static final Charset UTF8                        = Charset.forName("UTF-8");
    public static final String  SUCCESS                     = "success";
    public static final String  FAILURE                     = "failure";
    public static final String  SYSTEM                      = "system";
    public static final String  YES                 = "Y";
    public static final String  NO                  = "N";
    public static final String  AGENT_USER_STATUS_NORMAL = "有效";

    public static final String  COOKIE_SESSION_ID           = "HQ1001";

    public static final String ZHONGAN_SUCCESS = "200";
    public static final String DELETED = "Y";
    public static final String NOT_DELETED = "N";
    public static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 10;

    public static final String SEND_MESSAGE_TYPE_RESETPWD = "XNAPP_001";
    public static final String SEND_MESSAGE_AIR_SIGN_SUCCESS = "HQAPP_009";//空中签名成功
    public static final String SEND_MESSAGE_AIR_PAY_SUCCESS = "HQAPP_010";//空中支付成功

    public static final String APPLY_RELATION_SELF = "00";//投被关系-本人

    public final class Login {
        private Login(){

        }

        public static final String COOKIE_FRONT_LOGIN_KEY     = "HTTP_USER_TOKEN";
        public static final String COOKIE_FRONT_LOGIN_COUNT     = "HQ1004";
        public static final String COOKIE_FRONT_OPEN_ID = "HQ1003";
        public static final String LOGIN_ERROR_FRONT_PREFIX = "HQ1004";
        public static final String SEPARATE_TOKEN="&&";
        public final static int    EXPIRE_TIME_LOGIN          = 20 * 60;                 //7D
        public final static String  ACCEPT_LANGUAGE          = "Accept-Language";
        public final static String REDIS_LOGIN_USER_MAP = "REDIS_LOGIN_USER_MAP_LDAP";
    }

    public final  class Agent
    {
        private Agent(){
        }
        public static final String ENABLED = "Y";
        public static final String NOT_ENABLED = "N";
        public static final String STATUS_01 = "01";
        public static final String STATUS_02 = "02";
        public static final String STATUS_03 = "03";
        public static final String STATUS_04 = "04";
        public static final String STATUS_05 = "05";

//    01－增员，02－二次增员，03－离职,04-二次离职,05-清退

    }


    public final class Proposal {
        private Proposal(){

        }

        public static final String T_PROPOSAL     = "t_proposal";
//        public static final String COOKIE_FRONT_LOGIN_COUNT     = "HQ1004";
//        public static final String COOKIE_FRONT_OPEN_ID = "HQ1003";
//        public static final String LOGIN_ERROR_FRONT_PREFIX = "HQ1004";
//        public static final String SEPARATE_TOKEN="HQ1005";

        public final static int    CREATE_PROPOSAL_TIME          = 7 * 24 * 60 * 60;                 //7D
        public final static int    CREATE_PLAN_TIME          = 7 * 24 * 60 * 60;                 //7D
    }

    public final class Attendance {
        private Attendance() {

        }

        public static final String HQ_WORK_ATTENDANCE = "hq_work_attendance";
        public static final String HQ_REMEDY_RECORD = "hq_remedy_record";
        public static final String HQ_ATTENDANCE_SETTING = "hq_attendance_setting";


        public static final String NORMAL_SIGN = "1";
        public static final String LATE = "2";
        public static final String UNSIGN = "0";
        public static final String NOTSIGNYET = "-1";

        public static final String AUDIT_PASS = "0";
        public static final String AUDIT_NOT_PASS = "2";

        public static final String AUDITING = "1";

        public static final String NOT_REMEDY = "0";

        public static final String SETTING_NORMAL = "1";
        public static final String SETTING_LATE = "2";
        public static final String SETTING_UNSIGN = "0";
    }

    public final class Study {
        private Study() {

        }

        public static final String HQ_STUDY_MENU = "hq_study_menu";
        public static final String STUDY_MENU_TYPE = "1";
        public static final String HQ_STUDY_CONTENT = "hq_study_content";
        public static final String HQ_STUDY_ISTOP = "Y";
        public static final String HQ_STUDY_NOTOP = "N";
        public static final String HQ_STUDY_STATUS = "2";
/*        public static final String HQ_STUDY_TYPE_PDF = "PDF";
        public static final String HQ_STUDY_TYPE_MP3 = "MP3";
        public static final String HQ_STUDY_TYPE_MP4OR3GP = "MP4or3GP";
        public static final String HQ_STUDY_TYPE_ONE = "1";
        public static final String HQ_STUDY_TYPE_TWO = "2";
        public static final String HQ_STUDY_TYPE_THREE = "3";
        public static final String HQ_STUDY_LABEL_NEW = "1";
        public static final String HQ_STUDY_LABEL_HOT = "2";
        public static final String HQ_STUDY_LABEL_GROOM = "1";*/
        public static final String HQ_STUDY_STATUS_FIRST = "1";//1-未发布
        public static final String HQ_STUDY_STATUS_SECOND = "2";//2-已上线
        public static final String HQ_STUDY_STATUS_THRID = "3";//3-已发布待提交
        public static final String HQ_STUDY_STATUS_FOURTH = "4";//4-待审核
        public static final String HQ_STUDY_STATUS_FIFTH = "5";//5-审核通过
        public static final String HQ_STUDY_STATUS_SIXTH = "6";//6-审核退回
    }

    public final class Banner {
        private Banner() {

        }

        public static final String HQ_ANNOUNCEMENT = "hq_announcement";
    }

    public final class Customer {
        private Customer() {
        }

        public static final String MESSAGE_TABLE = "hq_message";//message表名
        public static final int NEW_CUSTOMER_TYPE = 0;//准客户
        public static final int OLD_CUSTOMER_TYPE = 1;//老客户
        public static final int APPLY_CUSTOMER_TYPE = 2;//待承保客户
        public static final String SYSTEM_INFORMATION = "0";//系统消息
        public static final String MANUAL_INFORMATION = "1";//手工消息
        public static final String BIRTHDAY_REMIND_INFORMATION = "2";//生日提醒消息
        public static final String BIRTHDAY_REMIND_TITLE = "生日提醒";
        public static final String BIRTHDAY_REMIND_CONTENT = "后天是s%的生日,记得送上你的祝福";
    }


    public final class Pay {
        private Pay() {

        }


    }

    public final class Product {
        private Product() {

        }

        public static final String HQ_PRODUCT = "za_insure_product";
        public static final String HQ_PRODUCT_CHANNEL = "个险";
        public static final String HQ_MAIN_INSURANCE = "hq_main_insurance";
        public static final String HQ_DELETE_FLAG = "N";
    }

    public final class Account {
        private Account() {

        }

        public static final String HQ_SYS_ACCOUNT = "za_sys_account";
        public static final String HQ_DEFAULT_STATUS = "Y";
        public static final String HQ_DELETE_FLAG = "N";
        public static final String HQ_DEFAULT_INNER = "I";
        public static final String HQ_DEFAULT_PASSFLAG = "0";
        public static final String HQ_NOT_DEFAULT_PASSFLAG = "1";
        public static final String HQ_DELETEED = "Y";
        public static final String HQ_DEFAULT_ROLESTATUS = "1";
    }

    public final class Role {
        private Role() {

        }

        public static final String HQ_STATUS_NO = "N";//启用
        public static final String HQ_STATUS_YES = "Y";//禁用
        public static final String HQ_DELETED_NO = "N";//非删除
        public static final String HQ_DELETED_YES = "Y";//删除

    }
    
    public final class Exam{
    	private Exam()
    	{
    		
    	}
    	
    	public static final String HQ_PAPER_INFO = "hq_paper_info";//试卷表
        public static final String HQ_PAPERS_TEMPLATE="hq_papers_template";//试卷模板表
    	public static final String HQ_PAPER_DETAIL= "hq_answer_detail";//试卷记录表
    	public static final String QUESTION_TYPE_RANDOM ="1";//随机选择试题
    	public static final String IS_NOT_SAVE ="N";//不保存试题
    }
    
    public final class Recommended {
    	private Recommended()
    	{
    		
    	}
    	
    	public static final String APP_RECOMMENDED_ENTRY = "app_recommended_entry";//推荐人主表
    	public static final String APP_RECOMMENDED_APPROVE = "app_recommended_approve";//担保/审核记录表
    }

    public final class CertType {
        private CertType() {

        }
        public  static  final String ID_CARD = "0";//身份证
        public  static  final String HOUSEHOLD_REGISTER = "4";//户口本
        public  static  final String PASSPORT = "1";//护照
        public  static  final String SERVICEMAN_CARD = "2";//军人证
        public  static  final String SOLDIERS = "A";//士兵证
        public  static  final String REENTRY_PERMIT = "B";//回乡证
        public  static  final String MTP = "E";//台胞证
        public  static  final String BIRTH_CERTIFICATE = "7";//台胞证
    }

    public final class Note {
        private Note() {

        }

        public static final String HQ_AGENT_NOTE = "hq_agent_note";//工作日志表名
    }

    public final class BranchLevel {
        private BranchLevel() {

        }

        public static final String GROUP = "01";//营业组
        public static final String DEPARTMENT = "02";//营业部
        public static final String DISTRICT = "03";//营业区
    }

    public final class AgentUser {
        private AgentUser() {

        }

        public static final String JOB_LEVEL_A01 = "A01";//见习理财规划师
        public static final String JOB_LEVEL_A02 = "A02";//理财规划师
        public static final String JOB_LEVEL_A03 = "A03";//高级理财规划师
        public static final String JOB_LEVEL_A04 = "A04";//首席理财规划师
        public static final String JOB_LEVEL_B01 = "B01";//业务组经理
        public static final String JOB_LEVEL_B02 = "B02";//高级业务组经理
        public static final String JOB_LEVEL_C01 = "C01";//营业部经理
        public static final String JOB_LEVEL_C02 = "C02";//高级营业部经理
        public static final String JOB_LEVEL_D01 = "D01";//业务总监
        public static final String JOB_LEVEL_D02 = "D02";//高级业务总监


    }

    public final class BusinessType {
        private BusinessType() {

        }

        public static final String ACTIVITY = "4";//活动
        public static final String SURVEY = "3";//问卷
    }

    public final class OriginFlag {
        private OriginFlag() {

        }

        public static final String APP = "1";//app
        public static final String JDT = "2";//经代通
    }

    public final class Organization {
        private Organization() {

        }
        public static final String ORGANIZATION_LEVEL_CLASS_A = "01";
        public static final String ORGANIZATION_LEVEL_CLASS_B = "02";
        public static final String ORGANIZATION_LEVEL_CLASS_C = "03";
        public static final String ORGANIZATION_LEVEL_CLASS_D = "04";
        public static final String ORGANIZATION_LEVEL_CLASS_E = "05";
    }
    public final class MyEarning {
        private MyEarning() {

        }
        public static final String MYEARNING_NEWTYPEINSURANCE = "新保类";
        public static final String MYEARNING_RENEWALCLASS = "续收类";
        public static final String MYEARNING_ORGANIZATIONCLASS = "组织类";
        public static final String MYEARNING_EXHIBITIONAWARDS = "展业奖励类";
        public static final String TABLE_NAME = "hq_myearning";
    }

    //新人入职相关模板
    public final class EntryTemplate{
        private EntryTemplate(){}

        public static final String PERSON_INFO="hq-business-person-info";//信息登记
        public static final String INTERVIEW_RECORD="hq-business-interview-record";//面谈记录
        public static final String HEALTH_TOLD="hq-business-health-told";//健康告知
        public static final String GUARANTY="hq-business-guaranty";//担保书
        public static final String UNSIGNED_GUARANTY="hq-business-guaranty-unsigned";//担保书(不显示签名)
        public static final String RELA_PROTOCOL="hq-business-protocol";//相关协议表
        public static final String PERSON_GRADE="hq-business-score";//试卷得分
        public static final String ENTRY_EXAM="hq-business-info4-template.pdf";//入职静态试题名称
        public static final String ENTRY_EXAM_OSSURL="business_exam.pdf";//入职静态试题在oss中存储的url
    }
}
