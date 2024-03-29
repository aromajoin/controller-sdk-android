[English](https://github.com/aromajoin/controller-sdk-android) / [日本語](README-JP.md)

# Controller SDK for Android

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.aromajoin.sdk/android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.aromajoin.sdk/android)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html) 
[![Join the community on Spectrum](https://withspectrum.github.io/badge/badge.svg)](https://spectrum.chat/aromajoin-software/)


**The Android version of AromaShooterController library which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/products/aroma-shooter)**.

# Table of Contents
1. [Supported devices](#supported-devices)  
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Usage](#usage)
    * [Connect](#connect)
      * [BLE](#bluetooth-ble)
      * [USB](#usb)
    * [Get connected devices](#get-connected-devices)
    * [Diffuse scents](#diffuse-scents)
    * [Stop diffusing](#stop-diffusing)
5. [License](#license)

## Supported devices
* Aroma Shooter Bluetooth BLE version 
* Aroma Shooter USB version

## Prerequisites
* Android 4.4 and later
* For USB connection: USB OTG is required

## Installation

The Gradle dependency is available via Maven Central. 

Please add the `controller-sdk` dependence in your module's `build.gradle` file:
```gradle
dependencies {
    implementation "com.aromajoin.sdk:core:2.5.2"
    implementation "com.aromajoin.sdk:android:2.4.6"
}
```

## Usage  
### Connect
There are small differences between BLE connection and USB connetion.
#### Bluetooth BLE
In case you are working with Aroma Shooter via Bluetooth BLE connection.
There are 3 options to have *connection screen* in your application.  

1. Extend **ASBaseActivity** which has a bar button to go to the default connection Screen
2. Use Intent to go to the default connection screen normally
```java
  Intent intent = new Intent(YourCurrentActivity.this, ASConnectionActivity.class);  
  startActivity(intent);
```
3. Write your own connection part using APIs  
	- *Get the reference of AndroidBLEController*
		```java
		AndroidBLEController controller = AndroidBLEController.getInstance(); 
		```
	- *Discover*
		```java
		controller.startScan(context, discoverCallback);
		```  
		Don't forget to stop scanning when pause or stop activity/fragment:  

		```java
		protected void onPause() {
		  super.onPause();
		  controller.stopScan(context);
		}
		```
	- *Connect*
		```java
		controller.connect(aromaShooter, connectCallback);  
		```
	- *Disconnect*
		```java
		controller.disconnect(aromaShooter, disconnectCallback);  
		```

#### USB
Note: currently, it can control only 1 Aroma Shooter. 

- *Initialize an AndroidUSBController object*
  ```java
  AndroidUSBController controller =  new AndroidUSBController(usbManager);
  ```
- *Discover*
  ```java
  controller.scan(discoverCallback);
  ```  
- *Connect*
  ```java
  controller.connect(aromaShooter. connectCallback);  
  ```
- *Disconnect*
  ```java
  controller.disconnect(aromaShooter, disconnectCallback);  
  ```
### Get connected devices
  ```java
  List<AromaShooter> aromaShooters = controller.getConnectedDevices();
  ```

### Diffuse scents
  ```java
  /**
   * Diffuses aroma at device's ports.
   *
   * @param duration diffusing duration in milliseconds.
   * @param booster whether booster is used or not.
   * @param ports port numbers to diffuse aroma.
   */
  controller.diffuseAll(duration, booster, ports);
  ```  
* Diffuse scents method for AS2 (AromaShooter 2) devices only
```java
/**
 * Diffuses aroma at specific ports from all connected devices.
 * @param duration              diffusing duration in milliseconds.
 * @param boosterIntensity      booster port. Value: 0~100.
 * @param fanIntensity          fan port. Value: 0~100.
 * @param ports                 array of ports. Value: Port(portNumber, portIntensity)
 */
controller.diffuseAll(duration, boosterIntensity, fanIntensity, ports);
```
### Stop diffusing
  ```java
  /**
   * Stops all ports of current connected devices if they are diffusing.
   */
  controller.stopAllPorts();
  
  /**
   * Stops all ports if they are diffusing aroma.
   *
   * @param aromaShooter device to communicate.
   */
  controller.stopAllPorts(AromaShooter aromaShooter);
  ```


**For more information, please checkout this repository and refer to the [sample project](https://github.com/aromajoin/controller-sdk-android/tree/master/sample).**  
**If you get any issues or require any new features, please create a [new issue](https://github.com/aromajoin/controller-sdk-android/issues).**

## License  
Please check the [LICENSE](/LICENSE.md) file for the details.
