package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.BookAudit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuditRepository extends PagingAndSortingRepository<BookAudit, Integer> {
}
