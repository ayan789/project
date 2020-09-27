package com.ccic.salesapp.noncar.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import java.io.*;

@Slf4j
public class FileUtil {

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @throws Exception
     */
    public static File decoderBase64File(String base64Code, String fileName) {
        File file = new File(fileName);
        try {
            int index = base64Code.indexOf(",");
            String substring = base64Code.substring(index + 1);
            byte[] buffer = new BASE64Decoder().decodeBuffer(substring);
            FileOutputStream out = new FileOutputStream(file);
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
