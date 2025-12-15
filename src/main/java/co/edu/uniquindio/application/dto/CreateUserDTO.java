package co.edu.uniquindio.application.dto;

import co.edu.uniquindio.application.model.Role;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateUserDTO(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        @NotBlank @Email @Length(max = 50) String email,
        @NotBlank @Length(min = 7, max = 20) String password,
        @Length(max = 300) String photoUrl,
        @NotNull @Past LocalDate dateBirth,
        @NotNull Role role
) {}
