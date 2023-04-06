package com.epam.distributedlibraryservice.services.impl;

import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.repositories.BookRepository;
import com.epam.distributedlibraryservice.services.BookService;
import com.epam.distributedlibraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void save(Book book) {
        User user = userService.getCurrentUser();
        book.setContributor(user);
        book.setCurrentKeeper(user);
        bookRepository.save(book);
    }


}