package com.lendrack.lend_rack.persistance.repository;

import com.lendrack.lend_rack.persistance.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
