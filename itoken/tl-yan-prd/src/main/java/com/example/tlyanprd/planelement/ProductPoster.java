/**
  * Copyright 2020 bejson.com 
  */
package com.example.tlyanprd.planelement;
import java.util.List;

/**
 * Auto-generated: 2020-05-26 13:50:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ProductPoster {

    private String title;
    private List<ImgList> imgList;
    private List<SharePlatform> sharePlatform;
    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setImgList(List<ImgList> imgList) {
         this.imgList = imgList;
     }
     public List<ImgList> getImgList() {
         return imgList;
     }

    public void setSharePlatform(List<SharePlatform> sharePlatform) {
         this.sharePlatform = sharePlatform;
     }
     public List<SharePlatform> getSharePlatform() {
         return sharePlatform;
     }

}