package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private User user;
    private CopyOfBook copyOfBook;
    private LocalDate rentalDate;
    private LocalDate returnDate;
}
