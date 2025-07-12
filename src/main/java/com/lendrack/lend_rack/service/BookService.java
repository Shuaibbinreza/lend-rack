package com.lendrack.lend_rack.service;

import com.lendrack.lend_rack.mapper.BookMapper;
import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.persistance.entity.BookEntity;
import com.lendrack.lend_rack.persistance.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<Book> getAllBooks(Pageable pageable) {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities.stream().map(bookMapper::EntityToDomain).toList();
    }
}
