package com.reactnativequnatcast;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.quantcast.choicemobile.ChoiceCmp;
import com.quantcast.choicemobile.ChoiceCmpCallback;
import com.quantcast.choicemobile.ChoiceCmpViewModel;
import com.quantcast.choicemobile.ChoiceCmpViewModelFactory;
import com.quantcast.choicemobile.core.model.ACData;
import com.quantcast.choicemobile.core.model.TCData;
import com.quantcast.choicemobile.model.ChoiceError;
import com.quantcast.choicemobile.model.NonIABData;
import com.quantcast.choicemobile.model.PingReturn;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


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
     ChoiceCmp choiceCmp =  ChoiceCmp.INSTANCE;
      ChoiceCmp.INSTANCE.startChoice(applicationContext, packageName, apiKey,this);
      promise.resolve("startChoice Start succesfully");
    }catch (Exception e){
      Log.i(logKey,  e.toString());
      promise.reject("error", e.toString());
    }
  }

  @ReactMethod
  public void forceDisplayUI(Promise promise){
    try {
      ChoiceCmp.INSTANCE.forceDisplayUI(reactContext.getCurrentActivity());
      promise.resolve("forceDisplayUI succesfully");


    }catch (Exception e){
      Log.i(logKey,  e.getMessage());
      promise.reject("error", e.toString());
    }
  }


  @Override
  public void onCmpError(@NotNull ChoiceError choiceError) {
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onCmpLoaded(@NotNull PingReturn pingReturn) {
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onCmpUIShown(@NotNull PingReturn pingReturn) {
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onGoogleVendorConsentGiven(@NotNull ACData acData) {
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onIABVendorConsentGiven(@NotNull TCData tcData) {
    Log.i(logKey, "onCmpError");
  }

  @Override
  public void onNonIABVendorConsentGiven(@NotNull NonIABData nonIABData) {
    Log.i(logKey, "onCmpError");
  }
}
