package com.lendrack.lend_rack.controller.web;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public String index(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        List<Book> books = bookService.getAllBooks(pageable);
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("manage-books/all")
    public String manageBooks(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        List<Book> books = bookService.getAllBooks(pageable);
        model.addAttribute("books", books);
        return "manage_books";
    }
}
