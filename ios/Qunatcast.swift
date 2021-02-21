import ChoiceMobile
import Foundation

@objc(Qunatcast)
open class Qunatcast:  RCTEventEmitter  {
    
    
    @objc(startChoice:withResolver:withRejecter:)
    func startChoice(apiKey: String, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        ChoiceCmp.shared.startChoice(pcode: apiKey, delegate: QunatcastCallBack());
        resolve("startChoice Start succesfully");
    }
    
    @objc(forceDisplayUI:withRejecter:)
    func forceDisplayUI(resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock){
        ChoiceCmp.shared.forceDisplayUI();
        resolve("forceDisplayUI Started");
        
    }
    
    
    @objc open override func supportedEvents() -> [String] {
          return  ["onCmpError", "onCmpLoaded", "onCmpUIShown", "onGoogleVendorConsentGiven", "onIABVendorConsentGiven", "onNonIABVendorConsentGiven"]
      }
    
}
