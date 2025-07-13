package com.lendrack.lend_rack.mapper;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.model.dto.CreateBookRequest;
import com.lendrack.lend_rack.model.dto.UpdateBookRequest;
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

    public BookEntity createRequestToEntity(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(request, bookEntity);
        return bookEntity;
    }

    public BookEntity udateRequestToEntity(UpdateBookRequest request, BookEntity bookEntity) {
        bookEntity.setDescription(request.description());
        bookEntity.setTitle(request.title());
        bookEntity.setAuthor_id(request.author_id());
        bookEntity.setPublisher_id(request.publisher_id());
        return bookEntity;
    }
}
