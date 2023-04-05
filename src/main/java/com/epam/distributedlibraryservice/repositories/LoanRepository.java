package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.Loan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {
}
