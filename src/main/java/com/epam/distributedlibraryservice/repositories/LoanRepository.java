package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.constants.LoanStatus;
import com.epam.distributedlibraryservice.entities.Loan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {
    List<Loan> findByUserIdAndStatusAndDueDateAfter(Integer fromUserId, LoanStatus status, Date currentDate);

    List<Loan> findByFromUserIdAndStatusAndDueDateAfter(Integer fromUserId, LoanStatus status, Date currentDate);

    List<Loan> findByUserIdOrFromUserId(int userId, int fromUserId);

    List<Loan> findByUserId(int userId);
}
