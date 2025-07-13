package com.lendrack.lend_rack.persistance.entity;

import com.lendrack.lend_rack.model.utils.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Getter
@Service
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @Setter
    private String fullName;

    @Setter
    private String bio;

    @Setter
    private LocalDate birthDate;

    @Setter
    private Gender gender;

    @Setter
    private String nationality;
}
