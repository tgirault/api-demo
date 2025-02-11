package fr.epsitek.api.demo.application.web.controller;

import fr.epsitek.api.demo.application.web.mapper.UserRestMapper;
import fr.epsitek.api.demo.application.web.dto.request.UserRegistrationRequest;
import fr.epsitek.api.demo.application.web.dto.response.UserResponse;
import fr.epsitek.api.demo.domain.core.exception.UserAlreadyExistsException;
import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.domain.core.model.UserId;
import fr.epsitek.api.demo.domain.core.services.UserService;
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
        return userService.getById(new UserId(id))
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
    ResponseEntity<UserResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest) throws UserAlreadyExistsException {
        User registeredUser = userService.register(userRestMapper.toUser(userRegistrationRequest));
        return ResponseEntity.created(URI.create("/users/" + registeredUser.userId().id())).build();
    }
}
