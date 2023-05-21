package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CopyOfBookDto {
    private Long id;
    private Title title;
    private Status status;
}
