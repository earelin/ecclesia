package org.earelin.ecclesia.service;

import org.dozer.Mapper;
import org.earelin.ecclesia.domain.GroupRole;
import org.earelin.ecclesia.repository.GroupRoleRepository;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.GroupRoleDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Group role service implementation
 */
@Service
@Transactional
public class GroupRoleServiceImpl implements GroupRoleService {
    
    private final GroupRoleRepository repository;
    private final GroupService groupService;
    private final Mapper mapper;

    @Autowired
    public GroupRoleServiceImpl(GroupRoleRepository repository,
            GroupService groupService, Mapper mapper) {
        this.repository = repository;
        this.groupService = groupService;
        this.mapper = mapper;
    }

    @Override
    public void add(GroupRoleDto roleDto) {        
        if (roleDto.getId() != 0) {
            throw new ValidationException("Error validating GroupRoleDto. Id is not 0");
        }
        
        check(roleDto);
        
        GroupRole role = mapper.map(roleDto, GroupRole.class);
        repository.add(role);
        roleDto.setId(role.getId());
    }

    @Override
    public void update(GroupRoleDto roleDto) {
        GroupRole role = repository.get(roleDto.getId());        
        if (role == null) {
            throw new EntityNotFoundException("Trying to update an unexisting GroupRole with id " + roleDto.getId());
        }
        
        check(roleDto);
        
        mapper.map(roleDto, role);
        
        repository.update(role);
    }

    @Override
    public void remove(long id) {
        GroupRole role = repository.get(id);
        
        if (role == null) {
            throw new EntityNotFoundException("Trying to remove an unexisting GroupRole with id " + id);
        }
        
        repository.remove(role);
    }

    @Transactional(readOnly = true)
    @Override
    public GroupRoleDto get(long id) {
        GroupRole role = repository.get(id);
        
        if (role == null) {
            throw new EntityNotFoundException("Trying to load an unexisting GroupRole with id " + id);
        }
        
        return mapper.map(role, GroupRoleDto.class);
    }
    
    /**
     * Check business rules
     * @param roleDto 
     */
    private void check(GroupRoleDto roleDto) {
        GroupDto group = roleDto.getGroup();
        if (group == null) {
            throw new ValidationException("Error in GroupRoleDto. Field group is null");
        }
        
        if (!groupService.exists(group.getId())) {
            throw new EntityNotFoundException("GroupRole does not belong to an existing group. Group with id " + group.getId() + " does not exist");
        }
    }

}
