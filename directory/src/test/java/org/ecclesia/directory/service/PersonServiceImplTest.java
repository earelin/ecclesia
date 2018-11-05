package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.repository.PersonRepository;
import org.ecclesia.directory.service.converter.PersonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class PersonServiceImplTest {

  private PersonServiceImpl personService;

  @Mock
  private PersonRepository personRepository;

  @Mock
  private OrganizationService organizationService;

  @Mock
  private PersonMapper personMapper;

  @Before
  public void init() {
    personService = new PersonServiceImpl(personMapper, personRepository, organizationService);
  }

  @Test
  public void testDeleteById() throws EntityDoesNotExists {
    when(personRepository.existsById(1)).thenReturn(true);

    personService.deleteById(1);
    verify(personRepository).deleteById(1);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testDeleteByIdNotFound() throws EntityDoesNotExists {
    when(personRepository.existsById(1)).thenReturn(false);

    personService.deleteById(1);
    verify(personRepository, never()).deleteById(1);
  }

  @Test
  public void testFindAllByOrganization() {

  }
}
