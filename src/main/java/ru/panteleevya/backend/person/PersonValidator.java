package ru.panteleevya.backend.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.person.web.PersonInfo;
import ru.panteleevya.backend.util.NonNullValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class PersonValidator {
    private final PersonRepository personRepository;
    private final NonNullValidator nonNullValidator;

    public PersonValidator(
            PersonRepository personRepository,
            NonNullValidator nonNullValidator
    ) {
        this.personRepository = personRepository;
        this.nonNullValidator = nonNullValidator;
    }

    public void validateCreate(PersonDocument personDocument) {
        checkFieldsNotNull(personDocument);
        String personId = personDocument.getPersonId();
        String nickname = personDocument.getNickname();
        List<String> fields = new ArrayList<>();
        if (personRepository.existsByPersonId(personId)) {
            fields.add("personId=" + personId);
        }
        if (personRepository.existsByNickname(nickname)) {
            fields.add("nickname=" + nickname);
        }
        if (!fields.isEmpty()) {
            String errorMessage = String.format("Person with fields [%s] already exists", String.join(", ", fields));
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void validateUpdate(String personId, PersonInfo personInfo) {
        if (!personRepository.existsByPersonId(personId)) {
            validationError("personId", personId, false);
        }
        String nickname = personInfo.getNickname();
        if (personRepository.existsByNickname(nickname)) {
            validationError("nickname", nickname, true);
        }
    }

    private void checkFieldsNotNull(PersonDocument personDocument) {
        Map<Supplier<Object>, String> map = Map.of(
                personDocument::getPersonId, "personId",
                personDocument::getName, "name",
                personDocument::getNickname, "nickname"
        );
        nonNullValidator.validateNonNullFields(map);
    }

    private void validationError(String paramName, String paramValue, boolean exists) {
        String errorMessage = "Person with "
                + paramName + "=" + paramValue
                + (exists ? " already exists" : " doesn't exist");
        throw new IllegalArgumentException(errorMessage);
    }
}
