//
//  QunatcastCallBack.swift
//  react-native-qunatcast
//
//  Created by Ajith on 21/02/21.
//

import ChoiceMobile

class QunatcastCallBack: RCTEventEmitter,  ChoiceCmpDelegate {
    
    func cmpDidLoad(info: PingResponse) {
     NSLog("@%sssss", "onCmpLoaded")
        self.sendEvent(withName: "cmpDidLoad", body: info);
    }
    
    func cmpDidShow(info: PingResponse) {
        self.sendEvent(withName: "onCmpUIShown", body: info);
        NSLog("@%sssss", "cmpDidShow")
  
    }
    
    func didReceiveIABVendorConsent(tcData: TCData, updated: Bool) {
        self.sendEvent(withName: "onIABVendorConsentGiven", body:tcData);
        NSLog("@%sssss", "didReceiveIABVendorConsent")
    }
    
    func didReceiveNonIABVendorConsent(nonIabData: NonIABData, updated: Bool) {
        self.sendEvent(withName: "onNonIABVendorConsentGiven", body:nonIabData);
        NSLog("@%sssss", "didReceiveNonIABVendorConsent")
    }
    
    func didReceiveAdditionalConsent(acData: ACData, updated: Bool) {
        self.sendEvent(withName: "onGoogleVendorConsentGiven", body:acData);
        NSLog("@%sssss", "didReceiveAdditionalConsent")
    }
    
    func cmpDidError(error: Error) {
        self.sendEvent(withName: "cmpDidError", body:error);
        NSLog("@%sssss", "onCmpError")
    }
    
    
    @objc open override func supportedEvents() -> [String] {
          return  ["onCmpError", "onCmpLoaded", "onCmpUIShown", "onGoogleVendorConsentGiven", "onIABVendorConsentGiven", "onNonIABVendorConsentGiven"]
      }
}
