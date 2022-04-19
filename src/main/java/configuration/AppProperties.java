package configuration;

import configuration.model.BrowserModel;
import configuration.model.EnvironmentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class AppProperties {
    Logger logger = LoggerFactory.getLogger(AppProperties.class);
    YamlReader yamlReader = new YamlReader();
    private BrowserModel browser;

    public AppProperties() {
        setSystemPropertiesFromYamlEnvironment();
        setBrowserProperties();
    }

    public static AppProperties getInstance() {
        return AppProperties.AppPropertiesSingleton.INSTANCE;
    }

    private void setSystemPropertiesFromYamlEnvironment() {
        List<EnvironmentModel> listOfEnvironments = yamlReader.getConfig().getEnvironment().getListOfEnvironments();
        boolean foundActiveEnvironment = false;
        for (EnvironmentModel environmentModel : listOfEnvironments) {
            if (environmentModel.isActive()) {
                foundActiveEnvironment = true;
                Map<String, Object> environmentProperties = environmentModel.getProperties();
                for (Map.Entry entry : environmentProperties.entrySet()) {
                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
                    logger.info("Loaded environment property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
                }
                logger.info("Loaded environment properties total: {}", environmentProperties.size());
                break;
            }
        }
        if (foundActiveEnvironment == false) {
            loadDefaultEnvironment();
        }

    }

    private void loadDefaultEnvironment() {
        logger.info("No environment was specified in config.yaml. Loading default properties for Test1");
        Map<String, Object> environmentProperties = new YamlReader().getConfig().getEnvironment().getTest1().getProperties();
        for (Map.Entry entry : environmentProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded environment property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
        logger.info("Loaded environment properties total: {}", environmentProperties.size());
    }

    private void setBrowserProperties() {
        YamlReader yamlReader = new YamlReader();
        browser = yamlReader.getConfig().getBrowser();
        Map<String, Object> browserProperties = browser.getBrowserProperties();
        for (Map.Entry entry : browserProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded browser properties: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static class AppPropertiesSingleton {
        private static final AppProperties INSTANCE = new AppProperties();
    }

}
