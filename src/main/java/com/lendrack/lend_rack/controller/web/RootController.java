package com.lendrack.lend_rack.controller.web;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.persistance.repository.AuthorRepository;
import com.lendrack.lend_rack.service.AuthorService;
import com.lendrack.lend_rack.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RootController {
    private final BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        List<Book> books = bookService.getAllBooks(pageable);
        model.addAttribute("books", books);
        return "index";
    }
}
