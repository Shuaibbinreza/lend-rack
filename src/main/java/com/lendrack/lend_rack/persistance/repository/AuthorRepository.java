package com.lendrack.lend_rack.persistance.repository;

import com.lendrack.lend_rack.persistance.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
