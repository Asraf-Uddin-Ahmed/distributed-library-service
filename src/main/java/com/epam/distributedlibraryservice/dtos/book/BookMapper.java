package com.epam.distributedlibraryservice.dtos.book;

import com.epam.distributedlibraryservice.dtos.home.UserRequestDto;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.User;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book getBookEntity(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setPublisher(bookRequestDto.getPublisher());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPublicationDate(bookRequestDto.getPublicationDate());
        book.setGenres(bookRequestDto.getGenres());
        book.setNumberOfPages(bookRequestDto.getNumberOfPages());
        book.setTags(bookRequestDto.getTags());
        book.setDescription(bookRequestDto.getDescription());
        return book;
    }
}
