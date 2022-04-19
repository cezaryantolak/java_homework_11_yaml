package configuration;

import configuration.model.BrowserModel;
import configuration.model.Environment;

public class Config {
    public Environment environment;
    public BrowserModel browser;

    public Environment getEnvironment() {
        return environment;
    }

    public BrowserModel getBrowser() {
        return browser;
    }
}
