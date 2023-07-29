package ru.panteleevya.backend.login.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class PersonWebTokenService {
    private final PersonWebTokenRepository personWebTokenRepository;
    private final JwtProvider jwtProvider;
    private final long tokenExpirationTimeMillis;

    public PersonWebTokenService(
            PersonWebTokenRepository personWebTokenRepository,
            JwtProvider jwtProvider,
            @Value("${jwt.expirationTimeMillis}") long tokenExpirationTimeMillis
    ) {
        this.personWebTokenRepository = personWebTokenRepository;
        this.jwtProvider = jwtProvider;
        this.tokenExpirationTimeMillis = tokenExpirationTimeMillis;
    }

    public Optional<PersonWebToken> findNotExpiredByToken(String token) {
        Optional<PersonWebTokenEntity> entityOptional = personWebTokenRepository.findByToken(token);
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PersonWebTokenEntity personWebTokenEntity = entityOptional.get();
        if (isExpired(personWebTokenEntity)) {
            return Optional.empty();
        }
        return Optional.of(new PersonWebToken(personWebTokenEntity.getPersonId(), token));
    }

    public Optional<PersonWebToken> findNotExpiredByPersonId(String personId) {
        Optional<PersonWebTokenEntity> entityOptional = personWebTokenRepository.findByPersonId(personId);
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PersonWebTokenEntity personWebTokenEntity = entityOptional.get();
        if (isExpired(personWebTokenEntity)) {
            return Optional.empty();
        }
        return Optional.of(new PersonWebToken(personId, personWebTokenEntity.getToken()));
    }

    public PersonWebToken createOrUpdateIfExists(String personId) {
        long expirationAtMillis = Instant.now().toEpochMilli() + tokenExpirationTimeMillis;
        String token = jwtProvider.createJwtString(personId, expirationAtMillis);
        Long id = personWebTokenRepository.findByPersonId(personId)
                .map(PersonWebTokenEntity::getId)
                .orElse(null);
        personWebTokenRepository.save(new PersonWebTokenEntity(id, personId, token, expirationAtMillis));
        return new PersonWebToken(personId, token);
    }

    private boolean isExpired(PersonWebTokenEntity personWebTokenEntity) {
        return Instant.now().toEpochMilli() > personWebTokenEntity.getExpiringAtMillis();
    }
}
