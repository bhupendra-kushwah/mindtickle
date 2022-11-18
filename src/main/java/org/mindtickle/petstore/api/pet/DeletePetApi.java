package org.mindtickle.petstore.api.pet;

import org.mindtickle.petstore.api.AbstractBaseApi;

import java.util.HashMap;
import java.util.Map;

public class DeletePetApi extends AbstractBaseApi {

    private String responseBody;
    private int responseCode;

    public static Map<String, String> apiHeader = new HashMap<String, String>();

    public String deletePetApiURL() {
        return "/v2/pet/petId";
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void deleteUserApi(String petId) throws Exception {
        response = delete(getHostUrl() + deletePetApiURL().replace("petId", petId), getHeaders(apiHeader), "");
        this.responseBody = response.body().string();
        this.responseCode = response.code();
    }



}
