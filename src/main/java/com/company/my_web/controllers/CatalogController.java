package com.company.my_web.controllers;

import com.company.my_web.models.Book;
import com.company.my_web.models.Comment;
import com.company.my_web.models.User;
import com.company.my_web.repo.BookRepository;
import com.company.my_web.repo.CommentRepository;
import com.company.my_web.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller //контроллер это програмный модуль, который получает запрос от пользователя и возвращает данные
public class CatalogController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        Iterable<Book> books = bookRepository.findAll(); //iterable - это массив данных, который получаем из бд
        model.addAttribute("books", books);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != "anonymousUser")
            model.addAttribute("menu", "blocks/user_header");
        else
            model.addAttribute("menu", "blocks/header");
        return "catalog";
    }

    @GetMapping("/search")
    public String searchBook(String title, Model model) {
        Book bookFromDb = bookRepository.findByTitle(title);
        model.addAttribute("books", bookFromDb);
        return "search";
    }

    @GetMapping("/{id}")
    public String book(Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        String[] url_array = url.split("/");
        long id = Long.parseLong(url_array[1]);
        Book bookFromDb = bookRepository.findById(id);
        Iterable<Comment> comments = commentRepository.findAllByBookId(id);
        model.addAttribute("books", bookFromDb);
        model.addAttribute("comments", comments);
        return "book_about";
    }

    @PostMapping("/{id}")
    public String addComment(@RequestParam String text, @RequestParam int grade, Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        String[] url_array = url.split("/");
        long id = Long.parseLong(url_array[1]);
        System.out.println(id);
        Book bookFromDb = bookRepository.findById(id);
        Iterable<Comment> comments = commentRepository.findAllByBookId(id);
        model.addAttribute("books", bookFromDb);
        model.addAttribute("comments", comments);
        if (text == null) {
            model.addAttribute("error", "Заполните поле.");
            return "book_about";
        }
        if (grade==0) {
            model.addAttribute("error", "Поставьте оценку :)");
            return "book_about";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userFromDb = userRepository.findByUsername(auth.getName());
        Comment comment = new Comment(grade, text, userFromDb, bookFromDb);
        commentRepository.save(comment);
        return "comment_success";
    }
}