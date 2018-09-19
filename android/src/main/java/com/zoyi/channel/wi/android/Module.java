package com.zoyi.channel.wi.android;

import android.os.AsyncTask;
import com.facebook.react.bridge.*;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

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
  public void getDeviceId(Callback callback) {
    new GoogleAppIdTask(callback, Utils.getWId()).execute();
  }

  private class GoogleAppIdTask extends AsyncTask<Void, Void, String> {
    private Callback callback;
    private String wId;

    public GoogleAppIdTask(Callback callback, String wId) {
      this.callback = callback;
      this.wId = wId;
    }

    protected String doInBackground(final Void... params) {
      try {
        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getReactApplicationContext());
        if (advertisingIdInfo != null && !advertisingIdInfo.isLimitAdTrackingEnabled()) {
          return advertisingIdInfo.getId();
        }
        return null;
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      return null;
    }

    protected void onPostExecute(String adId) {
      if (callback != null) {
        callback.invoke(wId, adId);
      }
    }
  }
}
