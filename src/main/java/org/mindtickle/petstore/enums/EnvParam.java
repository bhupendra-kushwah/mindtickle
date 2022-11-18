package org.mindtickle.petstore.enums;

import org.apache.commons.lang3.StringUtils;
import org.mindtickle.petstore.utils.ConfigUtils;

public enum EnvParam {
    HOST_URL("host.url"),
	EXECUTION_ENVIRONMENT("execution.environment");

    private final String envKey;
    private String 		 envValue = null;

    EnvParam(String paramKeyName) {
        envKey = paramKeyName;
    }

    public String getEnvKey() {
        return this.envKey;
    }

    public void setValue(String value) {
        this.envValue = value;
    }

    public String getValue() {
        if(StringUtils.isEmpty(this.envValue))
            this.envValue = System.getProperty(this.getEnvKey());

        if(this.envValue == null)
            this.envValue = ConfigUtils.getProperty(this.getEnvKey());
        return this.envValue;
    }

    public String getValueRaw() {
        return System.getProperty(this.getEnvKey());
    }

    // Returns environment value is set else return default value;
    public String getValue(String defaultValue) {
        this.envValue = this.getValue();

        if(StringUtils.isEmpty(this.envValue))
            this.envValue = defaultValue;

        return this.envValue;
    }
}