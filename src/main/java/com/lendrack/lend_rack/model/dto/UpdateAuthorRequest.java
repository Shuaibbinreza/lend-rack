package com.lendrack.lend_rack.model.dto;

import java.time.LocalDate;

public record UpdateAuthorRequest(String fullName, String bio, LocalDate birthDate, String nationality) {
}
