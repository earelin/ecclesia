package org.earelin.ecclesia.unit.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.repository.GroupRepository;
import org.earelin.ecclesia.service.GroupService;
import org.earelin.ecclesia.service.GroupServiceImpl;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * GroupServiceImpl unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {

    private OrganizationDto organization;
    private final Mapper mapper = new DozerBeanMapper();
    private GroupService instance;
    
    @Mock
    private OrganizationService organizationService;
    
    @Mock
    private GroupRepository repository;
    
    @Before
    public void init() {
        organization = new OrganizationDto();
        organization.setId(1);
        
        instance = new GroupServiceImpl(repository, organizationService, mapper);
    }
    
    @Test
    public void createNewGroup() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Group group = (Group) args[0];
                group.setId(1);
                return null;
            } 
        }).when(repository).add(any(Group.class));
        
        when(organizationService.exists(1)).thenReturn(true);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);

        Date beforeInsert = new Date();
        instance.add(group);
        Date afterInsert = new Date();
        
        assertNotEquals("Created group id should not be 0", 0, group.getId());
        assertTrue("Created group created field should have current date", 
                group.getCreated().compareTo(beforeInsert) >= 0
                && group.getCreated().compareTo(afterInsert) <= 0);
        assertEquals("Created group updated field should have the same value as created field", 
                group.getCreated(), group.getUpdated());
        assertEquals("Group should belong to defined Organization",
                organization, group.getOrganization());
        verify(repository).add(any(Group.class));
    }   
    
    @Test
    public void createNewGroupWithParent() {        
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Group group = (Group) args[0];
                group.setId(2);
                return null;
            } 
        }).when(repository).add(any(Group.class));
        
        GroupDto parent = new GroupDto();
        parent.setId(1);
        parent.setOrganization(organization);
        
        Organization organizationEntity = new Organization();
        organizationEntity.setId(1);
        
        Group parentGroupEntity = new Group();
        parentGroupEntity.setId(1);
        parentGroupEntity.setOrganization(organizationEntity);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setParent(parent);
        
        when(organizationService.exists(1)).thenReturn(true);
        when(repository.get(1)).thenReturn(parentGroupEntity);
        
        instance.add(group);
        
        verify(repository).add(any(Group.class));
    }
    
    @Test(expected = ValidationException.class)
    public void anExistingGroupShouldNotBeCreated() {
        GroupDto group = new GroupDto();
        group.setId(1);
        
        instance.add(group);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupShouldBelongToAnOrganization() {
        GroupDto group = new GroupDto();
        instance.add(group);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void newGroupShouldBelongToAnExistingOrganization() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        
        instance.add(group);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupParentShouldExists() {
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setParent(parent);
        
        when(organizationService.exists(1)).thenReturn(true);
        
        instance.add(group);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupParentShouldBelongToTheSameOrganization() {
        OrganizationDto parentOrganization = new OrganizationDto();
        parentOrganization.setId(2);
        
        GroupDto parent = new GroupDto();
        parent.setId(1);
        parent.setOrganization(parentOrganization);
        
        Organization parentOrganizationEntity = new Organization();
        parentOrganizationEntity.setId(2);
        
        Group parentGroupEntity = new Group();
        parentGroupEntity.setId(1);
        parentGroupEntity.setOrganization(parentOrganizationEntity);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setParent(parent);
        
        when(organizationService.exists(1)).thenReturn(true);
        when(repository.get(1)).thenReturn(parentGroupEntity);
        
        instance.add(group);
        
        verify(repository).add(any(Group.class));
    }
    
    @Test
    public void updateExistingGroup() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setId(1);        
        
        when(repository.get(1)).thenReturn(new Group());
        when(organizationService.exists(1)).thenReturn(true);
        
        Date beforeUpdate = new Date();
        instance.update(group); 
        Date afterUpdate = new Date();        
        
        verify(repository).update(any(Group.class));
        assertTrue("Updated group updated field should have current date", 
                group.getUpdated().compareTo(beforeUpdate) >= 0
                && group.getUpdated().compareTo(afterUpdate) <= 0);
    }
    
    @Test
    public void updateExistingGroupWithParent() {
        GroupDto parent = new GroupDto();
        parent.setId(1);
        parent.setOrganization(organization);
        
        Organization organizationEntity = new Organization();
        organizationEntity.setId(1);
        
        Group parentEntity = new Group();
        parentEntity.setId(1);
        parentEntity.setOrganization(organizationEntity);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setId(2);                         
        group.setParent(parent);
        
        Group groupEntity = new Group();
        groupEntity.setId(2);
        
        when(repository.get(1)).thenReturn(parentEntity);
        when(repository.get(2)).thenReturn(groupEntity);
        when(organizationService.exists(1)).thenReturn(true);
        
        instance.update(group);
        
        verify(repository).update(groupEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateNotExistingGroup() {
        GroupDto group = new GroupDto();
        group.setId(1);
        group.setOrganization(organization);

        instance.update(group);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupShouldBelongToAnOrganization() {
        GroupDto group = new GroupDto();
        group.setId(1);                
        
        Group groupEntity = new Group();
        groupEntity.setId(1);
        
        when(repository.get(1)).thenReturn(groupEntity);
 
        instance.update(group);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatedGroupShouldBelongToAnExistingOrganization() {
        GroupDto group = new GroupDto();
        group.setId(1);
        group.setOrganization(organization);
        
        Group groupEntity = new Group();
        groupEntity.setId(1);
        
        when(repository.get(1)).thenReturn(groupEntity);
 
        instance.update(group);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupParentShouldExists() {
        GroupDto parent = new GroupDto();
        parent.setId(1);
        
        GroupDto group = new GroupDto();
        group.setId(2);
        group.setOrganization(organization);
        group.setParent(parent);
        
        Group groupEntity = new Group();
        groupEntity.setId(2);
        
        when(repository.get(2)).thenReturn(groupEntity);
        when(organizationService.exists(1)).thenReturn(true);
 
        instance.update(group);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupParentShouldBelongToTheSameOrganization() {
        GroupDto parent = new GroupDto();
        parent.setId(1);
        parent.setOrganization(organization);
        
        Organization parentOrganizationEntity = new Organization();
        parentOrganizationEntity.setId(2);
        
        Group parentEntity = new Group();
        parentEntity.setId(1);
        parentEntity.setOrganization(parentOrganizationEntity);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setId(2);                         
        group.setParent(parent);
        
        Group groupEntity = new Group();
        groupEntity.setId(2);
        
        when(repository.get(1)).thenReturn(parentEntity);
        when(repository.get(2)).thenReturn(groupEntity);
        when(organizationService.exists(1)).thenReturn(true);
        
        instance.update(group);
    }
    
    @Test
    public void removeExistingGroup()
            throws EntityNotFoundException, ValidationException {
        GroupDto group = new GroupDto();
        group.setId(1);
        
        Group groupEntity = new Group();
        
        when(repository.get(1)).thenReturn(groupEntity);
        
        instance.remove(1);
        
        verify(repository).remove(groupEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingGroup() throws EntityNotFoundException {
        instance.remove(1);
    }
    
    @Test
    public void getExistingGroup()
            throws EntityNotFoundException, ValidationException {
        when(repository.get(1)).thenReturn(new Group());
        
        instance.get(1);        
        
        verify(repository).get(1);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingGroup() {
        instance.get(1);
    }
    
    @Test
    public void checkThanAGroupExists() {
        when(repository.get(1)).thenReturn(new Group());
        
        instance.exists(1);
        
        verify(repository).get(1);
    }
    
    @Test
    public void checkThanAGroupDoesNoExist() {
        assertFalse(instance.exists(1));
    }
    
    
}
