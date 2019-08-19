package com.neo;


import java.io.*;

/**
 * Created by yangzhilei on 2017/5/12.
 */
public class FileTool {

    public static String readFile(String path) {
        File f = new File(path);
        if (!f.exists())
            return null;
        FileReader fileReader = null;
        BufferedReader br = null;
        StringBuilder buffer = new StringBuilder("");
        try {
            fileReader = new FileReader(f);
            br = new BufferedReader(fileReader);
            String str;
            while ((str = br.readLine()) != null) {
                buffer.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    public static String loadInput(InputStream input) {
        return loadInput(input, "UTF-8");
    }


    public static String loadInput(InputStream input, String charsetName) {
        if (input != null) {
            try {
                return new String(loadByteArray(input), charsetName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] loadByteArray(InputStream input) {
        if (input != null) {
            BufferedInputStream br = null;
            try {
                br = new BufferedInputStream(input);
                byte[] bf = new byte[1024];
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len = 0;
                while ((len = br.read(bf)) > 0) {
                    out.write(bf, 0, len);
                }
                return out.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
