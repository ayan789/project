package com.common.util;



import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public class Pdf2ImgUtil {
    public static void main(String[] args) {
        String filePath = "/Users/youth.s/Desktop/aaa.pdf/aaa.pdf";
        Document document = new Document();
        try {
            document.setFile(filePath);
            float scale = 1.0f; //缩放比例
            float rotation = 0f; //旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i,
                        GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX,
                        rotation, scale);
                RenderedImage rendImage = image;
                File file = new File("G:/BaiduYunDownload/icepdf_" + i + ".jpg");
                // 这里png作用是：格式是jpg但有png清晰度
                ImageIO.write(rendImage, "png", file);
                image.flush();
            }
            document.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("======================完成============================");
    }
}
