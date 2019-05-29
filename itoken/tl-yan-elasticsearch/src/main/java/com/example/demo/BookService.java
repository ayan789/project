package com.example.demo;


import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {

    Optional<BookBean> findById(String id);

    BookBean save(BookBean blog);

    void delete();

    Page<BookBean> getBooksByName(String name);
}

