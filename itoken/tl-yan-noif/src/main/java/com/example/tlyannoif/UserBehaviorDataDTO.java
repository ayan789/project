package com.example.tlyannoif;

import lombok.*;

@Getter
@Setter
//使用@bulider时，必须添加无参，全参构造方法，否则转json报错
@Builder//使用@bulider时，使用className.bulider().bulid()生成的对象中 属性类型默认的属性值无效，需要在属性上添加@Builder.Default
@NoArgsConstructor
@AllArgsConstructor
public class UserBehaviorDataDTO {
    private long fansCount;
    private long msgCount;
    private long collectCount;
    private long followCount;
    private long redBagCount;
    private long couponCount;


}
