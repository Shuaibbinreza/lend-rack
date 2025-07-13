package com.lendrack.lend_rack.model.dto;

public record UpdateBookRequest(String title, Long author_id, Long publisher_id, String description) {
}
