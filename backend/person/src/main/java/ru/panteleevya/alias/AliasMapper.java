package ru.panteleevya.alias;

import org.springframework.stereotype.Service;

@Service
public class AliasMapper {
    public AliasEntity toPersonAliasEntity(Alias alias) {
        return new AliasEntity(
                null,
                alias.getPersonId(),
                alias.getAliasedPersonId(),
                alias.getAliasedSurname(),
                alias.getAliasedName()
        );
    }

    public Alias toPersonAlias(AliasEntity aliasEntity) {
        return new Alias(
                aliasEntity.getPersonId(),
                aliasEntity.getAliasedPersonId(),
                aliasEntity.getAliasedSurname(),
                aliasEntity.getAliasedName()
        );
    }
}
