package com.lendrack.lend_rack.controller.web;

import com.lendrack.lend_rack.model.domain.Book;
import com.lendrack.lend_rack.persistance.entity.User;
import com.lendrack.lend_rack.service.BookService;
import com.lendrack.lend_rack.service.auth.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RootController {
    private final BookService bookService;

     @GetMapping("/books")
     public String index(Model model) {
         Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
         List<Book> books = bookService.getAllBooks(pageable);
         model.addAttribute("books", books);
         return "index";
     }

    // @GetMapping("manage-books/all")
    // public String manageBooks(Model model) {
    //     Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
    //     List<Book> books = bookService.getAllBooks(pageable);
    //     model.addAttribute("books", books);
    //     return "manage_books";
    // }

    private final UserService userService;

    // public RootController(UserService userService) {
    //     this.userService = userService;
    // }
    @GetMapping("/")
    public String home(@AuthenticationPrincipal Object principal, Model model) {
        String name = "User";
        String email = "";
        String picture = "";

        if (principal instanceof OAuth2User oAuth2User) {
            // Google login
            name = oAuth2User.getAttribute("name");
            email = oAuth2User.getAttribute("email");
            picture = oAuth2User.getAttribute("picture");

            // Get or create user in DB
            User user = userService.getOrCreateUser(name, email, picture);
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("picture", user.getPicture());
        } else if (principal instanceof UserDetails userDetails) {
            // Form login - userDetails is our custom User entity
            if (userDetails instanceof User user) {
                name = user.getName();
                email = user.getEmail();
                picture = user.getPicture();
                model.addAttribute("name", name);
                model.addAttribute("email", email);
                model.addAttribute("picture", picture);
            } else {
                // Fallback for other UserDetails implementations
                name = userDetails.getUsername();
                model.addAttribute("name", name);
                model.addAttribute("email", email);
                model.addAttribute("picture", picture);
            }
        }

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String confirmPassword,
                              Model model) {
        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        // Validate password length
        if (password.length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters long");
            return "signup";
        }

        try {
            userService.registerUser(name, email, password);
            model.addAttribute("success", "Registration successful! Please login with your credentials.");
            return "login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
}
