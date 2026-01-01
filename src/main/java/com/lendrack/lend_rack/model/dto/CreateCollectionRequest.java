package com.lendrack.lend_rack.model.dto;

public record CreateCollectionRequest(String collection_name, String location, Long created_by) {
}