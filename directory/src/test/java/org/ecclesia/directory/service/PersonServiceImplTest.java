package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.repository.PersonRepository;
import org.ecclesia.directory.service.converter.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class PersonServiceImplTest {

  private PersonServiceImpl personService;

  @Mock
  private PersonRepository personRepository;

  @Mock
  private OrganizationService organizationService;

  @Mock
  private PersonMapper personMapper;

  public void init() {
    personService = new PersonServiceImpl(personMapper, personRepository, organizationService);
  }

  @Test
  public void testDeleteById() {

  }

}
