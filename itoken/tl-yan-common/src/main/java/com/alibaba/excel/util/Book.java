package com.alibaba.excel.util;

import lombok.Data;

@Data
public class Book {
    private String name;

    public String getBooks(int loop,String A1){

        return "books list";
    }

    public Boolean checkBook(int loop,String A1){
        System.out.println(A1);
        return true;
    }
}
