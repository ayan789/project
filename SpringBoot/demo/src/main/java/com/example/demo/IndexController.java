package com.example.demo;

import com.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class IndexController {
    @GetMapping("/home")
    public String index(Map<String,Object> map){
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 10; i++) {
            Book b = new Book("book"+i, "100"+i, "http://www.wendaoxueyuan.com/images/"+i+".jpg");
            books.add(b);
        }
        map.put("hello","你好 thymeleaf");
        map.put("even","odd");
        map.put("books",books);
        map.put("createTime",new Date());
        return "index";
    }
}
