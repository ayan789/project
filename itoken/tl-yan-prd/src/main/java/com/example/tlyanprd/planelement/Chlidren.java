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
public class Chlidren {

    private int id;
    private String title;
    private String link;
    private String type;
    private List<Chlidren> chlidren;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setLink(String link) {
         this.link = link;
     }
     public String getLink() {
         return link;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setChlidren(List<Chlidren> chlidren) {
         this.chlidren = chlidren;
     }
     public List<Chlidren> getChlidren() {
         return chlidren;
     }

}