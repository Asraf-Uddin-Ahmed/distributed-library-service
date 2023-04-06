package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.Book;

import java.util.Date;
import java.util.List;

public interface BookService {
    void save(Book book);

    Book getById(int id);

    List<Book> getBooksBy(String title, String author, String genres, Date publicationDate, int currentKeeperId);
}
