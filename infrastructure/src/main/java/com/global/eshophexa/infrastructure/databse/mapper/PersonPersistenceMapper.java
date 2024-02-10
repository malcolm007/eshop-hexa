package com.global.eshophexa.infrastructure.databse.mapper;

import com.global.eshophexa.infrastructure.databse.entities.PersonEntity;
import com.global.eshophexa.models.PersonDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonPersistenceMapper {

    PersonDomain toPersonDomain(PersonEntity personEntity);
    PersonEntity toPersonEntity(PersonDomain personDomain);
}
