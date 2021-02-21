//
//  QunatcastCallBack.swift
//  react-native-qunatcast
//
//  Created by Ajith on 21/02/21.
//

import ChoiceMobile

class QunatcastCallBack:  ,  ChoiceCmpDelegate {
    func cmpDidLoad(info: PingResponse) {
     NSLog("@%sssss", "cmpDidLoad")
    }
    
    func cmpDidShow(info: PingResponse) {
        NSLog("@%sssss", "cmpDidShow")
  
    }
    
    func didReceiveIABVendorConsent(tcData: TCData, updated: Bool) {
        NSLog("@%sssss", "didReceiveIABVendorConsent")
    }
    
    func didReceiveNonIABVendorConsent(nonIabData: NonIABData, updated: Bool) {
        NSLog("@%sssss", "didReceiveNonIABVendorConsent")
    }
    
    func didReceiveAdditionalConsent(acData: ACData, updated: Bool) {
        NSLog("@%sssss", "didReceiveAdditionalConsent")
    }
    
    func cmpDidError(error: Error) {
        NSLog("@%sssss", "cmpDidError")
    }
    
}
