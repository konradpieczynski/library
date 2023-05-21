package com.library.library.controller;

import com.library.library.domain.Title;
import com.library.library.domain.TitleDto;
import com.library.library.mapper.TitleMapper;
import com.library.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/titles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TitleController {
    @Autowired
    private final TitleDbService service;
    @Autowired
    private final TitleMapper titleMapper;

    //@RequestMapping(method = RequestMethod.GET, value = "")
    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitles() {
        List<Title> titles = service.getAllTitles();
        return ResponseEntity.ok(titleMapper.mapToTitleDtoList(titles));
    }
    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(titleMapper.mapToTitleDto(service.getTitle(titleId)));
    }
    @DeleteMapping(value = "{titleId}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long titleId) throws TitleNotFoundException{
        service.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<TitleDto> updateTitle(@RequestBody TitleDto titleDto) throws TitleExistException {
        Title title = titleMapper.mapToTitle(titleDto);
        Title savedTitle = service.saveTitle(title);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(savedTitle));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) throws TitleExistException {
        Title title = titleMapper.mapToTitle(titleDto);
        service.saveTitle(title);
        return ResponseEntity.ok().build();
    }
}
