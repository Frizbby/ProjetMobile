package com.esiea.projetmobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyApi {

    @GET("/pokemon/ditto")
    Call<RestGIPHYResponse> getGiphyResponse();

  /*  @GET("api.giphy.com/v1/gifs/search")
    Call<List<RestGIPHYResponse>> getGiphyCat(@Query("cat")String value);*/
}
