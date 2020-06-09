package com.example.tlyanprd.planelement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value=Include.NON_NULL)
@Data
public class PbBox {
	String name ="";
	String code ="";
	String imgUrl ="";
	String linkUrl ="";
}
