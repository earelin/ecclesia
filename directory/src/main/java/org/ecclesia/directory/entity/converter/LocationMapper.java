package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.entity.LocationDto;
import org.mapstruct.Mapper;

/**
 * Converts between location domain and dto objects.
 */
@Mapper(componentModel = "spring")
public abstract class LocationMapper {

  /**
   * Converts a location domain object to a location dto.
   * @param domain The location domain object
   * @return The equivalent location dto
   */
  public abstract LocationDto domainToDto(Location domain);

  /**
   * Converts a location dto to a location domain object.
   * @param dto The location dto object
   * @return The equivalent location domain object
   */
  public abstract Location dtoToDomain(LocationDto dto);

}
