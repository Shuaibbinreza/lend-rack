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
public class Collection {
    private Long id;
    private String collection_name;
    private String location;
    private LocalDateTime created_at;
    private Long created_by;
    private String createdByName;
}