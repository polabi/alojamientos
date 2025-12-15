package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(
            @Valid @RequestBody CreateUserDTO userDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(false, "Usuario creado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(
            @PathVariable String id,
            @Valid @RequestBody EditUserDTO userDTO
    ) {
        return ResponseEntity
                .ok(new ResponseDTO<>(false, "Usuario actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(
            @PathVariable String id
    ) {
        return ResponseEntity
                .ok(new ResponseDTO<>(false, "Usuario eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> get(
            @PathVariable String id
    ) {
        UserDTO user = new UserDTO(id, "Carlos", "carlos@email.com", "", null);
        return ResponseEntity.ok(new ResponseDTO<>(false, user));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserDTO>>> listAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone
    ) {
        List<UserDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}
