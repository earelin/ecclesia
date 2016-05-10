package org.earelin.ecclesia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.GroupRepository;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Group service
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
        
    private final GroupRepository repository;
    private final OrganizationService organizationService;
    private final Mapper mapper;
    
    @Autowired
    public GroupServiceImpl(GroupRepository dao,
            OrganizationService organizationService, Mapper mapper) {
        this.repository = dao;
        this.organizationService = organizationService;
        this.mapper = mapper;
    }

    @Override
    public void add(GroupDto groupDto) {
        
        if (groupDto.getId() != 0) {
            throw new ValidationException("Error validating GroupDto: field id is not 0");
        }                
        
        check(groupDto);
        
        Date now = new Date();
        groupDto.setCreated(now);
        groupDto.setUpdated(now);
        
        Group group = mapper.map(groupDto, Group.class);
        repository.add(group);
        
        groupDto.setId(group.getId());
    }

    @Override
    public void update(GroupDto groupDto) {
        Group group = repository.get(groupDto.getId());        
        if (group == null) {
            throw new EntityNotFoundException("Trying to update an unexisting Group with id " + groupDto.getId());
        }
        
        check(groupDto);
        
        Date now = new Date();
        groupDto.setUpdated(now);               
        
        mapper.map(groupDto, group);
        
        repository.update(group);
    }

    @Override
    public void remove(long id) {
        Group group = repository.get(id);
        
        if (group == null) {
            throw new EntityNotFoundException("Trying to remove an unexisting Group with id " + id);
        }
        
        repository.remove(group);
    }

    @Transactional(readOnly = true)
    @Override
    public GroupDto get(long id) {
        Group group = repository.get(id);
        
        if (group == null) {
            throw new EntityNotFoundException("Trying to load an unexisting Group with id " + id);
        }
        
        return mapper.map(group, GroupDto.class);
    }

    @Override
    public boolean exists(long id) {
        Group group = repository.get(id);
        return group != null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GroupDto> findAll() {
        List<Group> groups = repository.findAll();
        List<GroupDto> groupDtos = new ArrayList();
        mapper.map(groups, groupDtos); 
        return groupDtos;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GroupDto> findAll(int limit, int offset) {
        List<Group> groups = repository.findAll(limit, offset);
        List<GroupDto> groupDtos = new ArrayList();
        mapper.map(groups, groupDtos); 
        return groupDtos;
    }
    
    /**
     * Check business rules
     * @param groupDto 
     */
    private void check(GroupDto groupDto) {
        OrganizationDto organization = groupDto.getOrganization();
        if (organization == null) {
            throw new ValidationException("Error validating GroupDto: field organization is null");
        }
        
        if (!organizationService.exists(organization.getId())) {
            throw new EntityNotFoundException("Organization with id " + organization.getId() + " not found.");
        }
        
        GroupDto parentGroupDto = groupDto.getParent();
        if (parentGroupDto != null) {            
            Group parentGroup = repository.get(parentGroupDto.getId());
            if (parentGroup == null) {
                throw new ValidationException("Error validating GroupDto: field parent does not exist");
            } 
            if (parentGroup.getOrganization().getId()
                    != groupDto.getOrganization().getId()) {
                throw new ValidationException("Error validating GroupDto: field parent does not belong to a organization");
            }
        }
    }
    
}
