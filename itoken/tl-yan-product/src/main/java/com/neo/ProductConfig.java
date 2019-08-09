package com.neo;


/**
 * Created by yangzhilei on 2017/10/18.
 */
public class ProductConfig {

    public static final String RISK_MAIN = "main";//主险
    public static final String RISK_RIDER = "rider";//附加险

    public static final String TYPE_INPUT = "input";//输入
    public static final String TYPE_SELECT = "select";//下拉选择
    public static final String TYPE_BUTTON_GROUP = "button_group";//单选

    //录入项
    public static final String KEY_AMOUNT = "amount";//保额
    public static final String KEY_PREMIUM = "premium";//保费
    public static final String KEY_QUANTITY = "quantity";//份数
    public static final String KEY_PAY_FREQ = "pay_freq";//缴费类别
    public static final String KEY_PAY = "pay";//交费年期
    public static final String KEY_INSURE = "insure";//保险期间
    public static final String KEY_RANK = "rank";//档次

    /**
     * init节点下以key_input_+键值，value中配置lable,inputType
     * 如保额：
     * <init>
     * <item name="key_input_amount" type="string" value="保额,input"></item>
     * </init>
     */

    //默认标签名，xml中有配置以xml中为准
    public static String getLabel(String key) {
        if (KEY_AMOUNT.equals(key)) {
            return "保额";
        }
        if (KEY_PREMIUM.equals(key)) {
            return "保费";
        }
        if (KEY_INSURE.equals(key)) {
            return "保险期间";
        }
        if (KEY_PAY.equals(key)) {
            return "交费年期";
        }
        if (KEY_PAY_FREQ.equals(key)) {
            return "交费频次";
        }
        if (KEY_QUANTITY.equals(key)) {
            return "份数";
        }
        if (KEY_RANK.equals(key)) {
            return "档次";
        }
        return "未知录入项";
    }

    //默认输入类型，xml中有配置以xml中为准
    public static String getInputType(String key) {
        if (KEY_AMOUNT.equals(key) || KEY_PREMIUM.equals(key)) {
            return TYPE_INPUT;
        }
        return TYPE_SELECT;
    }

}
