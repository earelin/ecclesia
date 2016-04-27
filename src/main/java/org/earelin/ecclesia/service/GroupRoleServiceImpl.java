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
        validate(roleDto);
        
        if (roleDto.getId() != 0) {
            throw new ValidationException("Error validating GroupRoleDto: field id is not 0");
        }
        
        GroupRole role = mapper.map(roleDto, GroupRole.class);
        repository.add(role);
        roleDto.setId(role.getId());
    }

    @Override
    public void update(GroupRoleDto roleDto) {
        GroupRole role = repository.get(roleDto.getId());        
        if (role == null) {
            throw new EntityNotFoundException(roleDto.getId());
        }
        
        validate(roleDto);
        
        mapper.map(roleDto, role);
        
        repository.update(role);
    }

    @Override
    public void remove(long id) {
        GroupRole role = repository.get(id);
        
        if (role == null) {
            throw new EntityNotFoundException(id);
        }
        
        repository.remove(role);
    }

    @Transactional(readOnly = true)
    @Override
    public GroupRoleDto get(long id) {
        GroupRole role = repository.get(id);
        
        if (role == null) {
            throw new EntityNotFoundException(id);
        }
        
        return mapper.map(role, GroupRoleDto.class);
    }
    
    /**
     * Validates critical errors
     * @param roleDto 
     */
    private void validate(GroupRoleDto roleDto) {
        GroupDto group = roleDto.getGroup();
        if (group == null) {
            throw new ValidationException("Error validating GroupRoleDto: field group is null");
        }
        
        if (!groupService.exists(group.getId())) {
            throw new EntityNotFoundException(group.getId());
        }
    }

}
