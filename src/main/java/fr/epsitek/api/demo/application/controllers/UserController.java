package fr.epsitek.api.demo.application.controllers;

import fr.epsitek.api.demo.application.dto.UserDto;
import fr.epsitek.api.demo.domain.services.UserService;
import fr.epsitek.api.demo.infrastructure.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
        private final UserService userService;
        private final UserConverter userConverter;

        @Autowired
        public UserController(UserService userService, UserConverter userConverter) {
            this.userService = userService;
            this.userConverter = userConverter;
        }

        @GetMapping
        ResponseEntity<UserDto> findUserByEmail(@RequestParam String email) {
            return userService.findByEmail(email)
                    .map(user -> ResponseEntity.ok(userConverter.toUserDto(user)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
}
