package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record EditUserDTO(

        String name,
        String phone,

        @Email
        String email,

        String photoUrl,
        LocalDate dateBirth

) {}
