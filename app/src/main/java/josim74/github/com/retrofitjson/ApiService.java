package josim74.github.com.retrofitjson;

import josim74.github.com.retrofitjson.model.RootObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by JosimUddin on 23/06/2018.
 */

public interface ApiService {
    String BASE_URL = "https://api.flickr.com/services/feeds/";


    @Headers("Content-Type: application/json")
    @GET("photos_public.gne?format=json&tags=dog&nojsoncallback=1#")
    Call<RootObject> getData();
}
