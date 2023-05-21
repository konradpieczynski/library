package com.library.library.controller;

import com.library.library.domain.CopyOfBook;
import com.library.library.domain.CopyOfBookDto;
import com.library.library.mapper.CopyOfBookMapper;
import com.library.library.service.CopyOfBookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/CopiesOfBooks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CopyOfBookController {
    @Autowired
    private final CopyOfBookDbService service;
    @Autowired
    private final CopyOfBookMapper copyOfBookMapper;

    @GetMapping
    public ResponseEntity<List<CopyOfBookDto>> getCopyOfBooks() {
        List<CopyOfBook> copyOfBooks = service.getAllCopiesOfBooks();
        return ResponseEntity.ok(copyOfBookMapper.mapToCopyOfBookDtoList(copyOfBooks));
    }
    @GetMapping(value = "{copyOfBookId}")
    public ResponseEntity<CopyOfBookDto> getCopyOfBook(@PathVariable Long copyOfBookId) throws CopyOfBookNotFoundException {
        return ResponseEntity.ok(copyOfBookMapper.mapToCopyOfBookDto(service.getCopyOfBook(copyOfBookId)));
    }
    @DeleteMapping(value = "{copyOfBookId}")
    public ResponseEntity<Void> deleteCopyOfBook(@PathVariable Long copyOfBookId) throws CopyOfBookNotFoundException{
        service.deleteCopyOfBook(copyOfBookId);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<CopyOfBookDto> updateCopyOfBook(@RequestBody CopyOfBookDto copyOfBookDto) {
        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(copyOfBookDto);
        CopyOfBook savedCopyOfBook = service.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok(copyOfBookMapper.mapToCopyOfBookDto(savedCopyOfBook));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopyOfBook(@RequestBody CopyOfBookDto copyOfBookDto) {
        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(copyOfBookDto);
        service.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok().build();
    }
}
