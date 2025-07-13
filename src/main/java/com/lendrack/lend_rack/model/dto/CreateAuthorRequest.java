package com.lendrack.lend_rack.model.dto;

import com.lendrack.lend_rack.model.utils.Gender;

import java.time.LocalDate;

public record CreateAuthorRequest(String fullName, String bio, LocalDate birthDate, Gender gender, String nationality) {
}
