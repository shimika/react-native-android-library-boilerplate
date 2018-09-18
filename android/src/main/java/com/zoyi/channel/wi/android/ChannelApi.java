package com.zoyi.channel.wi.android;

import com.zoyi.okhttp3.RequestBody;
import com.zoyi.retrofit2.http.*;
import com.zoyi.rx.Observable;

import java.util.Map;

/**
 * Created by mika on 2018. 9. 18..
 */

public interface ChannelApi {
  @Headers({"Content-Type: application/json"})
  @POST("/app/plugins/{key}/boot/v2")
  Observable<Void> bootV2(@Path("key") String key, @Body RequestBody body);
}
