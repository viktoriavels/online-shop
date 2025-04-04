package ru.vels.orders.auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.vels.orders.dto.AccessTokenResponse;

@RestController
public class TokenController {
    private final KeyCloakClient keyCloakClient;

    public TokenController(KeyCloakClient keyCloakClient) {
        this.keyCloakClient = keyCloakClient;
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity handleAuthNotFoundException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/validate")
    @PreAuthorize("hasRoles('USER', 'ADMIN')")
    public ResponseEntity validate() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponse> refresh(@RequestHeader("refresh-token") String refreshToken) {
        return ResponseEntity.ok(keyCloakClient.refreshToken(refreshToken));
    }
}