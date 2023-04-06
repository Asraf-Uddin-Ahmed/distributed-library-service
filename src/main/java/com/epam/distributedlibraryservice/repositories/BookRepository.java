package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE (:title IS NULL OR b.title LIKE %:title%) "
            + "AND (:author IS NULL OR b.author LIKE %:author%) "
            + "AND (:genres IS NULL OR b.genres LIKE %:genres%) "
            + "AND (:publicationDate IS NULL OR b.publicationDate = :publicationDate)"
            + "AND (:currentKeeperId IS NULL OR b.currentKeeper.id = :currentKeeperId)")
    List<Book> findBooksBy(String title, String author, String genres, Date publicationDate, int currentKeeperId);

}
