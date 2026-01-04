package com.lendrack.lend_rack.model.dto;

public record UpdateBookRequest(String title, String author, String publisher, Long category_id, Long language_id, Long collection_id, String description, int page_count) {
}
