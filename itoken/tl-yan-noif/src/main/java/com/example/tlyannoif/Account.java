package com.example.tlyannoif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @date 2018/4/12 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    //{"alias":"kalakala","userName":"kalakala","passWord":"Za1","birthday":"1991-11-11","level":"1000","vip":"49","strs":["ABC","v","v"]}

    private String id;
    @NotNull
    @Length(max = 20)
    private String userName;
    @NotNull(groups = {GroupOne.class},message = "密码不能为空")
    @Pattern(regexp = "[A-Z][a-z][0-9]",groups = {GroupOne.class},message = "密码过于简单")
    private String passWord;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTime;
    private String alias;
    @Size(min=1,max =10, message = "长度要在1到10个字符之间",groups = {GroupOne.class})
    private String level;
    @DecimalMin(value="1",message = "vip最小是1",groups = {GroupOne.class})
    @DecimalMax(value="50",message = "vip最大是50",groups = {GroupOne.class})
    private Integer vip;
    @NotEmpty(groups = {GroupOne.class},message = "生日不能为空")
    @DateValidator(dateFormat = "yyyy-MM-dd",groups = {GroupTwo.class})
    @DateValidator2(dateFormat = "yyyy-MM-dd",groups = {GroupOne.class})
    private String birthday;
    private List<String> strs;
}