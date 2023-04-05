package com.epam.distributedlibraryservice.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
public class Book {

    /*
    * Generate fields and getters/setters for the following create table statement:
CREATE TABLE book (
    id int NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    isbn VARCHAR(100),
    publication_date DATE,
    genres VARCHAR(100),
    number_of_pages INT,
    tags VARCHAR(100),
    description VARCHAR(1000),
    contributor_id INT NOT NULL,
    current_keeper_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (contributor_id) REFERENCES user(id),
    FOREIGN KEY (current_keeper_id) REFERENCES user(id)
);
    * COPILOT: failed to generate and partially correct to suggest code snippet
    * */

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "isbn")
    private String isbn;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "genres")
    private String genres;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "tags")
    private String tags;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_id", nullable = false)
    private User contributor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_keeper_id", nullable = false)
    private User currentKeeper;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<BookAudit> bookAudits;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Loan> loans;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getContributor() {
        return contributor;
    }

    public void setContributor(User contributor) {
        this.contributor = contributor;
    }

    public User getCurrentKeeper() {
        return currentKeeper;
    }

    public void setCurrentKeeper(User currentKeeper) {
        this.currentKeeper = currentKeeper;
    }

    public Set<BookAudit> getBookAudits() {
        return bookAudits;
    }

    public void setBookAudits(Set<BookAudit> bookAudits) {
        this.bookAudits = bookAudits;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
