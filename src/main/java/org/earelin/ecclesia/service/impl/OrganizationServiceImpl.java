package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.service.dto.OrganizationDTO;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.OrganizationRepository;

/**
 * Organization service implementation
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationRepository dao;
    private final Mapper mapper;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationRepository dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public void add(OrganizationDTO organizationDTO) {
        Date now = new Date();
        organizationDTO.setCreated(now);
        organizationDTO.setUpdated(now);
        
        Organization organization = mapper.map(organizationDTO, Organization.class);
        dao.add(organization);
        
        organizationDTO.setId(organization.getId());
    }

    @Override
    public void update(OrganizationDTO organizationDTO) {
        Date now = new Date();
        organizationDTO.setUpdated(now);
        
        Organization organization = dao.get(organizationDTO.getId());        
        if (organization == null) {
            throw new OrganizationNotFoundException(organizationDTO.getId());
        }        
        mapper.map(organizationDTO, organization);
        
        dao.update(organization);
    }

    @Override
    public void remove(long id) {
        Organization organization = dao.get(id);
        
        if (organization == null) {
            throw new OrganizationNotFoundException(id);
        }
        
        dao.remove(organization);
    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationDTO get(long id) {
        Organization organization = dao.get(id);
        
        if (organization == null) {
            throw new OrganizationNotFoundException(id);
        }
        
        return mapper.map(organization, OrganizationDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrganizationDTO> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrganizationDTO> list(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
