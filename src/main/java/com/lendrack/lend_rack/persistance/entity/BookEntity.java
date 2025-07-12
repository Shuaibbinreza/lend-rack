package com.lendrack.lend_rack.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@Table(name = "books", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slug")
})
@Entity
public class BookEntity {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @Setter
    private String title;

    @Setter
    @Column(unique = true)
    private String slug;

    @Setter
    private Long author_id;

    @Setter
    private Long publisher_id;

    @Setter
    private LocalDateTime addedAt;

    @Setter
    private Long category_id;

    @Setter
    private Long language_id;

    @Setter
    private String description;

    @Setter
    private int page_count;
}
