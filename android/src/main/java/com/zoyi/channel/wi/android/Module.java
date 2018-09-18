package com.zoyi.channel.wi.android;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class Module extends ReactContextBaseJavaModule {

  private boolean debug = true;

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
  public void boot(String age, String gender) {
    Log.e("Boot", age + " " + gender + " " + debug + " " + Utils.getWId());
  }
}
