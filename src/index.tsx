import { NativeEventEmitter, NativeModules } from 'react-native';
enum Events{
  onCmpError = "onCmpError",
  onCmpLoaded = "onCmpLoaded",
  onCmpUIShown = "onCmpUIShown",
  onGoogleVendorConsentGiven = "onGoogleVendorConsentGiven",
  onIABVendorConsentGiven = "onIABVendorConsentGiven",
  onNonIABVendorConsentGiven = "onNonIABVendorConsentGiven"
};

type QunatcastType = {
  startChoice(apiKey: string): Promise<any>;
  forceDisplayUI(): Promise<any>;
};

const { Qunatcast } = NativeModules;
const eventEmitter = new NativeEventEmitter(Qunatcast);


export const onCmpError = (handler: Function) =>{
  eventEmitter.addListener(Events.onCmpError, data => {
    handler(data);
  })
}

export const onCmpLoaded = (handler: Function) =>{
  eventEmitter.addListener(Events.onCmpLoaded, data => {
    handler(data);
  })
}

export const onCmpUIShown = (handler: Function) =>{
  eventEmitter.addListener(Events.onCmpUIShown, data => {
    handler(data);
  })
}

export const onAdditionalVendorConsentGiven = (handler: Function) =>{
  eventEmitter.addListener(Events.onGoogleVendorConsentGiven, data => {
    handler(data);
  })
}

export const onIABVendorConsentGiven = (handler: Function) =>{
  eventEmitter.addListener(Events.onIABVendorConsentGiven, data => {
    handler(data);
  })
}

export const onNonIABVendorConsentGiven = (handler: Function) =>{
  eventEmitter.addListener(Events.onNonIABVendorConsentGiven, data => {
    handler(data);
  })
}

export default Qunatcast as QunatcastType;
