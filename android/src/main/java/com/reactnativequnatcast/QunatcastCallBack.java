package com.reactnativequnatcast;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.quantcast.choicemobile.ChoiceCmpCallback;
import com.quantcast.choicemobile.core.model.ACData;
import com.quantcast.choicemobile.core.model.TCData;
import com.quantcast.choicemobile.model.ChoiceError;
import com.quantcast.choicemobile.model.NonIABData;
import com.quantcast.choicemobile.model.PingReturn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class QunatcastCallBack implements ChoiceCmpCallback {

  public final String logKey = "Qunatcast";
  private ReactApplicationContext mReactContext;

  public QunatcastCallBack(ReactApplicationContext reactApplicationContext){
    mReactContext = reactApplicationContext;
  }

  @Override
  public void onCmpError(@NotNull ChoiceError choiceError) {
    WritableMap params = Arguments.createMap();
    params.putString("onCmpError", choiceError.getMessage() );
    this.sendEvent(mReactContext, "onCmpError", params);
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onCmpLoaded(@NotNull PingReturn pingReturn) {
    WritableMap params = Arguments.createMap();
    params.putString("onCmpLoaded", pingReturn.toString() );
    this.sendEvent(mReactContext, "onCmpLoaded", params);
    Log.i(logKey, "onCmpLoaded");
  }

  @Override
  public void onCmpUIShown(@NotNull PingReturn pingReturn) {
    WritableMap params = Arguments.createMap();
    params.putString("onCmpUIShown", pingReturn.toString() );
    this.sendEvent(mReactContext, "onCmpUIShown", params);
    Log.i(logKey, "onCmpUIShown");
  }

  @Override
  public void onGoogleVendorConsentGiven(@NotNull ACData acData) {
    WritableMap params = Arguments.createMap();
    params.putString("onGoogleVendorConsentGiven", acData.toString() );
    this.sendEvent(mReactContext, "onGoogleVendorConsentGiven", params);
    Log.i(logKey,   acData.getAcString());
  }

  @Override
  public void onIABVendorConsentGiven(@NotNull TCData tcData) {
    WritableMap params = Arguments.createMap();
    params.putString("onIABVendorConsentGiven", tcData.toString() );
    this.sendEvent(mReactContext, "onIABVendorConsentGiven", params);
    Log.i(logKey, "onIABVendorConsentGiven");
  }

  @Override
  public void onNonIABVendorConsentGiven(@NotNull NonIABData nonIABData) {
    WritableMap params = Arguments.createMap();
    params.putString("onNonIABVendorConsentGiven", nonIABData.toString() );
    this.sendEvent(mReactContext, "onNonIABVendorConsentGiven", params);
    Log.i(logKey, "onNonIABVendorConsentGiven");
  }


  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }


}
