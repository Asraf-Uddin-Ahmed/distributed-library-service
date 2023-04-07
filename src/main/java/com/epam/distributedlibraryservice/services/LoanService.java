package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.Loan;

import javax.transaction.Transactional;
import java.util.List;

public interface LoanService {
    void save(Loan loan);

    Loan getById(int id);

    List<Loan> getAllReceivedLoans();

    List<Loan> getAllSentLoans();

    @Transactional
    void rejectLoanRequest(Loan loan);

    @Transactional
    void acceptLoanRequest(Loan loan);
}
