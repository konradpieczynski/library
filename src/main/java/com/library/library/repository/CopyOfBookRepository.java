package com.library.library.repository;

import com.library.library.domain.CopyOfBook;
import com.library.library.domain.Status;
import com.library.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CopyOfBookRepository extends CrudRepository<CopyOfBook, Long> {
    @Override
    List<CopyOfBook> findAll();
    @Override
    CopyOfBook save(CopyOfBook copyOfBook);
    List<CopyOfBook> findByTitleAndStatus(Title title, Status status);
}
