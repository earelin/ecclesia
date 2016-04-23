package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.GroupRepository;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.GroupNotFoundException;
import org.earelin.ecclesia.service.exception.GroupWithoutOrganizationException;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.earelin.ecclesia.service.exception.ParentGroupBelongsToDiferentOrganizationException;
import org.earelin.ecclesia.service.exception.ParentGroupNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
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
        Date now = new Date();
        groupDto.setCreated(now);
        groupDto.setUpdated(now);
        
        OrganizationDto organization = groupDto.getOrganization();
        if (organization == null) {
            throw new GroupWithoutOrganizationException();
        }
        
        if (!organizationService.exists(organization.getId())) {
            throw new OrganizationNotFoundException(organization.getId());
        }
        
        GroupDto parentGroupDto = groupDto.getParent();
        if (parentGroupDto != null) {            
            Group parentGroup = repository.get(parentGroupDto.getId());
            if (parentGroup == null) {
                throw new ParentGroupNotFoundException();
            } 
            if (parentGroup.getOrganization().getId()
                    != groupDto.getOrganization().getId()) {
                throw new ParentGroupBelongsToDiferentOrganizationException();
            }
        }
        
        Group group = mapper.map(groupDto, Group.class);
        repository.add(group);
        
        groupDto.setId(group.getId());
    }

    @Override
    public void update(GroupDto groupDto) {
        Date now = new Date();
        groupDto.setUpdated(now);
        
        Group group = repository.get(groupDto.getId());        
        if (group == null) {
            throw new GroupNotFoundException(groupDto.getId());
        }
        
        OrganizationDto organization = groupDto.getOrganization();
        if (organization == null) {
            throw new GroupWithoutOrganizationException();
        }
        
        if (!organizationService.exists(organization.getId())) {
            throw new OrganizationNotFoundException(organization.getId());
        }
        
        GroupDto parentGroupDto = groupDto.getParent();
        if (parentGroupDto != null) {            
            Group parentGroup = repository.get(parentGroupDto.getId());
            if (parentGroup == null) {
                throw new ParentGroupNotFoundException();
            } 
            if (parentGroup.getOrganization().getId()
                    != groupDto.getOrganization().getId()) {
                throw new ParentGroupBelongsToDiferentOrganizationException();
            }
        }
        
        mapper.map(groupDto, group);
        
        repository.update(group);
    }

    @Override
    public void remove(long id) {
        Group group = repository.get(id);
        
        if (group == null) {
            throw new GroupNotFoundException(id);
        }
        
        repository.remove(group);
    }

    @Transactional(readOnly = true)
    @Override
    public GroupDto get(long id) {
        Group group = repository.get(id);
        
        if (group == null) {
            throw new GroupNotFoundException(id);
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
    public List<GroupDto> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public List<GroupDto> list(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
