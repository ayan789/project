package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "book8", type = "_doc")
public class BookBean implements Serializable {
    @Id
    private String id;
    @Field(searchAnalyzer="ik_max_word",analyzer="ik_max_word",store=true, type= FieldType.Text)
    private String title;
    @Field(searchAnalyzer="ik_max_word",analyzer="ik_max_word",store=true, type= FieldType.Text)
    private String author;
    private String postDate;

    public BookBean(){}

    public BookBean(String id, String title, String author, String postDate){
        this.id=id;
        this.title=title;
        this.author=author;
        this.postDate=postDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", postDate='" + postDate + '\'' +
                '}';
    }
}

