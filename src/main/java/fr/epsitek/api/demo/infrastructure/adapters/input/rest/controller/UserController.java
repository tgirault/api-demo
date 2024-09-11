package fr.epsitek.api.demo.infrastructure.adapters.input.rest.controller;

import fr.epsitek.api.demo.domain.core.services.UserService;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response.UserResponse;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
        private final UserService userService;
        private final UserRestMapper userRestMapper;

        @GetMapping
        ResponseEntity<UserResponse> findUserByEmail(@RequestParam String email) {
            return userService.findByEmail(email)
                    .map(user -> ResponseEntity.ok(userRestMapper.toUserResponse(user)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
}
