package com.common.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;


public class FileUtil {
    public static final String pathSeparator = "/";

    // 把文件读入byte数组
    public static byte[] readFile2Byte(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        byte data[] = new byte[(int) len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(data);
        if (r != len) {
            fin.close();
            throw new IOException("Only read " + r + " of " + len + " for " + file);
        }
        fin.close();
        return data;
    }

    // 把byte数组写出到文件
    public static void writeFile(String filename, byte data[]) throws IOException {
        filename=filename.replace("%7B","{").replace("%7D","}");
        File file=new File(filename);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream fout = new FileOutputStream(filename);
        fout.write(data);
        fout.close();
    }

    public static String readFile(String path) throws Exception {
        StringBuffer str = new StringBuffer();
        BufferedReader in = null;
        File inputFile = null;
        String realPath = path; // ClassLoader.getSystemResource(path).getPath();
        inputFile = new File(realPath);
        in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "GBK"));
        String line = null;
        str = new StringBuffer((int) inputFile.length());
        while ((line = in.readLine()) != null) {
            str.append(line);
        }
        in.close();
        return str.toString();
    }

    public static void fileWriter(String path, String fileName, String msg, String fileType) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(path + File.separator + fileName + "." + fileType);
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(msg);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileWriter(String path, String fileName, String msg) {
        GregorianCalendar gcNow = new GregorianCalendar();
        String localDir = File.separator + gcNow.get(GregorianCalendar.YEAR) + File.separator
                + (Integer.parseInt(gcNow.get(GregorianCalendar.MONTH) + "") + 1) + File.separator
                + gcNow.get(GregorianCalendar.DAY_OF_MONTH);

        File file = new File(path + localDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(path + localDir + File.separator + fileName + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(msg);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成uuid文件名称
     */
    public static String getUUIDFileName(String realFileName) {
        int count = realFileName.lastIndexOf(".");
        String fileName = UUIDGenerator.create32Key() + realFileName.substring(count);
        return fileName;
    }

    /**
     * 功能：对文件进行压缩 files -> 要压缩的文件 tempPath -> 压缩文件存放临时目录 zipFileName -> 压缩文件名称
     */
    public static File getZip(File[] files, String zipPath, String zipFileName) throws IOException {
        if (files == null || zipPath == null || zipFileName == null) {
            return null;
        }
        String uuid = UUIDGenerator.create32Key();
        String tempPath = zipPath + "/" + uuid + "/";
        for (int i = 0; i < files.length; i++) {
            fileChannelCopy(files[i], new File(tempPath + "/" + files[i].getName()));
        }
        String zipFilePath = zipPath + "/" + zipFileName;

        deleteFile(new File(tempPath));
        return getZip(tempPath, zipFilePath);
    }

    /**
     * 功能：对文件进行压缩 sourceDir -> 原文件路径 zipFilePath -> 压缩之后的文件目录
     */
    public static File getZip(String sourceDir, String zipFilePath) throws IOException {
        File file = new File(sourceDir);
        File zipFile = new File(zipFilePath);

        OutputStream os = new FileOutputStream(zipFile);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        ZipOutputStream zos = new ZipOutputStream(bos);

        // 原文件所在的文件夹
        String sourceBasePath = getDirectoryFromFile(file);
        // 压缩之后文件所在的文件夹
        String zipBasePath = getDirectoryFromFile(zipFile);

        zipFile(file, sourceBasePath, zipBasePath, zos);
        zos.closeEntry();
        zos.close();

        return new File(zipFilePath);
    }

    /**
     * 功能：获得文件目录 说明：如果是文件则获得所在文件夹目录 如果是文件夹则返回文件夹目录
     */
    private static String getDirectoryFromFile(File file) {
        String sourceBasePath = null;
        if (file.isDirectory()) {
            sourceBasePath = file.getPath();
        } else {
            sourceBasePath = file.getParent();
        }
        return sourceBasePath;
    }

    /**
     * 功能：压缩文件 source -> 需要压缩的文件 sourceBasePath -> 压缩文件所在的根目录 zipBasePath ->
     * 压缩之后的文件目录 zos -> 压缩输出流
     */
    private static void zipFile(File source, String sourceBasePath, String zipBasePath, ZipOutputStream zos)
            throws IOException {
        File[] files = null;
        if (source.isDirectory()) {
            files = source.listFiles();
        } else {
            files = new File[1];
            files[0] = source;
        }

        InputStream is = null;
        String pathName;
        byte[] buf = new byte[1024];
        int length = 0;
        try {
            for (File file : files) {
                if (file.isDirectory()) {// 文件夹处理
                    pathName = pathSeparator + file.getPath().substring(sourceBasePath.length()) + pathSeparator;
                    pathName = new File(pathName).getPath().replace("\\", pathSeparator);

                    File[] subFiles = file.listFiles();
                    if (subFiles == null || subFiles.length <= 0) {// 空文件夹的情况
                        zos.putNextEntry(new ZipEntry(pathName));
                    }
                    System.out.println(pathName);
                    zipFile(file, sourceBasePath, zipBasePath, zos);
                } else {// 文件处理
                    pathName = pathSeparator + file.getPath().substring(sourceBasePath.length());
                    pathName = new File(pathName).getPath().replace("\\", pathSeparator);
                    is = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    zos.putNextEntry(new ZipEntry(pathName));
                    while ((length = bis.read(buf)) > 0) {
                        zos.write(buf, 0, length);
                    }
                    bis.close();
                    System.out.println(pathName);
                }
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * 文件复制
     */
    public static void fileChannelCopy(File sourceFile, File targetFile) throws IOException {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        if (sourceFile.isDirectory()) {
            File[] subFiles = sourceFile.listFiles();
            if (subFiles != null && subFiles.length > 0) {
                for (int i = 0; i < subFiles.length; i++) {
                    String newFilePath = targetFile.getPath() + "/" + subFiles[i].getName();
                    File newFile = new File(newFilePath);
                    if (subFiles[i].isDirectory()) {
                        if (!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        fileChannelCopy(subFiles[i], newFile);
                    } else {
                        fi = new FileInputStream(subFiles[i]);
                        fo = new FileOutputStream(newFile);
                        in = fi.getChannel();// 得到对应的文件通道
                        out = fo.getChannel();// 得到对应的文件通道
                        in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
                    }
                }
            }
        } else {
            fi = new FileInputStream(sourceFile);
            fo = new FileOutputStream(targetFile);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        }
        if (fi != null) {
            fi.close();
            in.close();
            fo.close();
            out.close();
        }
    }

    /**
     * 获得所有子目录
     */
    public static List<File> getSubFiles(File file) {
        List<File> list = new ArrayList<File>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                list.add(files[i]);
                List<File> subfiles = getSubFiles(files[i]);
                list.addAll(subfiles);
            }
        }
        return list;
    }

    /**
     * 删除文件
     */
    public static void deleteFile(File file) {
        if (file != null) {
            if (file.isFile()) {
                file.delete();
            } else {
                File[] subFiles = file.listFiles();
                for (int i = 0; i < subFiles.length; i++) {
                    if (subFiles[i].isFile()) {
                        subFiles[i].delete();
                    } else {
                        deleteFile(subFiles[i]);
                    }
                }
            }
        }
        file.delete();
    }

    public static File[] convertListToArray(List<File> list) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return new File[] {};
        }
        File[] objs = new File[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objs[i] = list.get(i);
        }
        return objs;
    }

    public static String fileURL(String path){
        GregorianCalendar gcNow = new GregorianCalendar();
        String localDir=gcNow.get(GregorianCalendar.YEAR)+"/"+(Integer.parseInt(gcNow.get(GregorianCalendar.MONTH)+"")+1)+"/"+gcNow.get(GregorianCalendar.DAY_OF_MONTH)+"/";

        File file=new File(path+localDir);
        if(!file.exists()){
            file.mkdirs();
        }

        return path+localDir;
    }

    /**
     * office文本文件转base64图片
     *
     * @param url
     *            文件的URL地址
     * @param filePath
     *            保存文件的路径,以'/结尾'
     * @param command
     *            运行OpenOffice的命令
     * @param isCached
     *            是否进行保存下载及生成的文件
     * @return
     */
    public static List<String> officeToBase64PdfImage(String url, String filePath,String relativePath, String command, boolean isCached) {
        if (url == null || url.equals("")) {
            throw new RuntimeException("URL不能为空!");
        }
        List<String> list = new ArrayList<String>();
        // 通过url获取文件名及后缀
        String fileName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        String fileSurfix = url.substring(url.lastIndexOf("."));

        File officeFile = new File(filePath + fileName + fileSurfix);
        // 如果文件不存在,则从url下载
        if (!officeFile.exists()) {
            // 如果目标路径不存在, 则新建该路径
            if (!officeFile.getParentFile().exists()) {
                officeFile.getParentFile().mkdirs();
            }
            // 将url的文件保存到本地
            HttpClient client = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            try {
                HttpResponse response = client.execute(httpget);
                InputStream pdfIn = response.getEntity().getContent();
                OutputStream fileOut = new FileOutputStream(officeFile);
                // 保存文件
                IOUtils.copy(pdfIn, fileOut);
                fileOut.close();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String pdfPath = filePath + fileName + ".pdf";
        File pdfFile = new File(filePath + fileName + ".pdf");
        // 如果PDF文件不存在,则进行ppt,word,excel转换PDF
        if (!pdfFile.exists()) {
            // 启动OpenOffice进程
//			Process pro = null;
            OpenOfficeConnection connection = null;
            try {
//				if (command == null || command.equals("")) {
//					command = "C:/Program Files (x86)/OpenOffice 4/program/soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"-nofirststartwizard";
//				}
//				pro = Runtime.getRuntime().exec(command);
                connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
                connection.connect();
                // office转换pdf
                DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                converter.convert(officeFile, pdfFile);
                connection.disconnect();
//				pro.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 将pdf转换成图片bytes[]
        try {
            Document document = new Document();
            document.setFile(pdfPath);
            for (int i = 0, numberOfPages = document.getNumberOfPages(); i < numberOfPages; i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                        org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, 0, 1);
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
                FileOutputStream out = new FileOutputStream(filePath+fileName+"_"+i+".png");
                list.add(relativePath+fileName+"_"+i+".png");
                ImageIO.write(image, "png", out);
//				list.add(Base64Encoder.encode(out.toByteArray()));
                image.flush();

            }
            document.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        // 如果不缓存,则删除文件及PDF
        if (!isCached) {
            officeFile.delete();
            pdfFile.delete();
        }
        return list;
    }

    public static List<String> readFilePath(String path) throws Exception{
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        if(file.isFile()){
            String[] templist = file.list();
            int size = templist.length;
            for (int i = 0; i <size; i++) {
                for(int j = 0 ; j <templist.length;j++ ){
                    String [] img = templist[j].split("_");
                    String str = img[img.length-1];
                    if(str.equals(i+1+".jpg")){
                        list.add(path+str);
                        break;
                    }
                }
            }
        }else if(file.isDirectory()){
            String[] templist = file.list();
            int size = templist.length;
            for (int i = 0; i <size; i++) {
                for(int j = 0 ; j <templist.length;j++ ){
                    String url = templist[j];
                    String [] img = url.split("_");
                    String str = img[img.length-1];
                    if(str.equals(i+1+".jpg")){
                        list.add(path+url);
                        break;
                    }
                }
            }
        }
        return list;
    }

    public static String getRoot(String path){
        if(path!="" || path !=null){
            if(path.contains("file:/")){
                path=path.replace("file:/", "");
            }
        }
        File file = new File(path);
        String str = file.getPath();
        if(file.getParentFile()!=null){
            return getRoot(file.getParentFile().getPath());
        }
        return str;
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> base64= officeToBase64PdfImage(
                "test.pdf",
                "/home/jackphang/temp/","/home/jackphang/temp/test2/", null,true);
        long end = System.currentTimeMillis();
        System.out.println(base64.get(0)+"");
        System.out.println("cost:" + (end - start) / 1000.0 + "s");

        List<String> imgUrlList = FileUtil.officeToBase64PdfImage(
                "dbSysFileinfo.getFilename()",
                "dbSysFileinfo.getFilepath()", "dbSysFileinfo.getFilepath()", null, true);
    }
}
