package org.mindtickle.petstore.utils;

public class Logger {
	
	public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);
    
	public static void info(String s) {
        log.info(s);
    }

    public static void info(boolean flag, String s) {
        if(flag)
        	log.assertLog(flag, s);
    }
}
