package wallpaper.myapplication.mywallpaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APInterface {
    String BASE_URL="https://api.pexels.com/v1/ ";
    @Headers("Authorization: "+ APIutilities.Authkey)
    @GET("curated")
    Call<photosClass> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );
    @Headers("Authorization: "+ APIutilities.Authkey)
    @GET("search")
     Call<photosClass> getImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
