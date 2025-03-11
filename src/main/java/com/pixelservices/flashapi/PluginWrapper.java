package com.pixelservices.flashapi;


import com.pixelservices.flashapi.exception.PluginLifecycleException;
import com.pixelservices.flashapi.exception.PluginLoadException;

public abstract class PluginWrapper {
    protected final FlashPlugin plugin;
    protected final FlashPlugin[] dependencies;
    protected Status status;

    protected PluginWrapper(Class<? extends FlashPlugin> plugin, FlashPlugin[] dependencies) {
        try {
            this.plugin = plugin.getDeclaredConstructor().newInstance();
            this.plugin.load(this);
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

    public final FlashPlugin getPlugin() {
        return plugin;
    }

    protected Status getStatus() {
        return status;
    }

    protected void enable() {
        if (status != Status.LOADED && status != Status.DISABLED) {
            throw new PluginLifecycleException("Cannot enable plugin in state " + status);
        }

        try {
            plugin.onEnable();
            status = Status.ENABLED;
        } catch (Throwable e) {
            status = Status.FAILED;
            throw new PluginLifecycleException("Failed to enable plugin", e);
        }
    }

    protected void disable() {
        if (this.status != Status.ENABLED) {
            throw new PluginLifecycleException("Cannot disable plugin in state " + status);
        }

        try {
            plugin.onEnable();
            this.status = Status.DISABLED;
        } catch (Throwable e) {
            this.status = Status.FAILED;
            throw new PluginLifecycleException("Failed to disable plugin", e);
        }
    }

    public enum Status {
        LOADED,
        ENABLED,
        DISABLED,
        FAILED,
    }
}
