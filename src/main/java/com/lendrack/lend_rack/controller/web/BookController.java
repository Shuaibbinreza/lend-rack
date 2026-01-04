package com.lendrack.lend_rack.controller.web;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.model.domain.Collection;
import com.lendrack.lend_rack.model.dto.CreateBookRequest;
import com.lendrack.lend_rack.service.BookService;
import com.lendrack.lend_rack.service.CollectionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final CollectionService collectionService;

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

    @GetMapping("manage-books/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new CreateBookRequest("", "", null, null, null, null, "", 0));
        List<Collection> collections = collectionService.getAllCollections();
        model.addAttribute("collections", collections);
        return "create_book";
    }

    @PostMapping("manage-books/create")
    public String createBook(@ModelAttribute("book") CreateBookRequest request, RedirectAttributes redirectAttributes) {
        bookService.create(request);
        redirectAttributes.addFlashAttribute("message", "Book created successfully");
        return "redirect:/books/manage-books/all";
    }
}
