package co.edu.uniquindio.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String photoUrl;
    private LocalDate dateBirth;
    private LocalDateTime createdAt;
    private List<Role> roles;
    private UserStatus status;
}
