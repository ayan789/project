package com.example.tlyannoif;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.function.Function;

public class FunctionUtils2 {

    public static String load_1_3_4(String str) {

        int a1 = 1;
//        System.out.println(a1);
        int a3 = 3;
//        System.out.println(a3);
        int a4 = 4;
//        System.out.println(a4);
        return str + a1 + a3 + a4;
    }

    public static String load_2_3_5(String str) {
        int a2 = 2;
//        System.out.println(a2);
        int a3 = 3;
//        System.out.println(a3);
        int a5 = 5;
//        System.out.println(a5);
        return str + a2 + a3 + a5;
    }

    public static void main(String[] args){
        System.out.println(FunctionUtils2.load_1_3_4("qq"));
        System.out.println(FunctionUtils2.load_2_3_5("qq"));
//
//        String aa = FunctionUtils2.load_step1("qq",str -> FunctionUtils2.load_1(str));
//        String bb = FunctionUtils2.load_step1("qq",str -> FunctionUtils2.load_2(str));
////        System.out.println(aa);
////        System.out.println(bb);
//        String cc = FunctionUtils2.load_step2(aa,str -> FunctionUtils2.load_4(str));
//        String dd = FunctionUtils2.load_step2(bb,str -> FunctionUtils2.load_5(str));
//        System.out.println(cc);
//        System.out.println(dd);

//        VoDTO voDTO = new VoDTO();

        VoDTO aa = FunctionUtils2.load_step2(FunctionUtils2.load_step1("ww",voDTO_step1 -> FunctionUtils2.load_1(voDTO_step1)),voDTO_step2 -> FunctionUtils2.load_4(voDTO_step2));
        VoDTO bb = FunctionUtils2.load_step2(FunctionUtils2.load_step1("ww",voDTO_step1 -> FunctionUtils2.load_2(voDTO_step1)),voDTO_step2 -> FunctionUtils2.load_5(voDTO_step2));
        System.out.println(JSONObject.toJSON(aa));
        System.out.println(JSONObject.toJSON(bb));
    }

    public static <T> T load_step1(String str, Function<VoDTO, T> fun) {
        VoDTO voDTO = new VoDTO();
        voDTO.setA(str);
        return fun.apply(voDTO);
    }

    public static VoDTO load_1(VoDTO voDTO) {
        voDTO.setA(voDTO.getA()+1);
        return voDTO;
    }

    public static VoDTO load_2(VoDTO voDTO) {
        voDTO.setA(voDTO.getA()+2);
        return voDTO;
    }

    public static <T> T load_step2(VoDTO voDTO, Function<VoDTO, T> fun) {
        voDTO.setA(voDTO.getA()+3);
        return fun.apply(voDTO);
    }

    public static VoDTO load_4(VoDTO voDTO) {
        voDTO.setA(voDTO.getA()+4);
        return voDTO;
    }

    public static VoDTO load_5(VoDTO voDTO) {
        voDTO.setA(voDTO.getA()+5);
        return voDTO;
    }

}
