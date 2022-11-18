package org.mindtickle.petstore.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class CommonUtils {

    public static Gson                 gson       = new Gson();
    private static Gson                prettyGson = new GsonBuilder().setPrettyPrinting().create();
    private static Map<String, Object> cache      = new HashMap<String, Object>();

    public static Object getCache(String key) {
        return cache.get(key);
    }

    public static Object addToCache(Object key, Object obj) {
        return cache.put(String.valueOf(key), obj);
    }

    public static String convertMillisToDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static String toPrettyJson(Object object) {
        return "\n" + prettyGson.toJson(object) + "\n";
    }

    public static String getResourceFileContent(String path) throws IOException, URISyntaxException {
        String fileContent = new String(Files.readAllBytes(Paths.get(CommonUtils.class.getResource(path).toURI())), "UTF-8");
        return fileContent;
    }

    public static InputStream getResourceFileAsStream(String path) throws IOException, URISyntaxException {
        return CommonUtils.class.getResourceAsStream(path);
    }

    public static boolean validateJson(String schemaText, String jsonText) {
        try {
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaText));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(jsonText));
            return true;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
    
    public static String getDateAfterNumberOfDays(int days, String dateFormate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormate);
    	Calendar todaysDate = Calendar.getInstance(); 
    	todaysDate.add(Calendar.DAY_OF_YEAR, days);
    	return simpleDateFormat.format(todaysDate.getTime());
	}
    
    public static String getDateAfterNumberOfDays(int days) {
    	Calendar todaysDate = Calendar.getInstance(); 
    	todaysDate.add(Calendar.DAY_OF_YEAR, days);
    	return String.valueOf(todaysDate.getTimeInMillis());
	}

    public static String getMinuts(int minuts) {
    	Calendar todaysDate = Calendar.getInstance(); 
    	todaysDate.add(Calendar.MINUTE, minuts);
    	return convertMillisToDate(todaysDate.getTimeInMillis(), " HH:mm:ss");
	}
}
