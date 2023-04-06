package com.epam.distributedlibraryservice.controllers;

import com.epam.distributedlibraryservice.dtos.book.BookMapper;
import com.epam.distributedlibraryservice.dtos.book.BookRequestDto;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/books/new")
    public String showNewBookForm(Model model) {
        model.addAttribute("bookRequestDto", new BookRequestDto());
        model.addAttribute("actionUrl", "/books/new");
        return "book-form";
    }

    @PostMapping("/books/new")
    public String createBook(@ModelAttribute("bookRequestDto") @Valid BookRequestDto bookRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }
        Book book = bookMapper.getBookEntity(bookRequestDto);
        bookService.save(book);
        return "redirect:/dashboard";
    }

    @GetMapping("/books/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("bookRequestDto", bookMapper.getBookRequestDto(book));
        model.addAttribute("actionUrl", "/books/" + id + "/edit");
        return "book-form";
    }

    @PostMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("bookRequestDto") @Valid BookRequestDto bookRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }
        Book book = bookMapper.getBookEntity(bookRequestDto);
        book.setId(id);
        bookService.save(book);
        return "redirect:/dashboard";
    }


}
