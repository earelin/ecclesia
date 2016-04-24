package org.earelin.ecclesia.service.impl.resource;

import java.io.File;
import org.dozer.Mapper;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;
import org.earelin.ecclesia.service.resource.FileService;
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
    private final FileService fileService;
    private final Mapper mapper;

    @Autowired
    public ManagedFileServiceImpl(ManagedFileRepository repository,
            FileService fileService, Mapper mapper) {
        this.repository = repository;
        this.fileService = fileService;
        this.mapper = mapper;
    }            

    @Override
    public void add(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public ManagedFileDto get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
