package org.mindtickle.petstore.utils;

import org.mindtickle.petstore.enums.ExecEnv;

import java.io.File;

public class Constants {

    public static final String  JSON_SCHEMA_DIR             = File.separator + "json-schema" + File.separator;
    public static final String  CONFIG_DIR                  = File.separator + "config" + File.separator;
    public static final String  CONFIG_FILE_NAME            = "config.properties";
    public static final ExecEnv DEFAULT_EXECUTION_ENVIRONMENT = ExecEnv.STAGE;
}
