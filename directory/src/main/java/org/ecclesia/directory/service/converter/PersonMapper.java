package org.ecclesia.directory.service.converter;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.PersonDto;
import org.ecclesia.directory.repository.OrganizationRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Converts between person domain and dto objects.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, OrganizationMapper.class})
public abstract class PersonMapper {

  @Autowired
  protected OrganizationRepository organizationRepository;

  /**
   * Converts a person domain object to a person dto.
   * @param domain The person domain object
   * @return The equivalent person dto
   */
  @Mapping(target = "organization",
      expression = "java(organizationRepository.findById(domain.getOrganization()))")
  public abstract PersonDto domainToDto(Person domain);

  /**
   * Converts a organization dto to a organization domain object.
   * @param dto The organization dto object
   * @return The equivalent organization domain object
   */
  @Mapping(target = "organization", source = "organization.id")
  public abstract Person dtoToDomain(PersonDto dto);

}
