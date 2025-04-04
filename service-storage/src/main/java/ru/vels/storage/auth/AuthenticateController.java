package ru.vels.storage.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vels.storage.dto.AccessTokenResponse;
import ru.vels.storage.dto.AuthRequestDto;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    private final KeyCloakClient keyCloakClient;

    public AuthenticateController(KeyCloakClient keyCloakClient) {
        this.keyCloakClient = keyCloakClient;
    }

    @PostMapping
    public ResponseEntity<AccessTokenResponse> authenticate(@RequestBody AuthRequestDto request) {
        return ResponseEntity.ok(keyCloakClient.authenticate(request));
    }
}
