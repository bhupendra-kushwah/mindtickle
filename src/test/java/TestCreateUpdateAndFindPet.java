import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mindtickle.petstore.api.pet.CreatePetApi;
import org.mindtickle.petstore.api.pet.DeletePetApi;
import org.mindtickle.petstore.api.pet.FindPetApi;
import org.mindtickle.petstore.api.pet.UpdatePetApi;
import org.mindtickle.petstore.api.pojo.pet.Pet;
import org.mindtickle.petstore.utils.json.JsonSerializationAndDeserialization;

//@RunWith(JUnit4.class)
public class TestCreateUpdateAndFindPet {

    private CreatePetApi createPetApi = null;
    private UpdatePetApi updatePetApi = null;
    private FindPetApi findPetApi = null;
    private static DeletePetApi deletePetApi = null;
    private static String pet = "  {\n" +
            "    \"id\": 1111111111,\n" +
            "    \"category\": {\n" +
            "      \"id\": 9,\n" +
            "      \"name\": \"walk\"\n" +
            "    },\n" +
            "    \"name\": \"doggie\",\n" +
            "    \"photoUrls\": [\n" +
            "      \"string\"\n" +
            "    ],\n" +
            "    \"tags\": [\n" +
            "      {\n" +
            "        \"id\": 9,\n" +
            "        \"name\": \"dog\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"status\": \"sold\"\n" +
            "  }";

    private static Pet petObj = null;

    @BeforeClass
    public static void createPetsList(){
        petObj = new JsonSerializationAndDeserialization<Pet>().deserializeJson(pet, Pet.class);
    }

    @AfterClass
    public static void deletePets() throws Exception {
        deletePetApi = new DeletePetApi();
        deletePetApi.deleteUserApi(String.valueOf(petObj.getId()));

    }

    @Test
    public void testCreatePet() throws Exception {
        createPetApi = new CreatePetApi();
        createPetApi.createPetApi(pet);
        int responseCode = createPetApi.getResponseCode();

        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        Assert.assertTrue("Pet is not created ", createPetApi.validatePetCreated(petObj));
    }

    @Test
    public void testUpdatePet() throws Exception {
        pet = "  {\n" +
                "    \"id\": 77777777777,\n" +
                "    \"category\": {\n" +
                "      \"id\": 9,\n" +
                "      \"name\": \"walk\"\n" +
                "    },\n" +
                "    \"name\": \"Catttiii\",\n" +
                "    \"photoUrls\": [\n" +
                "      \"string\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "      {\n" +
                "        \"id\": 9,\n" +
                "        \"name\": \"dog\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"status\": \"sold\"\n" +
                "  }";
        petObj = new JsonSerializationAndDeserialization<Pet>().deserializeJson(pet, Pet.class);

        updatePetApi = new UpdatePetApi();
        updatePetApi.updateUserApi(pet);
        int responseCode = updatePetApi.getResponseCode();

        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        Assert.assertTrue("Pet is not updated ", updatePetApi.validatePetUpdated(petObj));
    }


    @Test
    public void testFindPetByStatus() throws Exception {
        String status = "sold";
        findPetApi = new FindPetApi();
        findPetApi.findPetByStatusApi(status);
        int responseCode = findPetApi.getResponseCode();
        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        Assert.assertTrue("Pet is not found ", findPetApi.validatePetFound(petObj));
    }
}
