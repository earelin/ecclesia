package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.entity.OrganizationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

  public abstract Organization dtoToDomain(OrganizationDto dto);
  public abstract OrganizationDto domainToDto(Organization domain);

}
