package com.reactnativequnatcast;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.quantcast.choicemobile.ChoiceCmp;
import com.quantcast.choicemobile.ChoiceCmpCallback;
import com.quantcast.choicemobile.core.model.TCData;
import com.quantcast.choicemobile.data.storage.SharedStorageKeys;
import com.quantcast.choicemobile.di.ServiceLocator;
import com.quantcast.choicemobile.model.ChoiceError;
import com.quantcast.choicemobile.model.NonIABData;
import com.quantcast.choicemobile.model.PingReturn;
import com.quantcast.choicemobile.presentation.privacy.PrivacyDialog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class QunatcastModule extends ReactContextBaseJavaModule implements ChoiceCmpCallback {
  public final String logKey = "Qunatcast";

  private final ReactApplicationContext reactContext;
  private final  Application applicationContext;
  private final String packageName;
  private final Activity activity;

  public QunatcastModule(ReactApplicationContext reactContext)  {
    super(reactContext);
    this.reactContext = reactContext;
    this.applicationContext = (Application) reactContext.getApplicationContext();
    this.packageName = reactContext.getPackageName();
    this.activity = reactContext.getCurrentActivity();
  }

  @Override
  public String getName() {
    return "Qunatcast";
  }

  @ReactMethod
  public void startChoice(String apiKey, Promise promise){
    try {
      ChoiceCmp.INSTANCE.startChoice(applicationContext, applicationContext, apiKey,this);
      promise.resolve("startChoice Start succesfully");
    }catch (Exception e){
      Log.i(logKey,  e.toString());
      promise.reject("error", e.toString());
    }
  }

  @ReactMethod
  public void forceDisplayUI(Promise promise){
    try {
      PrivacyDialog.Companion.setTAG(null);
      ServiceLocator.INSTANCE.getStorage().setStringPreference(SharedStorageKeys.TC_STRING, "");
      ChoiceCmp.INSTANCE.forceDisplayUI(reactContext.getCurrentActivity());
      promise.resolve("forceDisplayUI succesfully");
    }catch (Exception e){
      Log.i(logKey,  e.getMessage());
      promise.reject("error", e.toString());
    }
  }


  @Override
  public void onCmpError(@NotNull ChoiceError choiceError) {
    WritableMap params = Arguments.createMap();
    params.putString("onCmpError", choiceError.getMessage() );
    this.sendEvent(reactContext, "onCmpError", params);
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onCmpLoaded(@NotNull PingReturn pingReturn) {
    WritableMap params = Arguments.createMap();
    try {
      params.putString("displayStatus",  pingReturn.getDisplayStatus().getValue().toString());
    }catch (Exception e){
      Log.i(logKey, e.toString());
    }
    this.sendEvent(reactContext, "onCmpLoaded", params);
    Log.i(logKey, "onCmpLoaded");
  }

  @Override
  public void onCmpUIShown(@NotNull PingReturn pingReturn) {
    WritableMap params = Arguments.createMap();
    try {
      params.putString("displayStatus",  pingReturn.getDisplayStatus().getValue().toString());
    }catch (Exception e){
      Log.i(logKey, e.toString());
    }
    params.putString("onCmpUIShown", pingReturn.toString() );
    this.sendEvent(reactContext, "onCmpUIShown", params);
    Log.i(logKey, "onCmpUIShown");
  }


  @Override
  public void onIABVendorConsentGiven(@NotNull TCData tcData) {
    WritableMap params = Arguments.createMap();
    try {
      params.putString("cmpStatus", tcData.getCmpStatus().toString());
      params.putString("tc", tcData.getTcString().toString());
      params.putString("publisherCC", tcData.getPublisherCC().toString());
      params.putBoolean("isServiceSpecific", tcData.isServiceSpecific());
    }catch (Exception e){
        Log.i(logKey, e.toString());
    }
    params.putString("onIABVendorConsentGiven", tcData.toString() );
    this.sendEvent(reactContext, "onIABVendorConsentGiven", params);
    Log.i(logKey, "onIABVendorConsentGiven");
  }

  @Override
  public void onNonIABVendorConsentGiven(@NotNull NonIABData nonIABData) {
    WritableMap params = Arguments.createMap();
    try {
      params.putInt("hashCode", nonIABData.hashCode());
      params.putBoolean("HasGlobalScope", nonIABData.getHasGlobalScope());
      params.putBoolean("GdprApplies",  nonIABData.getGdprApplies());
    }catch (Exception e){
      Log.i(logKey, e.toString());
    }
    params.putString("onNonIABVendorConsentGiven", nonIABData.toString() );
    this.sendEvent(reactContext, "onNonIABVendorConsentGiven", params);
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
