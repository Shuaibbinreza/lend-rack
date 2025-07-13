package com.lendrack.lend_rack.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String slug;
    private Long author_id;
    private Long publisher_id;
    private LocalDateTime addedAt;
    private Long category_id;
    private Long language_id;
    private String description;
    private int page_count;

}
