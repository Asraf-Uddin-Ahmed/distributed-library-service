package com.epam.distributedlibraryservice.dtos.book;

import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.Loan;
import com.epam.distributedlibraryservice.entities.User;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan getLoanEntity(LoanRequestDto loanRequestDto, Book book, User user) {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setDueDate(loanRequestDto.getDueDate());
        loan.setUser(user);
        return loan;
    }

}
