package co.edu.uniquindio.application.dto;

import co.edu.uniquindio.application.model.Role;

import java.time.LocalDate;
import java.util.List;

public record UserDTO(
        String id,
        String name,
        String phone,
        String email,
        String photoUrl,
        LocalDate dateBirth,
        List<Role> role
) {}
