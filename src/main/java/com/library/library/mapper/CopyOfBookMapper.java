package com.library.library.mapper;

import com.library.library.domain.CopyOfBook;
import com.library.library.domain.CopyOfBookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyOfBookMapper {
    public CopyOfBook mapToCopyOfBook(final CopyOfBookDto copyOfBookDto){
        return new CopyOfBook(
                copyOfBookDto.getId(),
                copyOfBookDto.getTitle(),
                copyOfBookDto.getStatus()
        );
    }
    public  CopyOfBookDto mapToCopyOfBookDto(final CopyOfBook copyOfBook){
        return new CopyOfBookDto(
                copyOfBook.getId(),
                copyOfBook.getTitle(),
                copyOfBook.getStatus()
        );
    }
    public List<CopyOfBookDto> mapToCopyOfBookDtoList(final List<CopyOfBook> copyOfBookList){
        return copyOfBookList.stream()
                .map(this::mapToCopyOfBookDto)
                .toList();
    }
}
