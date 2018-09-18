package com.zoyi.channel.wi.android;

import com.zoyi.okhttp3.OkHttpClient;
import com.zoyi.retrofit2.Retrofit;
import com.zoyi.retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by mika on 2018. 9. 18..
 */

public class ChannelApiFactory {
  public static ChannelApi channelApi;

  public static ChannelApi getApi() {
    if (channelApi == null) {
      channelApi = ChannelApiFactory.create();
    }
    return channelApi;
  }

  private static ChannelApi create() {
    String REST_END_POINT = "https://52.79.73.249/";

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    OkHttpClient client = clientBuilder.build();

    return new Retrofit.Builder()
        .baseUrl(REST_END_POINT)
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(ChannelApi.class);
  }
}
