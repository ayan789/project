package com.example.demo;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("blogService")
public class BookServiceImpl implements BookService {



    @Autowired
    @Qualifier("bookRepository")
    private BookRepository bookRepository;


    @Override
    public Optional<BookBean> findById(String id) {
        //CrudRepository中的方法
        return bookRepository.findById(id);
    }

    @Override
    public BookBean save(BookBean blog) {
        return bookRepository.save(blog);
    }


    @Override
    public Page<BookBean> getBooksByName(String name) {

        //查询条件
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        bqb.should(QueryBuilders.matchQuery("title",name).analyzer("ik_max_word"));
        bqb.should(QueryBuilders.matchQuery("author",name).analyzer("ik_max_word"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bqb)
                //  .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                //  .withSort(new FieldSortBuilder(orderField).order(SortOrder.DESC))
                .build();
        Page<BookBean> bookBeans = bookRepository.search(searchQuery);
        return bookBeans;
    }


}


