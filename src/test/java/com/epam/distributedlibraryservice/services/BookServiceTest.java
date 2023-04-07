package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.repositories.BookRepository;
import com.epam.distributedlibraryservice.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserService userService;

    @Test
    void whenBookSave_thenContributorAndCurrentKeeperShouldBeTheCurrentUser() {
        // Arrange
        Book book = new Book();
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        // Act
        bookService.save(book);

        // Assert
        Mockito.verify(bookRepository, Mockito.times(1)).save(book);
        assertEquals(user, book.getContributor());
        assertEquals(user, book.getCurrentKeeper());
    }

    @Test
    void whenGetById_thenBookShouldBeFound() {
        // Arrange
        int id = 1;
        Book expectedBook = new Book();
        when(bookRepository.findById(id)).thenReturn(Optional.of(expectedBook));

        // Act
        Book actualBook = bookService.getById(id);

        // Assert
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void whenGetByIdNotExists_thenNoSuchElementExceptionThrown() {
        // Arrange
        int id = 1;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThatThrownBy(() -> bookService.getById(id)).isInstanceOf(NoSuchElementException.class);

    }

    @Test
    void whenGetBooksByFilterCriteria_thenListOfBooksShouldBeFound() {
        // Arrange
        String title = "Book Title";
        String author = "John Doe";
        String genres = "Fiction";
        Date publicationDate = new Date();
        int currentKeeperId = 1;
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());
        when(bookRepository.findBooksBy(title, author, genres, publicationDate, currentKeeperId)).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.getBooksBy(title, author, genres, publicationDate, currentKeeperId);

        // Assert
        assertEquals(expectedBooks, actualBooks);
    }

}
