package com.geekbrains.lesson2.data.network;



import com.geekbrains.lesson2.data.model.ArticleResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Api {

    @GET("v2/top-headlines?sources=techcrunch&apiKey=6c0bf4b64c3c4755b9fe91879368b157")
    Single<ArticleResponse> getCrypto();
}
