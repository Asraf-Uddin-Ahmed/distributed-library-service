package com.epam.distributedlibraryservice.services.impl;

import com.epam.distributedlibraryservice.constants.LoanStatus;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.Loan;
import com.epam.distributedlibraryservice.repositories.BookRepository;
import com.epam.distributedlibraryservice.repositories.LoanRepository;
import com.epam.distributedlibraryservice.repositories.ReservationRepository;
import com.epam.distributedlibraryservice.services.LoanService;
import com.epam.distributedlibraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void save(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public Loan getById(int id) {
        return loanRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Loan> getAllReceivedLoans() {
        return loanRepository.findByUserIdAndStatusAndDueDateAfter(
                userService.getCurrentUser().getId(), LoanStatus.PENDING, new Date());
    }

    @Override
    public List<Loan> getAllSentLoans() {
        return loanRepository.findByFromUserIdAndStatusAndDueDateAfter(
                userService.getCurrentUser().getId(), LoanStatus.PENDING, new Date());
    }

    @Override
    @Transactional
    public void rejectLoanRequest(Loan loan) {
        loan.setLoanDate(new Date());
        loan.setStatus(LoanStatus.REJECTED);
        loanRepository.delete(loan);
    }

    @Override
    @Transactional
    public void acceptLoanRequest(Loan loan) {
        loan.setLoanDate(new Date());
        loan.setStatus(LoanStatus.ACCEPTED);
        loanRepository.save(loan);

        Book book = loan.getBook();
        book.setCurrentKeeper(loan.getUser());
        bookRepository.save(book);
    }
}