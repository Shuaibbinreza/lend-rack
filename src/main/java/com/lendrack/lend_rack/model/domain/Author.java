package com.lendrack.lend_rack.model.domain;

import com.lendrack.lend_rack.model.utils.Gender;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    private String fullName;
    private String bio;
    private LocalDate birthDate;
    private Gender gender;
    private String nationality;
}
