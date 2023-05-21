package com.library.library.service;

import com.library.library.controller.CopyOfBookNotFoundException;
import com.library.library.domain.CopyOfBook;
import com.library.library.repository.CopyOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyOfBookDbService {
    private final CopyOfBookRepository repository;
    public List<CopyOfBook> getAllCopiesOfBooks(){
//        return Streamable.of(repository.findAll()).toList();
        return repository.findAll();
    }
    public CopyOfBook getCopyOfBook(Long id) throws CopyOfBookNotFoundException {
        return repository.findById(id).orElseThrow(CopyOfBookNotFoundException::new);
    }
    public CopyOfBook saveCopyOfBook(final CopyOfBook copyOfBook){
       return repository.save(copyOfBook);
    }
    public void deleteCopyOfBook(Long id) throws CopyOfBookNotFoundException{
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new CopyOfBookNotFoundException();
        }
    }
}
