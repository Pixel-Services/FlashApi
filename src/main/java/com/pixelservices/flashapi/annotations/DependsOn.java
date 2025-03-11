package com.pixelservices.flashapi.annotations;

import com.pixelservices.flashapi.FlashPlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated class depends on other FlashPlugin classes.
 * This annotation is used to specify the dependencies of a plugin.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DependsOn {
    Class<? extends FlashPlugin>[] value();
}
