package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private CopyOfBook copyOfBook;

    @Column(name = "rental_date")
    private LocalDate rentalDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public Rental(User user, CopyOfBook copyOfBook, LocalDate rentalDate) {
        this.user = user;
        this.copyOfBook = copyOfBook;
        this.rentalDate = rentalDate;
    }
}
