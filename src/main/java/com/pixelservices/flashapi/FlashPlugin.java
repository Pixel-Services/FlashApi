package com.pixelservices.flashapi;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;

public abstract class FlashPlugin {
    private final Logger pluginLogger = LoggerFactory.getLogger(this.getClass());
    private PluginWrapper wrapper;

    final void load(PluginWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public Logger getLogger() {
        return pluginLogger;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    protected final FlashPlugin getDependency(Class<? extends FlashPlugin> dependencyClass) {
        return wrapper.getDependency(dependencyClass);
    }
}
