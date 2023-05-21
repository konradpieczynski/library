package com.library.library.service;

import com.library.library.controller.UserNotFoundException;
import com.library.library.domain.User;
import com.library.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {
    private final UserRepository repository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }
    public User getUser(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public User saveUser(final User user) {
        return repository.save(user);
    }
    public void deleteUser(Long id) throws UserNotFoundException{
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new UserNotFoundException();
        }
    }
}
