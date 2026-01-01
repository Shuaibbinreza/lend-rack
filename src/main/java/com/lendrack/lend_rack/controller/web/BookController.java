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

import com.lendrack.lend_rack.model.domain.Author;
import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.model.dto.CreateBookRequest;
import com.lendrack.lend_rack.service.AuthorService;
import com.lendrack.lend_rack.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

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
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.ASC, "name");
        List<Author> authors = authorService.findAllAuthors(pageable);
        model.addAttribute("authors", authors);
        model.addAttribute("book", new CreateBookRequest("", 0L, null, null, null, "", 0));
        return "create_book";
    }

    @PostMapping("manage-books/create")
    public String createBook(@ModelAttribute("book") CreateBookRequest request, RedirectAttributes redirectAttributes) {
        bookService.create(request);
        redirectAttributes.addFlashAttribute("message", "Book created successfully");
        return "redirect:/books/manage-books/all";
    }
}
