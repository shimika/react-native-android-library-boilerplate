package com.zoyi.channel.wi.android;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class Module extends ReactContextBaseJavaModule {

  public Module(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ChannelIOSynergy";
  }

  @ReactMethod
  public void boot(String age, String gender) {
    boot(age, gender, false);
  }

  @ReactMethod
  public void boot(String age, String gender, boolean debug) {
    Log.e("Boot", age + " " + gender + " " + debug);
  }
}
