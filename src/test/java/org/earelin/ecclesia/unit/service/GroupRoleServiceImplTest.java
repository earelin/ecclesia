package org.earelin.ecclesia.unit.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.GroupRole;
import org.earelin.ecclesia.repository.GroupRoleRepository;
import org.earelin.ecclesia.service.GroupRoleService;
import org.earelin.ecclesia.service.GroupRoleServiceImpl;
import org.earelin.ecclesia.service.GroupService;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.GroupRoleDto;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * GroupRoleServiceImpl unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupRoleServiceImplTest {
    
    private GroupDto group; 
    private final Mapper mapper = new DozerBeanMapper();
    private GroupRoleService instance;
    
    @Mock
    private GroupRoleRepository repository;
    
    @Mock
    private GroupService groupService;
    
    @Before
    public void setUp() {
        OrganizationDto organization = new OrganizationDto();
        
        group = new GroupDto();
        group.setId(1);
        group.setOrganization(organization);
        
        instance = new GroupRoleServiceImpl(repository, groupService, mapper);        
    }
    
    @Test
    public void createNewGroupRole() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                GroupRole role = (GroupRole) args[0];
                role.setId(1);
                return null;
            } 
        }).when(repository).add(any(GroupRole.class));
        
        when(groupService.exists(1)).thenReturn(true);
        
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        instance.add(role);
        
        verify(repository).add(any(GroupRole.class));
        assertNotEquals("Created group role id should not be 0", 0, role.getId());
        assertEquals("Group should belong to defined group", group, role.getGroup());
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupRoleShouldBelongToAGroup() {
        GroupRoleDto role = new GroupRoleDto();
        
        instance.add(role);
    }    
    
    @Test(expected = EntityNotFoundException.class)
    public void newGroupRoleShouldBelongToAnExistingGroup() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);

        instance.add(role);
    }
    
    @Test(expected = ValidationException.class)
    public void existingGroupRoleShouldNotBeAdded() {
        GroupRoleDto role = new GroupRoleDto();
        role.setId(1);
        
        instance.add(role);
    }
    
    @Test
    public void updateExistingGroupRole() {
        GroupRole roleEntity = new GroupRole();
        roleEntity.setId(1);
        
        GroupRoleDto role = new GroupRoleDto();
        role.setId(1);
        role.setGroup(group);
        
        when(repository.get(1)).thenReturn(roleEntity);
        when(groupService.exists(1)).thenReturn(true);
        
        instance.update(role);
        
        verify(repository).update(roleEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateNotExistingGroupRole() {
        GroupRoleDto role = new GroupRoleDto();
        role.setId(1);
        role.setGroup(group);
        
        instance.update(role);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupRoleShouldBelongToAGroup() {
        GroupRole roleEntity = new GroupRole();
        roleEntity.setId(1);
        
        when(repository.get(1)).thenReturn(roleEntity);
        
        GroupRoleDto role = new GroupRoleDto();
        role.setId(1);
        
        instance.update(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatedGroupRoleShouldBelongToAnExistingGroup() {
        GroupRole roleEntity = new GroupRole();
        roleEntity.setId(1);
        
        when(repository.get(1)).thenReturn(roleEntity);
        
        GroupRoleDto role = new GroupRoleDto();
        role.setId(1);
        role.setGroup(group);
        
        instance.update(role);
    }
    
    @Test
    public void removeExistingGroupRole() {
        GroupRole roleEntity = new GroupRole();
        roleEntity.setId(1);
        
        when(repository.get(1)).thenReturn(roleEntity);
        
        instance.remove(1);
        
        verify(repository).remove(roleEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingGroupRole() {
        instance.remove(1);
    }
    
    @Test
    public void getExistingGroupRole() {
        GroupRole roleEntity = new GroupRole();
        roleEntity.setId(1);
        
        when(repository.get(1)).thenReturn(roleEntity);
        
        instance.get(1);
        
        verify(repository).get(1);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingGroupRole() {
        instance.get(1);
    }
    
}
