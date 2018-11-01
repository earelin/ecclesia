package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.PersonDto;
import org.ecclesia.directory.service.OrganizationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, OrganizationMapper.class})
public abstract class PersonMapper {

  @Autowired
  protected OrganizationService organizationService;

  @Mapping(target = "organization", source = "organization.id")
  public abstract Person dtoToDomain(PersonDto dto);

  @Mapping(target = "organization",
      expression = "java(organizationService.get(domain.getOrganization()))")
  public abstract PersonDto domainToDto(Person domain);

}
