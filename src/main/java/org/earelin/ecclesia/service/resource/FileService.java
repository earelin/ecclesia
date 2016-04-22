/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.service.resource;

import java.io.File;

/**
 *
 * @author xcarriba
 */
public interface FileService {
    
    String getMimeType(File file);
    String getFile(String uri);
    String getFileUrl(String uri);
    String getFilePath(String uri);
    String saveFile(File file, String folderUri);
    
}
