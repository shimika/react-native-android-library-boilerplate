package com.zoyi.channel.wi.android;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

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
    String REST_END_POINT = "https://api.channel.io/";

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    OkHttpClient client = clientBuilder
        .hostnameVerifier(new HostnameVerifier() {
          @Override
          public boolean verify(String hostname, SSLSession session) {
            return true;
          }
        })
        .build();

    return new Retrofit.Builder()
        .baseUrl(REST_END_POINT)
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(ChannelApi.class);
  }
}
