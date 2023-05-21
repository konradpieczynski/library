package com.library.library.service;

import com.library.library.controller.NoFreeCopiesException;
import com.library.library.controller.RentalNotFoundException;
import com.library.library.domain.*;
import com.library.library.mapper.CopyOfBookMapper;
import com.library.library.mapper.RentalMapper;
import com.library.library.mapper.TitleMapper;
import com.library.library.mapper.UserMapper;
import com.library.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    @Autowired
    private final RentalRepository repository;
    @Autowired
    private final RentalMapper rentalMapper;
    @Autowired
    private final TitleMapper titleMapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final CopyOfBookDbService copyOfBookDbService;

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

    public Rental rentCopyOfBook(final TitleDto titleDto, final UserDto userDto) throws NoFreeCopiesException {
        Title title = titleMapper.mapToTitle(titleDto);
        User user = userMapper.mapToUser(userDto);
        CopyOfBook copyOfBook = copyOfBookDbService.getFreeCopyOfBook(title);
        copyOfBook.setStatus(Status.RENTED);
        Rental rental = new Rental(user,copyOfBook, LocalDate.now());
        saveRental(rental);
        return rental;
    }

    public Rental returnCopyOfBook(final RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        rental.getCopyOfBook().setStatus(Status.FREE);
        rental.setReturnDate(LocalDate.now());
        saveRental(rental);
        return rental;
    }
}
