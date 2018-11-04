package org.ecclesia.directory.service.converter;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.entity.OrganizationDto;
import org.mapstruct.Mapper;

/**
 * Converts between organization domain and dto objects.
 */
@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

  /**
   * Converts a organization domain object to a organization dto.
   * @param domain The organization domain object
   * @return The equivalent organization dto
   */
  public abstract OrganizationDto domainToDto(Organization domain);

  /**
   * Converts a organization dto to a organization domain object.
   * @param dto The organization dto object
   * @return The equivalent organization domain object
   */
  public abstract Organization dtoToDomain(OrganizationDto dto);

}
