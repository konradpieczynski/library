package com.library.library.repository;

import com.library.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TitleRepository extends CrudRepository<Title, Long> {
    @Override
    List<Title> findAll();
    @Override
    Title save(Title title);
}
