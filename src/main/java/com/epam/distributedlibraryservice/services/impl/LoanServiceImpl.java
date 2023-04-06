package com.epam.distributedlibraryservice.services.impl;

import com.epam.distributedlibraryservice.entities.Loan;
import com.epam.distributedlibraryservice.repositories.LoanRepository;
import com.epam.distributedlibraryservice.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    @Transactional
    public void save(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public Loan getById(int id) {
        return loanRepository.findById(id).orElseThrow();
    }

}