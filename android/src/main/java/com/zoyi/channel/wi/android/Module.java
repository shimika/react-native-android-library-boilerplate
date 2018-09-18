package com.zoyi.channel.wi.android;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoyi.rx.Subscriber;
import com.zoyi.rx.android.schedulers.AndroidSchedulers;
import com.zoyi.rx.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;

public class Module extends ReactContextBaseJavaModule {

  private boolean debug = false;

  public Module(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ChannelIOSynergy";
  }

  @ReactMethod
  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  @ReactMethod
  public void boot(String pluginKey, String id, String age, String gender) {
    if (pluginKey == null || pluginKey.trim().length() == 0) {
      Log.e("Channel", "Plugin key can not be null or empty");
      return;
    }
    if (id == null || id.trim().length() == 0) {
      Log.e("Channel", "ID can not be null or empty");
      return;
    }

    Map<String, String> form = new HashMap<>();

    form.put("userId", id);

    if (age != null) {
      form.put("age", age);
    }
    if (gender != null) {
      form.put("gender", gender);
    }

    ChannelApiFactory.getApi().bootV2(pluginKey, Utils.form(form))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Void>() {
          @Override
          public void onCompleted() {
          }

          @Override
          public void onError(Throwable throwable) {
            Log.e("Channel", "Failed");

            if (throwable != null) {
              throwable.printStackTrace();
            }
          }

          @Override
          public void onNext(Void aVoid) {
            Log.d("Channel", "Success");
          }
        });
  }
}
