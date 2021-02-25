import ChoiceMobile

@objc(Qunatcast)
open class Qunatcast: RCTEventEmitter,  UIApplicationDelegate , ChoiceCmpDelegate {
    public func cmpDidLoad(info: PingResponse) {
        let payload = [
            "displayStatus": info.accessibilityViewIsModal
        ];
        self.sendEvent(withName: "onCmpLoaded", body: payload);
    }
    
    public func cmpDidShow(info: PingResponse) {
        print("cmpDidShow")
        let payload = [
            "displayStatus": info.accessibilityViewIsModal
        ];
        self.sendEvent(withName: "onCmpUIShown", body:  payload);
    }
    
    public func didReceiveIABVendorConsent(tcData: TCData, updated: Bool) {
        let payload = [
            "cmpStatus": tcData.cmpStatus.rawValue,
            "tc": tcData.tcString,
            "publisherCC": tcData.publisherCC,
            "isServiceSpecific": tcData.isServiceSpecific
        ] as [String : Any];
        
        self.sendEvent(withName: "onIABVendorConsentGiven", body: payload);
        print("didReceiveIABVendorConsent")
    }
    
    public func didReceiveNonIABVendorConsent(nonIabData: NonIABData, updated: Bool) {
        print("didReceiveNonIABVendorConsent")
        let payload = [
            "metaData": nonIabData.description,
            "hashCode": nonIabData.hashValue,
            "HasGlobalScope": nonIabData.shouldGroupAccessibilityChildren,
            "GdprApplies": nonIabData.accessibilityElementsHidden
        ] as [String : Any];
        
        self.sendEvent(withName: "onNonIABVendorConsentGiven", body: payload);
    }
    
    public func didReceiveAdditionalConsent(acData: ACData, updated: Bool) {
        print("didReceiveAdditionalConsent")
        let payload = [
            "ac": acData.acString,
            "accessibilityLabel": acData.accessibilityLabel
        ]
        self.sendEvent(withName: "onGoogleVendorConsentGiven", body: payload);
    }
    
    public func cmpDidError(error: Error) {
        print("onCmpError" + error.localizedDescription)
        let payload = [
            "onCmpError": error.localizedDescription
        ];
        self.sendEvent(withName: "onCmpError", body: payload);
    }
    
    
    
    @objc(startChoice:withResolver:withRejecter:)
    func startChoice(apiKey: String, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        ChoiceCmp.shared.startChoice(pcode: apiKey, delegate:self);
        resolve("startChoice Start succesfully");
    }
    
    @objc(forceDisplayUI:withRejecter:)
    func forceDisplayUI(resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock){
        ChoiceCmp.shared.forceDisplayUI();
        resolve("forceDisplayUI Started");
        
    }
    
    
    @objc open override func supportedEvents() -> [String] {
          return   ["onCmpError", "onCmpLoaded", "onCmpUIShown", "onGoogleVendorConsentGiven", "onIABVendorConsentGiven", "onNonIABVendorConsentGiven"]
      }
    
}
