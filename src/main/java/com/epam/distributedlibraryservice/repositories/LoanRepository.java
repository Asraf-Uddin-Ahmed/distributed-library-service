package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.Loan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {
    List<Loan> findByUserIdAndLoanDateIsNull(Integer userId);
    List<Loan> findByBookCurrentKeeperIdAndLoanDateIsNull(Integer currentKeeperId);
}
