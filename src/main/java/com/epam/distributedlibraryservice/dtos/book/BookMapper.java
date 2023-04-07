package com.epam.distributedlibraryservice.dtos.book;

import com.epam.distributedlibraryservice.entities.Book;
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

    public BookRequestDto getBookRequestDto(Book book) {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitle(book.getTitle());
        bookRequestDto.setAuthor(book.getAuthor());
        bookRequestDto.setPublisher(book.getPublisher());
        bookRequestDto.setIsbn(book.getIsbn());
        bookRequestDto.setPublicationDate(book.getPublicationDate());
        bookRequestDto.setGenres(book.getGenres());
        bookRequestDto.setNumberOfPages(book.getNumberOfPages());
        bookRequestDto.setTags(book.getTags());
        bookRequestDto.setDescription(book.getDescription());
        return bookRequestDto;
    }
}
