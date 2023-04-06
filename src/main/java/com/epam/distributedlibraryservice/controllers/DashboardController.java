package com.epam.distributedlibraryservice.controllers;

import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private BookService bookService;

    // Controller method to handle GET request for the dashboard
    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam(required = false) String title,
                                @RequestParam(required = false) String author,
                                @RequestParam(required = false) String genres,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date publicationDate,
                                Model model) {
        // Call service method to filter books based on given criteria
        List<Book> filteredBooks = bookService.getBooksBy(title, author, genres, publicationDate);

        // Add filtered books to the model
        model.addAttribute("books", filteredBooks);

        // Return the view name for rendering the dashboard
        return "dashboard";
    }
}
