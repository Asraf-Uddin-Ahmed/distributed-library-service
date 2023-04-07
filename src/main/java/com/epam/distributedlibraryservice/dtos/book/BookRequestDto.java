package com.epam.distributedlibraryservice.dtos.book;

import com.epam.distributedlibraryservice.validators.EmptyISBN;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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

    @EmptyISBN
    private String isbn;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;

    private String genres;

    @Min(value = 1)
    private Integer numberOfPages;

    private String tags;

    private String description;

}
