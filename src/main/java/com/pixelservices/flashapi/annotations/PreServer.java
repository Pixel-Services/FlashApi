package com.pixelservices.flashapi.annotations;

import org.jetbrains.annotations.ApiStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated class is a pre-server component.
 * This annotation is used to mark classes that should be enabled
 * before the server starts.
 *
 * <p>This annotation is marked as experimental and may change in future releases.</p>
 *
 * @see org.jetbrains.annotations.ApiStatus.Experimental
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ApiStatus.Experimental
public @interface PreServer {
}

