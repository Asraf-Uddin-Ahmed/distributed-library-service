package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.Reservation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {
}
