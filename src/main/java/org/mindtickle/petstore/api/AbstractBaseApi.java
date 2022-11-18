package org.mindtickle.petstore.api;

import okhttp3.Response;


import java.util.HashMap;
import java.util.Map;

import org.mindtickle.petstore.enums.EnvParam;
import org.mindtickle.petstore.utils.http.HttpClient;

public abstract class AbstractBaseApi {

        //Static stuff
        //Class variables
        public static HttpClient client	= new HttpClient();
        protected Response response;
        private Map<String, String> defaultHeaders = new HashMap<String, String>();
        private String requestData;
        private String responseData;
        
        //Default BASE URL and other variables value is fetched from environment variable if passed.
        protected static String HOST_URL =	EnvParam.HOST_URL.getValue();
        
        //Constructors
        public AbstractBaseApi() {
            if(HOST_URL == null || HOST_URL.isEmpty())
            	HOST_URL = "https://petstore.swagger.io";
        	this.setDefaultHeaders();
        }

        /*
         * Add headers which are common across all api's.
         */
        private void setDefaultHeaders() { 
           defaultHeaders.put("accept", "application/json");
           defaultHeaders.put("Content-Type", "application/json");
        }

        public String getHostUrl() {
        	return HOST_URL;
        }
        
        protected  Map<String, String> getHeaders(Map<String, String> headers) {
            
        	Map<String, String> finalHeaders = new HashMap<String, String>();
            if(headers != null)
                finalHeaders.putAll(headers);
            finalHeaders.putAll(this.defaultHeaders);
            return finalHeaders;
        }

        public String getRequestData() {
            return requestData;
        }

        public void setRequestData(String requestData) {
            this.requestData = requestData;
        }

        public String getResponseData() {
            return responseData;
        }

        public void setResponseData(String responseData) {
            this.responseData = responseData;
        }
        
        //Abstract stuff
        
        //Keep a String variable in child api class that holds response code at all times and below method should return that value.
        public abstract int getResponseCode();


        //Keep a String variable in child api class that holds response body at all times and below method should return that value.
        public abstract String getResponseBody();
                
        public Response get(String url, Map<String, String> headers) {
            return client.GET(url, this.getHeaders(headers));
        }

        public Response post(String url, Map<String, String> headers, String payload) {
            return client.POST(url, this.getHeaders(headers), payload);
        }

        public Response put(String url, Map<String, String> headers, String payload) {
            return client.PUT(url, this.getHeaders(headers), payload);
        }
        public Response delete(String url, Map<String, String> headers, String payload) {
            return client.DELETE(url, this.getHeaders(headers), payload);
        }
    
}

