package co.edu.uniquindio.application.dto;

import co.edu.uniquindio.application.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserDTO(

        @NotBlank
        String name,

        @NotBlank
        String phone,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        String photoUrl,

        @NotNull
        LocalDate dateBirth
) {}
