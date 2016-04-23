package org.earelin.ecclesia.service.impl.resource;

import org.dozer.Mapper;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDTO;
import org.earelin.ecclesia.service.resource.ManagedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * File managed service
 */
@Service
@Transactional
public class ManagedFileServiceImpl implements ManagedFileService {
    
    private final ManagedFileRepository repository;
    private final Mapper mapper;

    @Autowired
    public ManagedFileServiceImpl(ManagedFileRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }            

    @Override
    public void add(ManagedFileDTO file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ManagedFileDTO file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public ManagedFileDTO get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}