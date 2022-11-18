package org.mindtickle.petstore.utils;

import org.apache.commons.lang3.StringUtils;
import org.mindtickle.petstore.enums.EnvParam;
import org.mindtickle.petstore.enums.ExecEnv;

import java.io.File;
import java.util.Properties;

public class ConfigUtils {

    private static String     envStr  = EnvParam.EXECUTION_ENVIRONMENT.getValueRaw();

    //get value of Execution Environment from environment variables, take default from Constants.java
    private static ExecEnv execEnv = StringUtils.isNotBlank(envStr)? ExecEnv.valueOf(envStr) : Constants.DEFAULT_EXECUTION_ENVIRONMENT;
    private static Properties prop    = new Properties();

    private static void init() {
        try {
            prop.load(CommonUtils.getResourceFileAsStream(Constants.CONFIG_DIR + execEnv.name().toLowerCase() + File.separator + Constants.CONFIG_FILE_NAME));
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if(prop.size() == 0)
            init();
        return prop.getProperty(key);
    }
}
