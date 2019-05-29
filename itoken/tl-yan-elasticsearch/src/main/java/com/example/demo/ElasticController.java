package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ElasticController {

    @Autowired
    @Qualifier("bookRepository")
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @RequestMapping("/book/{id}")
    @ResponseBody
    public BookBean getBookById(@PathVariable String id){
        Optional<BookBean> opt =bookService.findById(id);
        BookBean book=opt.get();
        System.out.println(book);
        return book;
    }

    @RequestMapping("/save")
    @ResponseBody
    public void Save(){
        BookBean book1=new BookBean("1","ES入门教程","ayan","2018-10-01");
        bookService.save(book1);
        System.out.println("save");
    }

    @RequestMapping("/saveAll")
    @ResponseBody
    public void SaveAll(){
        List<BookBean> bookList = new ArrayList<>();
        BookBean book1=new BookBean("1","ES入门教程","ayan","2018-10-01");
        BookBean book2=new BookBean("2","美国留给伊拉克的是个烂摊子吗","ayan","2018-10-01");
        BookBean book3=new BookBean("3","公安部：各地校车将享最高路权","ayan","2018-10-01");
        BookBean book4=new BookBean("4","中韩渔警冲突调查：韩警平均每天扣1艘中国渔船","ayan","2018-10-01");
        BookBean book5=new BookBean("5","中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首","ayan","2018-10-01");
        BookBean book6=new BookBean("6","咖啡会看到就","谁都快结婚快卡中国宿舍看得见","2018-10-01");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        bookRepository.saveAll(bookList);
        System.out.println("saveAll");
    }

    @RequestMapping("/search")
    @ResponseBody
    public Page<BookBean>  Search(){
        Page<BookBean> bookBeans = bookService.getBooksByName("中国教程");
        return bookBeans;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void Delete(){
        bookService.delete();
        System.out.println("delete");
    }

}

