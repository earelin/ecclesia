package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.service.OrganizationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationMapperImpl.class, OrganizationServiceImpl.class})
public class OrganizationMapperTest {

  @Autowired
  private OrganizationMapper organizationMapper;

  @Test
  public void testDomainToDto() {

  }

  @Test
  public void testDtoToDomain() {

  }

}
