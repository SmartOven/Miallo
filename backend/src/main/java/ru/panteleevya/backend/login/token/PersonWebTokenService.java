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
        Optional<PersonWebTokenDocument> entityOptional = personWebTokenRepository.findByToken(token);
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PersonWebTokenDocument personWebTokenDocument = entityOptional.get();
        if (isExpired(personWebTokenDocument)) {
            return Optional.empty();
        }
        return Optional.of(new PersonWebToken(personWebTokenDocument.getPersonId(), token));
    }

    public Optional<PersonWebToken> findNotExpiredByPersonId(String personId) {
        Optional<PersonWebTokenDocument> entityOptional = personWebTokenRepository.findByPersonId(personId);
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PersonWebTokenDocument personWebTokenDocument = entityOptional.get();
        if (isExpired(personWebTokenDocument)) {
            return Optional.empty();
        }
        return Optional.of(new PersonWebToken(personId, personWebTokenDocument.getToken()));
    }

    public PersonWebToken createOrUpdateIfExists(String personId) {
        long expirationAtMillis = Instant.now().toEpochMilli() + tokenExpirationTimeMillis;
        String token = jwtProvider.createJwtString(personId, expirationAtMillis);
        String id = personWebTokenRepository.findByPersonId(personId)
                .map(PersonWebTokenDocument::getId)
                .orElse(null);
        personWebTokenRepository.save(new PersonWebTokenDocument(id, personId, token, expirationAtMillis));
        return new PersonWebToken(personId, token);
    }

    private boolean isExpired(PersonWebTokenDocument personWebTokenDocument) {
        return Instant.now().toEpochMilli() > personWebTokenDocument.getExpiringAtMillis();
    }
}
