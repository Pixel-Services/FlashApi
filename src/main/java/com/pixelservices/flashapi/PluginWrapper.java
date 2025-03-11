package com.pixelservices.flashapi;


import com.pixelservices.flashapi.exception.PluginLifecycleException;
import com.pixelservices.flashapi.exception.PluginLoadException;

public abstract class PluginWrapper {
    protected final FlashPlugin plugin;
    protected final FlashPlugin[] dependencies;
    protected Status status;

    protected PluginWrapper(Class<? extends FlashPlugin> plugin, FlashPlugin[] dependencies) {
        try {
            this.plugin = plugin.getDeclaredConstructor(PluginWrapper.class).newInstance(this);
        } catch (Throwable e) {
            throw new PluginLoadException("Failed to load plugin", e);
        }
        this.dependencies = dependencies;
        this.status = Status.LOADED;
    }

    public final FlashPlugin getDependency(Class<? extends FlashPlugin> dependencyClass) {
        for (FlashPlugin flashPlugin : dependencies) {
            if (dependencyClass.isInstance(flashPlugin)) {
                return flashPlugin;
            }
        }
        return null;
    }

    protected void enable() {
        if (this.status != Status.LOADED && this.status != Status.DISABLED) {
            throw new PluginLifecycleException("Cannot enable plugin in state " + this.status);
        }

        try {
            this.plugin.onEnable();
            this.status = Status.ENABLED;
        } catch (Throwable e) {
            this.status = Status.FAILED;
            throw new PluginLifecycleException("Failed to enable plugin", e);
        }
    }

    protected void disable() {
        if (this.status != Status.ENABLED) {
            throw new PluginLifecycleException("Cannot disable plugin in state " + this.status);
        }

        try {
            plugin.onEnable();
            this.status = Status.DISABLED;
        } catch (Throwable e) {
            this.status = Status.FAILED;
            throw new PluginLifecycleException("Failed to disable plugin", e);
        }
    }

    protected enum Status {
        LOADED,
        ENABLED,
        DISABLED,
        FAILED,
    }
}
