package com.epam.distributedlibraryservice.dtos.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
public class BookRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String publisher;

    private String isbn;

    @NotNull
    @PastOrPresent
    private Date publicationDate;

    private String genres;

    @Min(value = 1)
    private Integer numberOfPages;

    private String tags;

    private String description;

}
