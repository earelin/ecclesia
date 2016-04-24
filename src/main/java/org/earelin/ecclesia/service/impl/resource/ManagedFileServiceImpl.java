package org.earelin.ecclesia.service.impl.resource;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
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
    public void add(File file) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("public:///yyyy/MM/dd");
        String dateString = formatter.format(LocalDateTime.now());
        add(file, dateString);
    }

    @Override
    public void add(File file, String folderUri) throws Exception {
        ManagedFile fileEntity = new ManagedFile();
        fileEntity.setCreated(new Date());
        fileEntity.setMime(fileService.getMimeType(file));
        
        String writtenFileUri = fileService.save(file, folderUri);
        fileEntity.setUri(writtenFileUri);
        
        repository.add(fileEntity);
    }

    @Override
    public void remove(long id) throws Exception {
        ManagedFile file = repository.get(id);
        
        if (file == null) {
            throw new EntityNotFoundException(id);
        }
        
        repository.remove(file);
    }

    @Transactional(readOnly = true)
    @Override
    public ManagedFileDto get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
