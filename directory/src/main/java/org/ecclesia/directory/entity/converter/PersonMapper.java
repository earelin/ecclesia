package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.PersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public abstract class PersonMapper {

  public abstract Person dtoToDomain(PersonDto dto);

  public abstract PersonDto domainToDto(Person person);

}
