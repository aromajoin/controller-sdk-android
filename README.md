# Controller SDK for Android

[ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Aandroid/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Aandroid/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)


**The Android version of AromaShooterController library which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/hardware/shooters/aroma-shooter-1)**  

# Table of Contents
1. [Supported devices](#supported-devices)  
2. [Dependency](#dependency)
3. [Usage](#usage)
    * [Connect devices](#connect)
    	* [BLE](#bluetooth-ble)
        * [USB](#usb)
    * [Diffuse scents](#diffuse-scents)
    * [Proguard](#proguard)
4. [License](#license)

---

## Supported devices
* Aroma Shooter Bluetooth BLE version 
* Aroma Shooter USB version
---

## Dependency  

The Gradle dependency is available via Maven Central. 

Firstly, add this on top of your app/build.gradle:

```gradle
repositories {
    maven { url "http://dl.bintray.com/aromajoin/maven" }
    maven { url "https://jitpack.io" } // We use a third-party library hosted on jitpack
}
```


Then, add the `controller-sdk` dependence in your module's `build.gradle` file:

```gradle
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:android:2.0.0-rc1'
}
```

---

## Usage  
### Connect
There are small differences between BLE connection and USB connetion.
#### Bluetooth BLE
In case you are working with Aroma Shooter via Bluetooth BLE connection.
There are 3 options to have *connection screen* in your application.  

* Extend **ASBaseActivity** which has a bar button to go to the default connection Screen.  

* Use Intent to go to the default connection screen normally

```java
  Intent intent = new Intent(YourCurrentActivity.this, ASConnectionActivity.class);  
  startActivity(intent);
```

* Write your own connection part using APIs  
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
		aromaShooterController.connect(aromaShooter, connectCallback);  
		```
	- *Disconnect*  
		```java
		aromaShooterController.disconnect(aromaShooter, disconnectCallback);  
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
  aromaShooterController.connect(aromaShooter. connectCallback);  
  ```
- *Disconnect*  
  ```java
  aromaShooterController.disconnect(aromaShooter, disconnectCallback);  
  ```

### Diffuse scents 

  ```java
  /**
   * Diffuses aroma at device's ports.
   *
   * @param aromaShooters device to communicate.
   * @param duration diffusing duration in milliseconds.
   * @param booster whether booster is used or not.
   * @param ports port numbers to diffuse aroma.
   */
  void diffuse(List<AromaShooter> aromaShooters, int duration, boolean booster, int... ports);
  ```  
### Stop diffusing
  ```java
  /**
   * Stops all ports if they are diffusing aroma.
   *
   * @param aromaShooter device to communicate.
   */
  void stopAllPorts(AromaShooter aromaShooter);

  /**
   * Stops all ports of current connected devices if they are diffusing.
   */
  void stopAllPorts();
  ```

**For more information, please checkout this repository and refer to the [sample project](https://github.com/aromajoin/controller-sdk-android/tree/master/sample).**  
**If you get any issues or require any new features, please create a [new issue](https://github.com/aromajoin/controller-sdk-android/issues).**

### Proguard
[EventBus](https://github.com/greenrobot/EventBus) is utilized inside Controller SDK, so if you use Proguard, make sure to add the following lines in your Proguard file.
```
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
```
---
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
