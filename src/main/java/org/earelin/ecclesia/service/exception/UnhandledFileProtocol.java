package org.earelin.ecclesia.service.exception;

/**
 * Exception to report a protocol that can not be handled by file services
 */
public class UnhandledFileProtocol extends RuntimeException {

    public UnhandledFileProtocol(String message) {
        super(message);
    }

}
