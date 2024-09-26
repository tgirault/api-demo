package fr.epsitek.api.demo.infrastructure.adapters.input.rest.controller;

import fr.epsitek.api.demo.domain.core.exception.UserAlreadyExistsException;
import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.domain.core.services.UserService;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.request.UserRequest;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response.UserResponse;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRestMapper userRestMapper;

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(userRestMapper.toUserResponse(user)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    ResponseEntity<UserResponse> findUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(user -> ResponseEntity.ok(userRestMapper.toUserResponse(user)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
        User registeredUser = userService.register(userRestMapper.toUser(userRequest));
        return ResponseEntity.created(URI.create("/users/" + registeredUser.getId())).build();
    }
}
