package kedia.pk.testapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiPoint {
    String BASE_URL = "https://api.github.com/";
    @GET("/users/{user}")
    Call<GitProfile> userRepo(
            @Path("user") String user
    );
}
