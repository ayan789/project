package com.common.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import net.coobird.thumbnailator.Thumbnails;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.sun.media.imageio.plugins.tiff.TIFFTag;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriterSpi;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;
public class ICEPdf {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
////		//1，把项目lib下jar包添加到环境中；2， 目录自己新建下，没有会报目录不存在//
//        converPdfToJPGYs("/Users/youth.s/Desktop/aaa.pdf","/Users/youth.s/Documents/pdfimg/","aaa",5.0f);
////        converPdfToImg("‪/Users/youth.s/Desktop/aaa.pdf","/Users/youth.s/Documents/pdfimg/","JYB_ec648000fef04180ab90bae010b502eb.pdf",3.0f);
//    }
//
//    /**
//     * jpg转tiff
//     * @param input  jpg图片 ，路径文件
//     * @param output 输出tiff图片 ，路径文件
//     * @throws IOException
//     */
//    public static void jpg2Tiff(String input, String output) throws IOException{
//        int dpi = 300;
//        Image image = ImageIO.read(new File(input));
//        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
//        bufferedImage.getGraphics().drawImage(image, 0, 0, null);
//        OutputStream os = new FileOutputStream(output);
//        TIFFEncodeParam param = new TIFFEncodeParam();
//        param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);// 设置压缩方式
//        TIFFField[] extras = new TIFFField[4];
//        extras[0] = new TIFFField(262, TIFFTag.TIFF_SHORT, 1, (Object) new short[] {0});
//        extras[1] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//        extras[2] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//        extras[3] = new TIFFField(296, TIFFTag.TIFF_SHORT, 1, (Object) new char[] {0});
//        param.setExtraFields(extras);
//        // 指定格式类型，tif 属于 TIFF 类型
//        ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os, param);
//        enc.encode(bufferedImage);
//        os.close();
//    }
//
//    /**
//     * 转换指定pdf文件为图片到指定的文件夹目录下
//     * @param pdfFilePath	需要转换的pdf文件路径
//     * @param imgPushPath	需要存放转换后的图片文件目录路径
//     * @param name			文件名
//     * @param imgScaling	图片缩放的比例
//     * @return	转换后图片的文件名集合
//     */
//    public static List<String> converPdfToImg(String pdfFilePath,String imgPushPath,String name,float imgScaling){
//        //图片路径
//        List<String> imgList = new ArrayList<String>();
//        //定义Document,用于转换图片
//        Document document = new Document();
//
//        try {
//            document.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        float rotation = 0f;
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document.getNumberOfPages(); i++) {
//            BufferedImage image = (BufferedImage)document.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, imgScaling);
//
//            BufferedImage imageCut = image.getSubimage(1, 5, image.getWidth()-2, image.getHeight()-5);//生产的jpg有黑边，所以裁剪一下
//            image.flush();
//            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//            ColorConvertOp op = new ColorConvertOp(cs, null);
//            BufferedImage imageOp = op.filter(imageCut, null);
//            imageCut.flush();
//
//            try {
//                System.out.println("/t capturing page " + i);
//                int j = i+1;
//                imgList.add(imgPushPath + name+"_"+j+".jpg");
//                File file = new File(imgPushPath + name+"_"+j+".jpg");
//                ImageIO.write(imageOp, "JPEG" , file);
//                //TODO
//                jpg2Tiff(imgPushPath + name+"_" + j + ".jpg", imgPushPath + name+"_" + j + ".tif");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            imageOp.flush();
//        }
//        // 清理document资源
//        document.dispose();
//
//        return imgList;
//    }
//
//
//    /**
//     * 转换指定pdf文件为图片到指定的文件夹目录下
//     * @param pdfFilePath	需要转换的pdf文件路径
//     * @param imgPushPath	需要存放转换后的图片文件目录路径
//     * @param name			文件名
//     * @param imgScaling	图片缩放的比例
//     * @return	转换后图片的文件名集合
//     */
//    public static Map<String, String> converPdfToJPG(String pdfFilePath,String imgPushPath,String name,float imgScaling){
//        //定义Document,用于转换图片
//        Document document = new Document();
//        //用来保存当前页码的页码
//        Map<String, String> map = new HashMap<String, String>();
//        File tempfile = new File(imgPushPath);
//        String[] temp = tempfile.list();
//        if(temp != null){
//            for (String str : temp) {
//                File file = new File(imgPushPath+str);
//                if(file.exists()){
//                    file.delete();
//                }
//            }
//        }
//
//        try {
//            document.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        // save page caputres to file.
//        float rotation = 0f;
//        int maxPages = document.getNumberOfPages();
//        // 循环把每页的数据转换成对应的图片
//        int size = document.getNumberOfPages();
//        for (int i = 0; i < size; i++) {
//            BufferedImage image = (BufferedImage)
//                    document.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, imgScaling);
//            try {
//                System.out.println("/t capturing page " + i);
//                int j = i+1;
//                File file = new File(imgPushPath + name+"_" + j + ".jpg");
//                if(!file.exists()){
//                    if(!file.getParentFile().isDirectory()){
//                        file.getParentFile().mkdirs();
//                    }else{
//                        file.createNewFile();
//                    }
//                }
//                ImageIO.write(image, "JPEG" , file);
//                map.put(String.valueOf(j), file.getAbsolutePath());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            image.flush();
//        }
//        // 清理document资源
//        document.dispose();
//        return map;
//    }
//
//    /**
//     * 转换指定pdf文件为图片到指定的文件夹目录下-带图片压缩
//     * @param pdfFilePath	需要转换的pdf文件路径
//     * @param imgPushPath	需要存放转换后的图片文件目录路径
//     * @param name			文件名
//     * @param imgScaling	图片缩放的比例
//     * @return	转换后图片的文件名集合
//     */
//    public static Map<String, String> converPdfToJPGYs(String pdfFilePath,String imgPushPath,String name,float imgScaling){
//        //定义Document,用于转换图片
//        Document document = new Document();
//        //用来保存当前页码的页码
//        Map<String, String> map = new HashMap<String, String>();
//        File tempfile = new File(imgPushPath);
//        String[] temp = tempfile.list();
//        if(temp != null){
//            for (String str : temp) {
//                File file = new File(imgPushPath+str);
//                if(file.exists()){
//                    file.delete();
//                }
//            }
//        }
//
//        try {
//            document.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        // save page caputres to file.
//        float rotation = 0f;
//        int maxPages = document.getNumberOfPages();
//        // 循环把每页的数据转换成对应的图片
//        int size = document.getNumberOfPages();
//        for (int i = 0; i < size; i++) {
//            BufferedImage image = (BufferedImage)
//                    document.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, imgScaling);
//            try {
//                System.out.println("/t capturing page " + i);
//                int j = i+1;
//                File file = new File(imgPushPath + name+"_" + j + ".jpg");
//                //File outFile = new File(imgPushPath + name+"_" + j + ".jpg");
//                if(!file.exists()){
//                    if(!file.getParentFile().isDirectory()){
//                        file.getParentFile().mkdirs();
//                    }else{
//                        file.createNewFile();
//                    }
//                }
//                //System.out.println("-----------------------------压缩图片处理 " + i);
//                ImageIO.write(image, "JPEG" , file);
//                Thumbnails.of(file).size(1500, 3000).toFile(file);
//
//                map.put(String.valueOf(j), file.getAbsolutePath());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            image.flush();
//        }
//        // 清理document资源
//        document.dispose();
//        return map;
//    }
//
//
//    public static String converPdfToJpg(String pdfFilePath,String imgPushPath,String name,float imgScaling){
//        //定义Document,用于转换图片
//        Document document2 = new Document();
//        //用来保存当前页码的页码
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        String output036Back="";
//
//        try {
//            document2.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        float rotation = 0f;
//        int maxPages = document2.getNumberOfPages();
//
//        BufferedImage bufferedImage = null;
//        BufferedImage image2 =null;
//        OutputStream os036=null;
//        OutputStream os=null;
//        //System.out.println("maxPages: "+maxPages);
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document2.getNumberOfPages(); i++) {
//            int j=0;
//            String output="";
//            try {
//                image2 = (BufferedImage)document2.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, 9.0f);
//
//                int w = 1664, h = 2342;
//                image2=setResizedByImg(image2,2467,3489);
//                j = i+1;
//                //生成的图片的全路径
//                output= imgPushPath+File.separator + name+"_" + j+".jpg";
//                if(!new File(imgPushPath).exists()){
//                    new File(imgPushPath).mkdirs();
//                }
//
//                bufferedImage = new BufferedImage(image2.getWidth(null), image2.getHeight(null), 12);
//                bufferedImage.getGraphics().drawImage(image2, 0, 0, null);
//                os = new FileOutputStream(output);
//
//                JPEGEncodeParam param = new JPEGEncodeParam();
//
//                //TIFFEncodeParam param = new TIFFEncodeParam();
//    			/*param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);// 设置压缩方式
//    			int dpi = 300;
//    			TIFFField[] extras = new TIFFField[4];
//    			extras[0] = new TIFFField(262, TIFFTag.TIFF_SHORT, 1, (Object) new short[] {0});
//    			extras[1] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//    			extras[2] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//    			extras[3] = new TIFFField(296, TIFFTag.TIFF_SHORT, 1, (Object) new char[] {0});
//    			param.setExtraFields(extras);*/
//
//                ImageEncoder enc = ImageCodec.createImageEncoder("JPEG", os, param);
//                enc.encode(bufferedImage);
//                os.close();
//
//                image2.flush();
//                bufferedImage=null;
//                image2=null;
//                enc=null;
//                //extras=null;
//                param=null;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally{
//                if(image2!=null){
//                    image2.flush();
//                }
//                if(os036!=null){
//                    try {
//                        os036.close();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//                if(os!=null){
//                    try {
//                        os.close();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        // 清理document资源
//        document2.dispose();
//        document2 = null;
//        map.put("pageCount", maxPages);
//
//        //System.gc();
//
//        return output036Back;
//    }
//
//    public static List<String> converPdfToJPGList(String pdfFilePath,String imgPushPath,String name,float imgScaling){
//        //定义Document,用于转换图片
//        Document document = new Document();
//        //用来保存当前页码的页码
//        List<String> strList = new ArrayList<String>();
//        try {
//            document.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        // save page caputres to file.
//        float rotation = 0f;
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document.getNumberOfPages(); i++) {
//            BufferedImage image = (BufferedImage)
//                    document.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, imgScaling);
//
//            //RenderedImage rendImage = image;
//            try {
//                int j = i+1;
//                File file = new File(imgPushPath + name+"_" + j + ".jpg");
//                ImageIO.write(image, "JPEG" , file);
//
//                System.out.println(imgPushPath + name+"_" + j + ".jpg");
//                strList.add(imgPushPath + name+"_" + j + ".jpg");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            image.flush();
//        }
//        // 清理document资源
//        document.dispose();
//
//        return strList;
//    }
//
//    /**
//     * 转换指定pdf文件为图片到指定的文件夹目录下-回执回销影像文件生成
//     * @param pdfFilePath	需要转换的pdf文件路径
//     * @param imgPushPath	需要存放转换后的图片文件目录路径
//     * @param name			文件名
//     * @param imgScaling	图片缩放的比例
//     * @return	转换后图片的文件名集合
//     */
//    public static Map<String, Integer> converPdfToTif(String pdfFilePath,String imgPushPath,String name,float imgScaling){
//        //定义Document,用于转换图片
//        Document document = new Document();
//        //用来保存当前页码的页码
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        try {
//            document.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        // save page caputres to file.
//        float rotation = 0f;
//        int maxPages = document.getNumberOfPages();
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document.getNumberOfPages(); i++) {
//            BufferedImage image = (BufferedImage)
//                    document.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, imgScaling);
//            RenderedImage rendImage = image;
//            try {
//                System.out.println("/t capturing page " + i);
//                File file = new File(imgPushPath + name+".jpg");
//                ImageIO.write(rendImage, "jpg" , file);
//                jpg2Tiff(imgPushPath + name+".jpg", imgPushPath + name+".tif");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            image.flush();
//        }
//        // 清理document资源
//        document.dispose();
//
//        map.put("pageCount", maxPages);
//        return map;
//    }
//
//
//
//
//
//    /**
//     * 转换指定pdf文件为图片到指定的文件夹目录下
//     * @param pdfFilePath	需要转换的pdf文件路径
//     * @param imgPushPath	需要存放转换后的图片文件目录路径
//     * @param name			文件名
//     * @param imgScaling	图片缩放的比例
//     * @return	转换后图片的文件名集合
//     */
//    public static String converPdfToTiff(String pdfFilePath,String imgPushPath,String name,String suffix, float imgScaling){
//        //定义Document,用于转换图片
//        Document document2 = new Document();
//        //用来保存当前页码的页码
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        String output036Back="";
//
//        try {
//            document2.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            document2.dispose();
//            ex.printStackTrace();
//        }
//        float rotation = 0f;
//        int maxPages = document2.getNumberOfPages();
//        //System.out.println("maxPages: "+maxPages);
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document2.getNumberOfPages(); i++) {
//
//            BufferedImage image2 = (BufferedImage)document2.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, 9.0f);
//
//            int w = 1664, h = 2342;
//            image2=setResizedByImg(image2,2467,3489);
//
//            int j=0;
//            String output="";
//            String output036="";
//
//            try {
//                if ("036".equals(suffix)) {
//                    j = i+3;
//                    output036= imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + j+"_" +suffix+"_"+"1.tif";
//                    output036Back=imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + "*"+"_" +suffix+"_"+"1.tif";
//                    File file1 = new File(imgPushPath+"tif"+File.separator+"image");
//                    if (!file1.exists()) {
//                        file1.mkdirs();
//                    }
//                }else if("053".equals(suffix)){
//                    j = i+2;
//                    output036= imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + j+"_" +suffix+"_"+"1.tif";
//                    output036Back=imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + "*"+"_" +suffix+"_"+"1.tif";
//                    File file1 = new File(imgPushPath+"tif"+File.separator+"image");
//                    if (!file1.exists()) {
//                        file1.mkdirs();
//                    }
//                }else{
//                    j = i+1;
//                }
//                //生成的tif图片的全路径
//                output= imgPushPath+File.separator + name+"_" + j+"_" +suffix+"_"+"1.tif";
//                if(!new File(imgPushPath).exists()){
//                    new File(imgPushPath).mkdirs();
//                }
//
//                BufferedImage bufferedImage = new BufferedImage(image2.getWidth(null), image2.getHeight(null), 12);
//                bufferedImage.getGraphics().drawImage(image2, 0, 0, null);
//                OutputStream os = new FileOutputStream(output);
//                TIFFEncodeParam param = new TIFFEncodeParam();
//                param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);// 设置压缩方式
//                int dpi = 300;
//                TIFFField[] extras = new TIFFField[4];
//                extras[0] = new TIFFField(262, TIFFTag.TIFF_SHORT, 1, (Object) new short[] {0});
//                extras[1] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//                extras[2] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//                extras[3] = new TIFFField(296, TIFFTag.TIFF_SHORT, 1, (Object) new char[] {0});
//                param.setExtraFields(extras);
//                if("036".equals(suffix)||"053".equals(suffix)){
//                    OutputStream os036 = new FileOutputStream(output036);
//                    ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os036, param);
//
//                    enc.encode(bufferedImage);
//                    os036.close();
//                    //image2.flush();
//                }
//
//                ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os, param);
//                enc.encode(bufferedImage);
//                os.close();
//                image2.flush();
//            } catch (IOException e) {
//                image2.flush();
//                document2.dispose();
//                e.printStackTrace();
//            }
//            //image2.flush();
//        }
//        // 清理document资源
//        document2.dispose();
//
//        map.put("pageCount", maxPages);
//        return output036Back;
//    }
//
//
//    /**泛华掌中宝生成PDF页码有区别
//     * @param pdfFilePath
//     * @param imgPushPath
//     * @param name
//     * @param suffix
//     * @param imgScaling
//     * @return
//     */
//    public static String converHfPdfToTiff(String pdfFilePath,String imgPushPath,String name,String suffix, float imgScaling){
//        //定义Document,用于转换图片
//        Document document2 = new Document();
//        //用来保存当前页码的页码
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        String output036Back="";
//
//        try {
//            document2.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        float rotation = 0f;
//        int maxPages = document2.getNumberOfPages();
//        //System.out.println("maxPages: "+maxPages);
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document2.getNumberOfPages(); i++) {
//
//            BufferedImage image2 = (BufferedImage)
//                    document2.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, 9.0f);
//
//            int w = 1664, h = 2342;
//            image2=setResizedByImg(image2,2467,3489);
//
//            int j=0;
//            String output="";
//            String output036="";
//
//            try {
//                if ("036".equals(suffix)) {
//                    j = i+1;
//                    output036= imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + j+"_" +suffix+"_"+"1.tif";
//                    output036Back=imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + "*"+"_" +suffix+"_"+"1.tif";
//                    File file1 = new File(imgPushPath+"tif"+File.separator+"image");
//                    if (!file1.exists()) {
//                        file1.mkdirs();
//                    }
//                }else if("053".equals(suffix)){
//                    j = i+2;
//                    output036= imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + j+"_" +suffix+"_"+"1.tif";
//                    output036Back=imgPushPath+"tif"+File.separator+"image"+File.separator + name+"_" + "*"+"_" +suffix+"_"+"1.tif";
//                    File file1 = new File(imgPushPath+"tif"+File.separator+"image");
//                    if (!file1.exists()) {
//                        file1.mkdirs();
//                    }
//                }else{
//                    j = i+1;
//                }
//                //生成的tif图片的全路径
//                output= imgPushPath+File.separator + name+"_" + j+"_" +suffix+"_"+"1.tif";
//                if(!new File(imgPushPath).exists()){
//                    new File(imgPushPath).mkdirs();
//                }
//
//                BufferedImage bufferedImage = new BufferedImage(image2.getWidth(null), image2.getHeight(null), 12);
//                bufferedImage.getGraphics().drawImage(image2, 0, 0, null);
//                OutputStream os = new FileOutputStream(output);
//                TIFFEncodeParam param = new TIFFEncodeParam();
//                param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);// 设置压缩方式
//                int dpi = 300;
//                TIFFField[] extras = new TIFFField[4];
//                extras[0] = new TIFFField(262, TIFFTag.TIFF_SHORT, 1, (Object) new short[] {0});
//                extras[1] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//                extras[2] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//                extras[3] = new TIFFField(296, TIFFTag.TIFF_SHORT, 1, (Object) new char[] {0});
//                param.setExtraFields(extras);
//                if("036".equals(suffix)||"053".equals(suffix)){
//                    OutputStream os036 = new FileOutputStream(output036);
//                    ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os036, param);
//
//                    enc.encode(bufferedImage);
//                    os036.close();
//                    image2.flush();
//                }
//
//                ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os, param);
//                enc.encode(bufferedImage);
//                os.close();
//                image2.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            image2.flush();
//        }
//        // 清理document资源
//        document2.dispose();
//
//        map.put("pageCount", maxPages);
//        return output036Back;
//    }
//
//    /**
//     * 转换指定pdf文件为图片到指定的文件夹目录下
//     * @param pdfFilePath	需要转换的pdf文件路径
//     * @param imgPushPath	需要存放转换后的图片文件目录路径
//     * @param name			文件名
//     * @param imgScaling	图片缩放的比例
//     * @return	转换后图片的文件名集合
//     */
//    public static int converProductPdfToTiff(String pdfFilePath,String imgPushPath,String name,String suffix, float imgScaling,int pages){
//        //定义Document,用于转换图片
//        Document document2 = new Document();
//        //用来保存当前页码的页码
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        try {
//            document2.setFile(pdfFilePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        float rotation = 0f;
//        int maxPages = document2.getNumberOfPages();
//
//        System.out.println("maxPages: "+maxPages);
//        // 循环把每页的数据转换成对应的图片
//        for (int i = 0; i < document2.getNumberOfPages(); i++) {
//            BufferedImage image2 = (BufferedImage)
//                    document2.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, 9.0f);
//            int w = 1664, h = 2342;
//            image2=setResizedByImg(image2,2467,3489);
//            String output="";
//            String output036="";
//            try {
//                pages = pages+1;
//                //生成的tif图片的全路径
//                output= imgPushPath+File.separator + name+"_" + pages+"_" +suffix+"_"+"1.tif";
//                if(!new File(imgPushPath).exists()){
//                    new File(imgPushPath).mkdirs();
//                }
//                BufferedImage bufferedImage = new BufferedImage(image2.getWidth(null), image2.getHeight(null), 12);
//                bufferedImage.getGraphics().drawImage(image2, 0, 0, null);
//                OutputStream os = new FileOutputStream(output);
//                TIFFEncodeParam param = new TIFFEncodeParam();
//                param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);// 设置压缩方式
//                int dpi = 300;
//                TIFFField[] extras = new TIFFField[4];
//                extras[0] = new TIFFField(262, TIFFTag.TIFF_SHORT, 1, (Object) new short[] {0});
//                extras[1] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//                extras[2] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][] { { (long) dpi, 1 }, { 0, 0 } });
//                extras[3] = new TIFFField(296, TIFFTag.TIFF_SHORT, 1, (Object) new char[] {0});
//                param.setExtraFields(extras);
//                if("036".equals(suffix)||"053".equals(suffix)){
//                    OutputStream os036 = new FileOutputStream(output036);
//                    ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os036, param);
//
//                    enc.encode(bufferedImage);
//                    os036.close();
//                    image2.flush();
//                }
//
//                ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", os, param);
//                enc.encode(bufferedImage);
//                os.close();
//                image2.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            image2.flush();
//        }
//        // 清理document资源
//        document2.dispose();
//        map.put("pageCount", maxPages);
//        return pages;
//    }
//    /**
//     * 设置图片宽和高
//     * @param originalImage	图像缓冲区
//     * @param scaledWidth	宽
//     * @param scaledHeight	高
//     * @return BufferedImage 图像缓冲区
//     */
//    static BufferedImage setResizedByImg(Image originalImage, int scaledWidth,int scaledHeight) {
//        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = scaledBI.createGraphics();
//        g.setComposite(AlphaComposite.Src);
//        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
//        g.dispose();
//        return scaledBI;
//    }
//
//    static void convertDensityByJPEGImg(File file) throws  IOException{
//		/*File infile = file;
//        File outfile = file;
//
//        ImageReader reader = ImageIO.getImageReadersByFormatName("jpeg").next();
//        reader.setInput(new FileImageInputStream(infile), true, false);
//		BufferedImage image = reader.read(0);
//
//		//分辨率是72像素/英寸时，A4纸的尺寸的图像的像素是595×842；
//		//分辨率是96像素/英寸时，A4纸的尺寸的图像的像素是794×1123；(默认)
//		//分辨率是120像素/英寸时，A4纸的尺寸的图像的像素是1487×2105；
//		//int w = 990, h = 1400;
//		int w = 1240, h = 1745;
//	    Image rescaled = image.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
//	    BufferedImage output = toBufferedImage(rescaled, BufferedImage.TYPE_INT_BGR);
//
// 		FileOutputStream fos = new FileOutputStream(outfile);
//        JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(fos);
//        JPEGEncodeParam jpegEncodeParam = jpegEncoder.getDefaultJPEGEncodeParam(output);
//        jpegEncodeParam.setDensityUnit(JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
//        jpegEncodeParam.setXDensity(300);
//        jpegEncodeParam.setYDensity(300);
//        jpegEncoder.encode(output, jpegEncodeParam);
//		fos.close();*/
//    }
//
//    public static BufferedImage toBufferedImage(Image image, int type) {
//        int w = image.getWidth(null);
//        int h = image.getHeight(null);
//        BufferedImage result = new BufferedImage(w, h, type);
//        Graphics2D g = result.createGraphics();
//        g.drawImage(image, 0, 0, null);
//        g.dispose();
//        return result;
//    }
//
//    public static void tiffToTiff(String resPath,String goalPath){
//        try {
//            TIFFImageWriterSpi tiffws=new TIFFImageWriterSpi();
//            ImageWriter writer=tiffws.createWriterInstance();
//            ImageWriteParam param=writer.getDefaultWriteParam();
//            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//            param.setCompressionType("CCITT T.6");
//            param.setCompressionQuality(0.8f);
//            ImageOutputStream ios=ImageIO.createImageOutputStream(new File(goalPath));
//            writer.setOutput(ios);
//            BufferedImage srcImage = ImageIO.read(new File(resPath));
//            writer.write(null,new IIOImage(srcImage, null, null), param);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



}
