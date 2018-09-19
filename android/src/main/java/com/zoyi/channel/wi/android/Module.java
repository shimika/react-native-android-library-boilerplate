package com.zoyi.channel.wi.android;

import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
  public void getDeviceId(Promise promise) {
    promise.resolve(Utils.getWId());
  }

  @ReactMethod
  public void version() {
    ChannelApiFactory.getApi().version()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Void>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(Void aVoid) {

          }
        });
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

    Map<String, Object> form = new HashMap<>();

    form.put("userId", id);

    if (age != null || gender != null) {
      Map<String, String> profileMap = new HashMap<>();

      if (age != null) {
        profileMap.put("age", age);
      }
      if (gender != null) {
        profileMap.put("gender", gender);
      }

      form.put("profile", profileMap);
    }

    String wId = Utils.getWId();
    if (wId != null) {
      Map<String, String> sysProfileMap = new HashMap<>();

      sysProfileMap.put("$wId", wId);

      form.put("sysProfile", sysProfileMap);
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
            if (throwable != null) {
              Log.e("Channel", "Failed: " + throwable.getMessage());
              throwable.printStackTrace();
            } else {
              Log.e("Channel", "Failed");
            }
          }

          @Override
          public void onNext(Void aVoid) {
            Log.d("Channel", "Success");
          }
        });
  }
}
