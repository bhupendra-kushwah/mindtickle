package org.mindtickle.petstore.api.user;

import org.mindtickle.petstore.api.AbstractBaseApi;

import java.util.HashMap;
import java.util.Map;

public class GetUserDetailsApi extends AbstractBaseApi {

    private String responseBody;
    private int responseCode;

    public static Map<String, String> apiHeader = new HashMap<String, String>();

    public String getUserDetailsApiURL() {
        return "/v2/user/username";
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void getUserDetailsApi(String userName) throws Exception {
        response = get(getHostUrl() + getUserDetailsApiURL().replace("username", userName), getHeaders(apiHeader));
        this.responseBody = response.body().string();
        this.responseCode = response.code();
    }



}
