package org.earelin.ecclesia.service.resource;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;
import org.earelin.ecclesia.service.dto.resource.ManagedImageDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ArrayUtils;

/**
 * File managed service
 */
@Service
@Transactional
public class ManagedFileServiceImpl implements ManagedFileService {
    
    public static final String[] IMAGE_MIME_TYPES
            = {"image/gif", "image/jpeg", "image/png"};
    
    private final ManagedFileRepository repository;
    private final FileService fileService;
    private final ImageProcessingService imageService;

    @Autowired
    public ManagedFileServiceImpl(ManagedFileRepository repository,
            FileService fileService, ImageProcessingService imageService) {
        this.repository = repository;
        this.fileService = fileService;
        this.imageService = imageService;
    }            

    @Override
    public ManagedFileDto addPublicFile(File file) throws Exception {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final String dateString = formatter.format(LocalDateTime.now());
        return add(file, "public:///" + dateString);
    }
    
    @Override
    public ManagedFileDto addPrivateFile(File file) throws Exception {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final String dateString = formatter.format(LocalDateTime.now());
        return add(file, "private:///" + dateString);
    }

    @Override
    public ManagedFileDto add(File file, String folderUri) throws Exception {
        ManagedFile fileEntity = new ManagedFile();
        fileEntity.setCreated(new Date());
        fileEntity.setMime(fileService.getMimeType(file));
        
        final String writtenFileUri = fileService.save(file, folderUri);
        fileEntity.setUri(writtenFileUri);
        
        repository.add(fileEntity);
        
        ManagedFileDto fileDto = generateDto(fileEntity);
        
        if (fileDto instanceof ManagedImageDto) {
            imageService.processImage(fileEntity.getUri());
        }
        
        return fileDto;
    }

    @Override
    public void remove(long id) throws Exception {
        ManagedFile file = repository.get(id);
        
        if (file == null) {
            throw new EntityNotFoundException(id);
        }
                
        fileService.delete(file.getUri());
        
        if (isImage(file)) {
            imageService.deleteGeneratedImages(file.getUri());
        }
        
        repository.remove(file);      
    }

    @Transactional(readOnly = true)
    @Override
    public ManagedFileDto get(long id) throws Exception {
        ManagedFile file = repository.get(id);
        
        if (file == null) {
            throw new EntityNotFoundException(id);
        }
        
        return generateDto(file);
    }
    
    private ManagedFileDto generateDto(ManagedFile fileEntity) throws Exception {
        ManagedFileDto fileDto;
        
        final String fileUrl = fileService.getUrl(fileEntity.getUri());
        
        if (isImage(fileEntity)) {
            final Map<String, String> imageStyles
                    = imageService.getGeneratedImagesPaths(fileUrl);
            fileDto = new ManagedImageDto(fileEntity.getId(), fileEntity.getMime(),
                    fileEntity.getCreated(), fileUrl, imageStyles);
        } else {
            fileDto = new ManagedFileDto(fileEntity.getId(), fileEntity.getMime(),
                    fileEntity.getCreated(), fileUrl);
        }
        
        return fileDto;
    }
    
    private boolean isImage(ManagedFile file) {
        return isImage(file.getMime());
    }
    
    private boolean isImage(String mime) {
        return ArrayUtils.contains(IMAGE_MIME_TYPES, mime);
    }

}
