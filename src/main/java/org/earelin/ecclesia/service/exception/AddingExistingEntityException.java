/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.service.exception;

/**
 *
 * @author xcarriba
 */
public class AddingExistingEntityException extends RuntimeException {
    
    public AddingExistingEntityException(String message) {
        super(message);
    }
}
