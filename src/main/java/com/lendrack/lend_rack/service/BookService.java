package com.lendrack.lend_rack.service;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.mapper.BookMapper;
import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.model.dto.CreateBookRequest;
import com.lendrack.lend_rack.model.dto.UpdateBookRequest;
import com.lendrack.lend_rack.persistance.entity.AuthorEntity;
import com.lendrack.lend_rack.persistance.entity.BookEntity;
import com.lendrack.lend_rack.persistance.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;

//    public List<Book> getAllBooks(Pageable pageable) {
//        List<BookEntity> bookEntities = bookRepository.findAll();
//        return bookEntities.stream().map(bookMapper::EntityToDomain).toList();
//    }
public List<Book> getAllBooks(Pageable pageable) {
    List<BookEntity> bookEntities = bookRepository.findAll(pageable).getContent();
    return bookEntities.stream().map(bookMapper::EntityToDomain).toList();
}


//    public Long create(CreateBookRequest createBookRequest) {
//        var bookToSave = bookMapper.createRequestToEntity(createBookRequest);
//        var savedBook = bookRepository.save(bookToSave);
//        return savedBook.getId();
//    }

    public Long create(CreateBookRequest createBookRequest) {
        if (createBookRequest.author_id() == null) {
            throw new IllegalArgumentException("author_id must not be null");
        }

        // Fetch AuthorEntity from DB
        AuthorEntity author = authorRepository.findById(createBookRequest.author_id())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Map request to BookEntity (without author)
        BookEntity bookToSave = bookMapper.createRequestToEntity(createBookRequest);

        // Manually set the author entity
        bookToSave.setAuthor(author);

        // Save book with author
        BookEntity savedBook = bookRepository.save(bookToSave);
        return savedBook.getId();
    }


    public Book getById(Long id) throws NotFoundException {
        var entity = bookRepository.findById(id).orElse(null);
        return bookMapper.EntityToDomain(entity);
    }

    public void update(Long id, UpdateBookRequest updateBookRequest) throws NotFoundException {
        BookEntity entity = this.findEntityById(id);
        BookEntity updateBookEntity = bookMapper.udateRequestToEntity(updateBookRequest, entity);
        bookRepository.save(updateBookEntity);
    }

    public BookEntity findEntityById(Long id) throws NotFoundException {
        var entity = bookRepository.findById(id).orElseThrow(()->new NotFoundException("Book not found"));
        return entity;
    }

    public void delete(Long id) throws NotFoundException {
        this.findEntityById(id);
        bookRepository.deleteById(id);
    }
}
