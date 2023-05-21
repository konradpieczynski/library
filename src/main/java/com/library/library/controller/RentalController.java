package com.library.library.controller;

import com.library.library.domain.*;
import com.library.library.mapper.RentalMapper;
import com.library.library.service.CopyOfBookDbService;
import com.library.library.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rentals")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentalController {
    @Autowired
    private final RentalDbService service;
    @Autowired
    private final RentalMapper rentalMapper;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals() {
        List<Rental> rentals = service.getAllRentals();
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(rentals));
    }
    @GetMapping(value = "{rentalId}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(service.getRental(rentalId)));
    }
    @DeleteMapping(value = "{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long rentalId) throws RentalNotFoundException{
        service.deleteRental(rentalId);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Rental savedRental = service.saveRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(savedRental));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        service.saveRental(rental);
        return ResponseEntity.ok().build();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/rent")
    public ResponseEntity<RentalDto> rentCopyOfBook(@RequestBody TitleDto titleDto, @RequestBody UserDto userDto) throws NoFreeCopiesException {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto((service.rentCopyOfBook(titleDto, userDto))));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/return")
    public ResponseEntity<RentalDto> returnCopyOfBook(@RequestBody RentalDto rentalDto) {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto((service.returnCopyOfBook(rentalDto))));
    }
}
