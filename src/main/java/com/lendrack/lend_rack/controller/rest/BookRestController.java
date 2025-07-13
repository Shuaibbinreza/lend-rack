package com.lendrack.lend_rack.controller.rest;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.model.dto.CreateBookRequest;
import com.lendrack.lend_rack.model.dto.UpdateBookRequest;
import com.lendrack.lend_rack.persistance.entity.BookEntity;
import com.lendrack.lend_rack.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Resource", description = "API for managing posts")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/books")
public class BookRestController {
    private final BookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping
    public List<Book> getAllBooks(@ParameterObject Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public void createBook(CreateBookRequest createBookRequest) {
        bookService.create(createBookRequest);
    }

    @Operation(summary = "Get Book by ID")
    @GetMapping("{id}")
    public BookEntity getBookById(@PathVariable Long id) throws NotFoundException {
        BookEntity bookEntity = bookService.findEntityById(id);
        return bookEntity;
    }

    @Operation(summary = "Update a book by id")
    @PutMapping("{id}")
    public void updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest request) throws NotFoundException {
        bookService.update(id, request);
    }

    @Operation(summary = "Delete Book by id")
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) throws NotFoundException {
        bookService.delete(id);
    }
}
