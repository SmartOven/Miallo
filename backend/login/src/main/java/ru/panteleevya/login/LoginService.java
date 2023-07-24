package ru.panteleevya.login;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panteleevya.login.model.credentials.PersonCredentials;
import ru.panteleevya.login.model.credentials.PersonCredentialsEntity;
import ru.panteleevya.login.model.credentials.PersonCredentialsService;
import ru.panteleevya.login.model.token.PersonWebToken;
import ru.panteleevya.login.model.token.PersonWebTokenService;
import ru.panteleevya.login.model.token.TokenExpiredException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class LoginService {
    private final PersonCredentialsService personCredentialsService;
    private final PersonWebTokenService personWebTokenService;

    public LoginService(
            PersonCredentialsService personCredentialsService,
            PersonWebTokenService personWebTokenService
    ) {
        this.personCredentialsService = personCredentialsService;
        this.personWebTokenService = personWebTokenService;
    }

    @Transactional
    public PersonWebToken signUp(PersonCredentials personCredentials) {
        PersonCredentialsEntity credentialsEntity = personCredentialsService.create(personCredentials);
        String personId = credentialsEntity.getPersonId();
        log.info("Registered person: login={}, personId={}", credentialsEntity.getLogin(), personId);
        return personWebTokenService.createOrUpdateIfExists(personId);
    }

    // TODO Сделать редирект на страницу логина если токен истек
    public PersonWebToken signIn(String token) {
        PersonWebToken personWebToken = personWebTokenService.findNotExpiredByToken(token)
                .orElseThrow(() -> new TokenExpiredException(String.format("Token %s has expired", token)));
        log.info("Authorized person with personId={} by existing token", personWebToken.getPersonId());
        return personWebToken;
    }

    public PersonWebToken signIn(PersonCredentials personCredentials) {
        String login = personCredentials.getLogin();
        String password = personCredentials.getPassword();
        PersonCredentialsEntity personCredentialsEntity = personCredentialsService.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new NoSuchElementException(String.format("Person with login=%s doesn't exist or password is incorrect", login)));
        String personId = personCredentialsEntity.getPersonId();
        Optional<PersonWebToken> personWebTokenOptional = personWebTokenService.findNotExpiredByPersonId(personId);
        log.info("Authorized person with personId={} by credentials", personId);
        return personWebTokenOptional.orElseGet(() -> personWebTokenService.createOrUpdateIfExists(personId));
    }
}
