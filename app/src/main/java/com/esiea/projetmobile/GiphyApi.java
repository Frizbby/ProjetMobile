package com.esiea.projetmobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyApi {

    @GET("/v1/gifs/search?api_key=oCZPRvBBJvrZ56WFhBiBYPpY9NL5utJW&q=cat&limit=5&offset=0&rating=G&lang=en")
    Call<RestGIPHYResponse> getGiphyResponse();

  /*  @GET("api.giphy.com/v1/gifs/search")
    Call<List<RestGIPHYResponse>> getGiphyCat(@Query("cat")String value);*/
}
