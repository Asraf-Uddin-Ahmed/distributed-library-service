package com.epam.distributedlibraryservice.services.impl;

import com.epam.distributedlibraryservice.entities.Loan;
import com.epam.distributedlibraryservice.repositories.LoanRepository;
import com.epam.distributedlibraryservice.services.LoanService;
import com.epam.distributedlibraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
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
        return loanRepository.findByUserIdAndLoanDateIsNull(userService.getCurrentUser().getId());
    }

    @Override
    public List<Loan> getAllSentLoans() {
        return loanRepository.findByBookCurrentKeeperIdAndLoanDateIsNull(userService.getCurrentUser().getId());
    }
}