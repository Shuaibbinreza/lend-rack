package com.lendrack.lend_rack.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @Setter
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    @Column(updatable = false)
    @Setter
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private Long publisher_id;

    @Setter
    private Long category_id;

    @Setter
    private Long language_id;

    @Setter
    private String description;

    @Setter
    private int page_count;
}
