package com.pixelservices.flashapi;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;

public abstract class FlashPlugin {
    private final PluginWrapper wrapper;
    private final Logger pluginLogger = LoggerFactory.getLogger(this.getClass());

    FlashPlugin (PluginWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public FlashPlugin() {
        throw new RuntimeException("Flash Plugins can only be initialized through a plugin wrapper.");
    }

    public Logger getLogger() {
        return pluginLogger;
    }

    abstract void onEnable();

    abstract void onDisable();

    protected final FlashPlugin getDependency(Class<? extends FlashPlugin> dependencyClass) {
        return wrapper.getDependency(dependencyClass);
    }
}
