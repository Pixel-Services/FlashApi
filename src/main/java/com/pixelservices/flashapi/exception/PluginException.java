package com.pixelservices.flashapi.exception;

/**
 * Represents an exception that occurs within the plugin system.
 * This exception is thrown to indicate various plugin-related errors.
 */
public class PluginException extends RuntimeException {

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable e) {
        super(message, e);
    }
}
