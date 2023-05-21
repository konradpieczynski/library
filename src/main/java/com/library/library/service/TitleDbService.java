package com.library.library.service;

import com.library.library.controller.TitleExistException;
import com.library.library.controller.TitleNotFoundException;
import com.library.library.domain.Title;
import com.library.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService {
    private final TitleRepository repository;

    public List<Title> getAllTitles(){
        return repository.findAll();
    }
    public Title getTitle(Long id) throws TitleNotFoundException {
        return repository.findById(id).orElseThrow(TitleNotFoundException::new);
    }
    public Title saveTitle(final Title title) throws TitleExistException {
        try {
            return repository.save(title);
        }
        catch (ConstraintViolationException e){
            throw new TitleExistException();
        }
    }
    public void deleteTitle(Long id) throws TitleNotFoundException{
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new TitleNotFoundException();
        }
    }
}
