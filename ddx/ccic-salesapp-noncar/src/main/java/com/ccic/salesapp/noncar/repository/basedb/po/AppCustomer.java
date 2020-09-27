package com.ccic.salesapp.noncar.repository.basedb.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 3/10
 * @author gaoxiang
 */
@Data
@ApiModel(value = "AppCustomer", description = "客户信息对象")
public class AppCustomer {
    private Integer id;
    /**
     * 客户类型（1个人2团体）
     */
    @NotBlank(message = "客户类型不得为空")
    private String custType;

    private String custNo;
    @NotBlank(message = "客户名称不得为空")
    @Length(max = 40, message = "客户姓名长度不得大于40个字符")
    private String custName;
    private String sex;
    private Date birthday;
    @NotBlank(message = "证件类型不得为空")
    private String certificateType;
    @NotBlank(message = "证件号码不得为空")
    private String certificateNo;
    private String mobile;
    private String businessNo;
    @Pattern(regexp = "^([京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新][ABCDEFGHJKLMNPQRSTUVWXY][1-9DF][1-9ABCDEFGHJKLMNPQRSTUVWXYZ]\\d{3}[1-9DF]|[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新][ABCDEFGHJKLMNPQRSTUVWXY][\\dABCDEFGHJKLNMxPQRSTUVWXYZ]{5})$", message = "请输入正确的车牌号")
    private String carNo;
    @Pattern(regexp = "^(?!(?:\\d+|[a-zA-Z]+)$)[\\da-zA-Z]{17}$", message = "请输入正确的车架号")
    private String frameNo;
    //厂牌型号
    private String brandNo;
    //发动机号
    private String engineNo;
    @Email(message = "请输入正确的邮箱")
    private String email;
    @Length(max = 40, message = "职业长度不得大于40个字符")
    private String profession;
    @Length(max = 100, message = "地址长度不得大于100个字符")
    private String address;
    /**
     * 地址类型：01家庭地址、02公司地址、03其他
     */
    private String addressType;
    @Length(max = 30, message = "备注长度不得大于30个字符")
    private String remark;
    /**
     * 单位性质：01国家机关、02党政机关、03社会团体、04基层群众自治组织、05事业单位
     */
    private String unitNature;
    @Length(max = 60, message = "企业经营范围长度不得大于60个字符")
    private String businessScope;
    @Length(max = 60, message = "可承保产品长度不得大于60个字符")
    private String contractedProducts;

    private String status;

    private Date createTime;

    private Date updateTime;
    /**
     * 是否本地客户（1本地客户，2保单客户）
     */
    private String custSourceType;
    private String createUser;

    private String updateUser;
}