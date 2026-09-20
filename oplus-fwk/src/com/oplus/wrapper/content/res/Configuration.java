package com.oplus.wrapper.content.res;

public class Configuration {
    private final android.content.res.Configuration mConfiguration;

    public Configuration(android.content.res.Configuration configuration) {
        this.mConfiguration = configuration;
    }

    public com.oplus.wrapper.app.WindowConfiguration getWindowConfiguration() {
        return new com.oplus.wrapper.app.WindowConfiguration();
    }
}
