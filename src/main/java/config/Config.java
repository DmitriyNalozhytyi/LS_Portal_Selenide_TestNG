package config;

import constants.ConfigType;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private final Properties config;
    private static Config instance;


    private Config() {
        config = new Properties();
        try {
            /**
             * Core props
             */
            config.load(ClassLoader.getSystemResourceAsStream(ConfigType.CONFIG_NAME));

            config.putAll(System.getProperties());
        } catch (IOException ex) {
            throw new AssertionError(ex.getMessage());
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public enum HostsData {

        METINVEST(new String[] {
                getInstance().config.getProperty(ConfigType.DEV_HOST),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER15_LOGIN),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER15_PASSWORD),

                getInstance().config.getProperty(ConfigType.DEV_TESTUSER14_LOGIN),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER14_PASSWORD),

                getInstance().config.getProperty(ConfigType.DEV_TESTUSER4_LOGIN),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER4_PASSWORD),

                getInstance().config.getProperty(ConfigType.DEV_TESTUSER10_LOGIN),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER10_PASSWORD),

                getInstance().config.getProperty(ConfigType.DEV_TESTUSER11_LOGIN),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER11_PASSWORD),

                getInstance().config.getProperty(ConfigType.DEV_TESTUSER12_LOGIN),
                getInstance().config.getProperty(ConfigType.DEV_TESTUSER12_PASSWORD),
            }
        );

        public final String value[];

        HostsData(String[] v) {
            value = v;
        }

        public String[] value() {
            return value;
        }
    }

    public static String getProperty(String configType) {
        return getInstance().config.getProperty(configType);
    }

    public static Integer getIntProperty(String configType) {
        String property = getInstance().config.getProperty(configType);
        return Integer.parseInt(property);
    }

}
