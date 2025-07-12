package com.lendrack.lend_rack.model.dto;

public record CreateBookRequest(String title, Long author_id, Long publisher_id, Long category_id, Long language_id,

                                String description, int page_count) {
}
