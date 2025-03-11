package com.pixelservices.flashapi.exception;

/**
 * Represents an exception that occurs during the lifecycle of a plugin.
 * This exception is thrown to indicate errors that happen during the
 * initialization, execution, or termination of a plugin.
 */
public class PluginLifecycleException extends PluginException {
    public PluginLifecycleException(String message) {
        super(message);
    }

    public PluginLifecycleException(String message, Throwable e) {
        super(message, e);
    }
}
