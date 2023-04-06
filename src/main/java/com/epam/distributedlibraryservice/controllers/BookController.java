package com.epam.distributedlibraryservice.controllers;

import com.epam.distributedlibraryservice.dtos.book.BookMapper;
import com.epam.distributedlibraryservice.dtos.book.BookRequestDto;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/books/new")
    public String showBookForm(Model model) {
        model.addAttribute("bookRequestDto", new BookRequestDto());
        return "book-form";
    }

    @PostMapping("/books/new")
    public String saveBook(@ModelAttribute("bookRequestDto") @Valid BookRequestDto bookRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }
        Book book = bookMapper.getBookEntity(bookRequestDto);
        bookService.save(book);
        return "redirect:/books/new";
    }


}
