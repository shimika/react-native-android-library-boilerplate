package com.zoyi.channel.wi.android;

import okhttp3.RequestBody;
import retrofit2.http.*;
import rx.Observable;

import java.util.Map;

/**
 * Created by mika on 2018. 9. 18..
 */

public interface ChannelApi {
  @Headers({"Content-Type: application/json"})
  @POST("/app/plugins/{key}/boot/v2")
  Observable<Void> bootV2(@Path("key") String key, @Body RequestBody body);
}
