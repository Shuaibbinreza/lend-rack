package com.lendrack.lend_rack.mapper;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.model.dto.CreateBookRequest;
import com.lendrack.lend_rack.model.dto.UpdateBookRequest;
import com.lendrack.lend_rack.persistance.entity.BookEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookMapper {
    public Book EntityToDomain(BookEntity bookEntity) {
        Book book = new Book();
        BeanUtils.copyProperties(bookEntity, book);
//        book.setId(bookEntity.getId());
//        book.setTitle(bookEntity.getTitle());
//        book.setDescription(bookEntity.getDescription());
        if (bookEntity.getAuthor() != null) {
            book.setAuthorName(bookEntity.getAuthor().getFullName());
        }
        return book;
    }

//    public BookEntity createRequestToEntity(CreateBookRequest request) {
//        BookEntity bookEntity = new BookEntity();
//        BeanUtils.copyProperties(request, bookEntity);
//        return bookEntity;
//    }

    public BookEntity createRequestToEntity(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(request.title());
        bookEntity.setPublisher_id(request.publisher_id());
        bookEntity.setCategory_id(request.category_id());
        bookEntity.setLanguage_id(request.language_id());
        bookEntity.setDescription(request.description());
        bookEntity.setPage_count(request.page_count());
        bookEntity.setCreatedAt(LocalDateTime.now());  // set current time

        return bookEntity;
    }


    public BookEntity udateRequestToEntity(UpdateBookRequest request, BookEntity bookEntity) {
        bookEntity.setDescription(request.description());
        bookEntity.setTitle(request.title());
//        bookEntity.setAuthor_id(request.author_id());
        bookEntity.setPublisher_id(request.publisher_id());
        return bookEntity;
    }
}
