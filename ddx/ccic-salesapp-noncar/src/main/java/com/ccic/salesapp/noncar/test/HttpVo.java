package com.ccic.salesapp.noncar.test;

import com.ccic.salessapp.common.core.web.HttpResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//使用@bulider时，必须添加无参，全参构造方法，否则转json报错
@Builder//使用@bulider时，使用className.bulider().bulid()生成的对象中 属性类型默认的属性值无效，需要在属性上添加@Builder.Default
@NoArgsConstructor
@AllArgsConstructor
public class HttpVo {
	 HttpResult<?> res;
	 HttpResult<?> res1;
	 HttpResult<?> res2;
	 HttpResult<?> res3;
	 HttpResult<?> res4;
}
