package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.entity.LocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class LocationMapper {

  public abstract Location dtoToDomain(LocationDto dto);

  public abstract LocationDto domainToDto(Location domain);

}
