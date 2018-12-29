package com.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ZipTool {
    private static final Logger logger = LoggerFactory.getLogger(ZipTool.class);
    /**
     * 压缩成ZIP文件
     * zipFileName 压缩后文件路径   inputFile待压缩文件夹目录
     **/
    public static void zip(String zipFileName, File inputFile) throws Exception {
        logger.info("压缩文件夹中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        //zip(out, inputFile, inputFile.getName(), out);
        zip(out, inputFile, inputFile.getName(), bo);
        bo.close();
        out.close(); // 输出流关闭
        logger.info("压缩文件夹完成");
    }

    /**
     * 功能:压缩多个文件成一个zip文件
     * @param srcfiles：源文件列表
     * @param zipfile：压缩后的文件
     * @author:jackphang
     * @date 2018-04-26
     */
    public static void zips(List<File> srcfiles, File zipfile){
        byte[] buf=new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));
            for(File srcfile:srcfiles){
                FileInputStream in=new FileInputStream(srcfile);
                out.putNextEntry(new ZipEntry(srcfile.getName()));
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            System.out.println("压缩完成.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void zip(ZipOutputStream out, File f, String base,BufferedOutputStream  bo) throws Exception { // 方法重载
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (fl.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
                //System.out.println(base + "/");
            }
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
            }
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
            //System.out.println("-------------base:"+base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bo.flush();
//			byte[] buf = new byte[1024*1024];
//			while ((b = in.read(buf)) != -1) {
//				bo.write(buf, 0, b);
//			}
            bi.close();
            in.close(); // 输入流关闭
        }
    }


    /*解压缩
     * zipFileName 源zip路径 ,unZipPath 输出路径（文件夹目录）
     * */
    public static String unZip(String zipFileName,String unZipPath){
        logger.info("开始解压ZIP文件： "+zipFileName);
        long startTime=System.currentTimeMillis();
        try {
            ZipInputStream zin=new ZipInputStream(new FileInputStream(zipFileName));//输入源zip路径
            BufferedInputStream bin=new BufferedInputStream(zin);
            File Fout=null;
            ZipEntry entry;
            try {
                while((entry = zin.getNextEntry())!=null ){
                    if(entry.isDirectory()){
                        //目录
                        Fout=new File(unZipPath,entry.getName());
                        if(!Fout.exists()){
                            Fout.mkdirs();
                        }
                    }else{
                        //文件
                        Fout=new File(unZipPath,entry.getName());
                        if(!Fout.exists()){
                            (new File(Fout.getParent())).mkdirs();
                        }
                        FileOutputStream out=new FileOutputStream(Fout);
                        BufferedOutputStream Bout=new BufferedOutputStream(out);
                        int b;
                        while((b=bin.read())!=-1){
                            Bout.write(b);
                        }
                        Bout.close();
                        out.close();
                    }
                    System.out.println(Fout+"解压成功");
                    return Fout.getPath();
                }
                bin.close();
                zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        logger.info("解压ZIP文件耗时： "+(endTime-startTime)+" ms");
        return null;
    }

    public static void main(String[] args) {
        try {
            //book.zip("d:\\zl.zip",new File("d:\\zl"));
            //book.unZip("d:\\Huatai2_1.5.zip","e:\\");
            ZipTool.unZip("/media/jackphang/500G4/项目文件/n22/吉祥/吉祥人寿销售支持平台接口请求文档 For JsonV1.0.pdf.zip","/home/jackphang/image/JXB/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
