package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "copies")
public class CopyOfBook {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
