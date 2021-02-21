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
import com.quantcast.choicemobile.ChoiceCmpViewModel;
import com.quantcast.choicemobile.ChoiceCmpViewModelFactory;
import javax.inject.Inject;


public class QunatcastModule extends ReactContextBaseJavaModule{
  public final String logKey = "Qunatcast";

  private final ReactApplicationContext reactContext;
  private final  Application applicationContext;
  private final String packageName;
  private final Activity activity;

  public QunatcastModule(ReactApplicationContext reactContext) {
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
      ChoiceCmp.INSTANCE.startChoice(applicationContext, packageName, apiKey, new QunatcastCallBack(reactContext));
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


}
