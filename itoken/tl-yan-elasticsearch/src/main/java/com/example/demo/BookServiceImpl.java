package com.example.demo;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
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
    public void delete() {
        BookBean bookBean = new BookBean();
        bookBean.setId("1");
        bookRepository.delete(bookBean);
    }


    @Override
    public Page<BookBean> getBooksByName(String name) {

        //查询条件
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        //boost值默认为1 大于1的boost会增加该查询子句的相对权重
        bqb.should(QueryBuilders.matchQuery("title",name).analyzer("ik_max_word").boost(2));
        bqb.should(QueryBuilders.matchQuery("author",name).analyzer("ik_max_word").boost('3'));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bqb)
                .withFields("title","author")
                .withHighlightFields(new HighlightBuilder.Field("title"),new HighlightBuilder.Field("author"))
                //  .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                //  .withSort(new FieldSortBuilder(orderField).order(SortOrder.DESC))
                .build();

        Page<BookBean> bookBeans = bookRepository.search(searchQuery);

        return bookBeans;
    }



}


