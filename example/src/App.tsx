import React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import Qunatcast, { onCmpError, onCmpLoaded, onCmpUIShown, onAdditionalVendorConsentGiven, onIABVendorConsentGiven, onNonIABVendorConsentGiven } from 'react-native-qunatcast';


export default function App() {

  React.useEffect(() => {
    Qunatcast.startChoice("s497uf3SngdapB").then(data => console.log(data)).catch(e=>console.log(e))


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
  }, []);

  const forceDisplayUi = () => {
    Qunatcast.forceDisplayUI().then((data)=>{
      console.log(data);
    }).catch(e=>{
      console.log(e)
    })
  }

  return (
    <View style={styles.container}>
      <Text>Result:</Text>
      <Button title="forceDisplayUi" onPress={forceDisplayUi} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
