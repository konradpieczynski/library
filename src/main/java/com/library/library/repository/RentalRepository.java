package com.library.library.repository;

import com.library.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Override
    List<Rental> findAll();
    @Override
    Rental save(Rental rental);
}
