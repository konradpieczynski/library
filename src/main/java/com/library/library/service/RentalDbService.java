package com.library.library.service;

import com.library.library.controller.RentalNotFoundException;
import com.library.library.domain.Rental;
import com.library.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentalRepository repository;

    public List<Rental> getAllRentals(){
        return repository.findAll();
    }
    public Rental getRental(Long id) throws RentalNotFoundException {
        return repository.findById(id).orElseThrow(RentalNotFoundException::new);
    }
    public Rental saveRental(final Rental rental) {
        return repository.save(rental);
    }
    public void deleteRental(Long id) throws RentalNotFoundException{
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new RentalNotFoundException();
        }
    }
}
