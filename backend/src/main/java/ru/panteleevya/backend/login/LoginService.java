package ru.panteleevya.backend.login;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panteleevya.backend.login.credentials.PersonCredentials;
import ru.panteleevya.backend.login.credentials.PersonCredentialsEntity;
import ru.panteleevya.backend.login.credentials.PersonCredentialsService;
import ru.panteleevya.backend.login.token.PersonWebToken;
import ru.panteleevya.backend.login.token.PersonWebTokenService;
import ru.panteleevya.backend.login.token.TokenExpiredException;
import ru.panteleevya.backend.person.PersonService;
import ru.panteleevya.backend.person.web.PersonDto;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class LoginService {
    private final PersonService personService;
    private final PersonCredentialsService personCredentialsService;
    private final PersonWebTokenService personWebTokenService;

    public LoginService(
            PersonService personService,
            PersonCredentialsService personCredentialsService,
            PersonWebTokenService personWebTokenService
    ) {
        this.personService = personService;
        this.personCredentialsService = personCredentialsService;
        this.personWebTokenService = personWebTokenService;
    }

    @Transactional
    public PersonWebToken signUp(PersonCredentials personCredentials) {
        PersonCredentialsEntity credentialsEntity = personCredentialsService.create(personCredentials);
        String personId = credentialsEntity.getPersonId();
        String login = credentialsEntity.getLogin();
        log.info("Registered person: login={}, personId={}", login, personId);
        PersonDto personDto = new PersonDto();
        personDto.setNickname(login);
        personDto.setName(login);
        personService.create(personDto);
        return personWebTokenService.createOrUpdateIfExists(personId);
    }

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
