package com.lendrack.lend_rack.persistance.repository;

import com.lendrack.lend_rack.persistance.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

}