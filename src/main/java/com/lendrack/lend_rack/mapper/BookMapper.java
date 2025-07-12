package com.lendrack.lend_rack.mapper;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.persistance.entity.BookEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book EntityToDomain(BookEntity bookEntity) {
        Book book = new Book();
        BeanUtils.copyProperties(bookEntity, book);
        return book;
    }
}
