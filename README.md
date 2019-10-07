[English](https://github.com/aromajoin/controller-sdk-android) / [日本語](README-JP.md)

# Controller SDK for Android

[ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Aandroid/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Aandroid/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)


**The Android version of AromaShooterController library which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/products/aroma-shooter)**.

# Table of Contents
1. [Supported devices](#supported-devices)  
2. [Installation](#installation)
3. [Usage](#usage)
    * [Connect](#connect)
      * [BLE](#bluetooth-ble)
      * [USB](#usb)
    * [Get connected devices](#get-connected-devices)
    * [Diffuse scents](#diffuse-scents)
    * [Stop diffusing](#stop-diffusing)
4. [License](#license)

## Supported devices
* Aroma Shooter Bluetooth BLE version 
* Aroma Shooter USB version

## Installation
The Gradle dependency is available via Maven Central. 

Firstly, add this on top of your app/build.gradle:
```gradle
repositories {
    maven { url "http://dl.bintray.com/aromajoin/maven" }
}
```
Then, add the `controller-sdk` dependence in your module's `build.gradle` file:
```gradle
dependencies {
    // ... other dependencies
    implementation 'com.aromajoin.sdk:core:2.3.0'
    implementation 'com.aromajoin.sdk:android:2.2.1'
}
```
Make sure that you enable **Java 8 compile** in your `gradle.build`.
```gradle
  android {
    //...
    compileOptions {
      sourceCompatibility JavaVersion.VERSION_1_8
      targetCompatibility JavaVersion.VERSION_1_8
    }
  }
```
And we use `Android design support library` to show Snackbar notification, please include it if your app has not used this yet.
```gradle
dependencies {
    // ... other dependencies
    implementation "com.android.support:design:27.1.0"
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

The Apache License (Apache)

    Copyright (c) 2017 Aromajoin Corporation

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
