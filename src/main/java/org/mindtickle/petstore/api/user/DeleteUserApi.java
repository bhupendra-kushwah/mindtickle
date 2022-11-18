package org.mindtickle.petstore.api.user;

import org.mindtickle.petstore.api.AbstractBaseApi;

import java.util.HashMap;
import java.util.Map;

public class DeleteUserApi extends AbstractBaseApi {

    private String responseBody;
    private int responseCode;

    public static Map<String, String> apiHeader = new HashMap<String, String>();

    public String deleteUserApiURL() {
        return "/v2/user/username";
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void deleteUserApi(String userName) throws Exception {
        response = delete(getHostUrl() + deleteUserApiURL().replace("username", userName), getHeaders(apiHeader), "");
        this.responseBody = response.body().string();
        this.responseCode = response.code();
    }



}
