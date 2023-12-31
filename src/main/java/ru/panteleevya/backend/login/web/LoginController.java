package ru.panteleevya.backend.login.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.panteleevya.backend.login.LoginService;
import ru.panteleevya.backend.login.credentials.PersonCredentials;
import ru.panteleevya.backend.login.token.PersonWebToken;
import ru.panteleevya.backend.login.token.TokenWrapper;

@RestController
@RequestMapping("/api/login/")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<PersonWebToken> signUp(@RequestBody PersonCredentials personCredentials) {
        return ResponseEntity.ok(loginService.signUp(personCredentials));
    }

    @PostMapping("/sign-in/token")
    public ResponseEntity<PersonWebToken> signIn(@RequestBody TokenWrapper tokenWrapper) {
        return ResponseEntity.ok(loginService.signIn(tokenWrapper.getToken()));
    }

    @PostMapping("/sign-in/credentials")
    public ResponseEntity<PersonWebToken> signIn(@RequestBody PersonCredentials personCredentials) {
        return ResponseEntity.ok(loginService.signIn(personCredentials));
    }
}
