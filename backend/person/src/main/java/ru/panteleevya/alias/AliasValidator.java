package ru.panteleevya.alias;

import org.springframework.stereotype.Service;
import ru.panteleevya.person.PersonRepository;

@Service
public class AliasValidator {
    private final PersonRepository personRepository;
    private final AliasRepository aliasRepository;

    public AliasValidator(
            PersonRepository personRepository,
            AliasRepository aliasRepository
    ) {
        this.personRepository = personRepository;
        this.aliasRepository = aliasRepository;
    }

    public void validateCreateAlias(Alias alias) {
        String personId = alias.getPersonId();
        String aliasedPersonId = alias.getAliasedPersonId();
        validatePersonsExist(personId, aliasedPersonId);
        if (aliasRepository.existsByPersonIdAndAliasedPersonId(personId, aliasedPersonId)) {
            throw new IllegalArgumentException(String.format(
                    "Alias for personId=%s and aliasedPersonId=%s already exists",
                    personId,
                    aliasedPersonId
            ));
        }
    }

    public void validateUpdateAlias(Alias alias) {
        String personId = alias.getPersonId();
        String aliasedPersonId = alias.getAliasedPersonId();
        validatePersonsExist(personId, aliasedPersonId);
        if (!aliasRepository.existsByPersonIdAndAliasedPersonId(personId, aliasedPersonId)) {
            throw new IllegalArgumentException(String.format(
                    "Alias for personId=%s and aliasedPersonId=%s doesn't exists",
                    personId,
                    aliasedPersonId
            ));
        }
    }

    private void validatePersonsExist(String personId, String aliasedPersonId) {
        if (!personRepository.existsByPersonId(personId)) {
            throw new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", personId));
        }
        if (!personRepository.existsByPersonId(aliasedPersonId)) {
            throw new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", aliasedPersonId));
        }
    }
}
