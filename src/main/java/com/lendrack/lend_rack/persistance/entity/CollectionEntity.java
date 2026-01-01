package com.lendrack.lend_rack.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "collections")
@Entity
public class CollectionEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private String collection_name;

    @Setter
    private String location;

    @Setter
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @Setter
    private User created_by;
}