package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.Loan;

public interface LoanService {
    void save(Loan loan);

    Loan getById(int id);
}
