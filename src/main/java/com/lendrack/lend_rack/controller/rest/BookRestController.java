package com.lendrack.lend_rack.controller.rest;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Post Resource", description = "API for managing posts")
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
}
