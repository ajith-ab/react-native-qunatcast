# react-native-qunatcast

Qunatcast API

## Installation

```sh
npm install react-native-qunatcast
```

## Usage

```js
import Qunatcast, { onCmpError, onCmpLoaded, onCmpUIShown, onAdditionalVendorConsentGiven, onIABVendorConsentGiven, onNonIABVendorConsentGiven } from 'react-native-qunatcast';

// ...

 Qunatcast.startChoice("Your-P-Code").then(data => console.log(data)).catch(e=>console.log(e))

 Qunatcast.forceDisplayUI().then((data)=>{
      console.log(data);
    }).catch(e=>{
      console.log(e)
    })



     //EVENTS
    onCmpError((data: any)=>{
      console.log(data);
    })

    onCmpLoaded((data: any)=>{
      console.log(data);
    })

    onCmpUIShown((data: any)=>{
      console.log(data);
    })


    onAdditionalVendorConsentGiven((data: any)=>{
      console.log(data);
    })

    onIABVendorConsentGiven((data: any)=>{
      console.log(data);
    })

    onNonIABVendorConsentGiven((data: any)=>{
      console.log(data);
    })
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
