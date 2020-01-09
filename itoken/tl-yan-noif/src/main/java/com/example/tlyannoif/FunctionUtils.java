package com.example.tlyannoif;

import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.function.Function;

public class FunctionUtils {

    public static Date readDate(String dir, String fileName) {
        Path path = Paths.get(dir, fileName);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String result = br.readLine();
            return DateUtils.parseDate(result, "yyyy-MM-dd");
        } catch (IOException e) {
            System.out.println("read fail file: " + path);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer readInteger(String dir, String fileName) {
        Path path = Paths.get(dir, fileName);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String result = br.readLine();
            return Integer.valueOf(result);
        } catch (IOException e) {
            System.out.println("read fail file: " + path);
        }
        return null;
    }

    public static void main(String[] args){
//        System.out.println(FunctionUtils.readDate("/Users/yanshuai/Desktop/qqqw","abc.txt"));
//        System.out.println(FunctionUtils.readInteger("/Users/yanshuai/Desktop/qqqw","ddd.txt"));

        Date step1 = FunctionUtils.read("/Users/yanshuai/Desktop/qqqw","abc.txt",voDTO -> FunctionUtils.read_date(voDTO));
        System.out.println(step1);

        int step2 = FunctionUtils.read("/Users/yanshuai/Desktop/qqqw","ddd.txt",voDTO -> FunctionUtils.read_int(voDTO));
        System.out.println(step2);
    }

    public static <T> T read(String dir, String fileName, Function<VoDTO, T> fun) {
        Path path = Paths.get(dir, fileName);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            VoDTO voDTO = new VoDTO();
            voDTO.setA(br.readLine());
            return fun.apply(voDTO);
        } catch (IOException e) {
            System.out.println("read fail file: " + path);
        }
        return null;
    }

    public static Date read_date(VoDTO voDTO) {
        try {
            return DateUtils.parseDate(voDTO.getA(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int read_int(VoDTO voDTO) {
        return Integer.parseInt(voDTO.getA());
    }

}
