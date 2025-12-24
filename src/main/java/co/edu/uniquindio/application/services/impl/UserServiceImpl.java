package co.edu.uniquindio.application.services.impl;

import co.edu.uniquindio.application.dto.CreateUserDTO;
import co.edu.uniquindio.application.dto.EditUserDTO;
import co.edu.uniquindio.application.dto.UserDTO;
import co.edu.uniquindio.application.exceptions.NotFoundException;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.UserMapper;
import co.edu.uniquindio.application.model.Role;
import co.edu.uniquindio.application.model.User;
import co.edu.uniquindio.application.model.UserStatus;
import co.edu.uniquindio.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    // Simulación de base de datos en memoria por ahora, ya que se utilizara docker
    private final Map<String, User> userStore = new ConcurrentHashMap<>();




    // ======================
    // CREATE
    // ======================
    @Override
    public void create(CreateUserDTO userDTO) {

        if (isEmailDuplicated(userDTO.email())) {
            throw new ValueConflictException("El correo electrónico ya está en uso.");
        }

        User user = userMapper.toEntity(userDTO);
        user.setPassword(encode(userDTO.password()));

        userStore.put(user.getId(), user);
    }

    // ======================
    // GET
    // ======================
    @Override
    public UserDTO get(String id) {

        User user = userStore.get(id);

        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return userMapper.toUserDTO(user);
    }

    // ======================
    // DELETE
    // ======================
    @Override
    public void delete(String id) throws Exception {

        User user = userStore.get(id);

        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        userStore.remove(id);
    }

    // ======================
    // LIST ALL
    // ======================
    @Override
    public List<UserDTO> listAll() {

        return userStore.values()
                .stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    // ======================
    // EDIT
    // ======================
    @Override
    public void edit(String id, EditUserDTO userDTO) throws Exception {

        User user = userStore.get(id);

        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        if (userDTO.name() != null) user.setName(userDTO.name());
        if (userDTO.phone() != null) user.setPhone(userDTO.phone());
        if (userDTO.photoUrl() != null) user.setPhotoUrl(userDTO.photoUrl());
        if (userDTO.dateBirth() != null) user.setDateBirth(userDTO.dateBirth());
    }

    // ======================
    // MÉTODOS DE APOYO
    // ======================
    private boolean isEmailDuplicated(String email) {
        return userStore.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    private String encode(String password) {
        return password; // luego se cambia por BCrypt
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPhotoUrl(),
                user.getDateBirth(),
                user.getRoles()
        );
    }
}

