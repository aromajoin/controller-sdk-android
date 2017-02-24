# Controller SDK for Android

[ ![Download](https://api.bintray.com/packages/quangnguyen/maven/controller-sdk/images/download.svg) ](https://bintray.com/quangnguyen/maven/controller-sdk/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)


**The Android version of AromaShooterController library which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/hardware/shooters/aroma-shooter-1)**  

# Table of Contents
1. [Supported devices](https://github.com/aromajoin/controller-sdk-android#supported-devices)  
2. [Dependency](https://github.com/aromajoin/controller-sdk-android#dependency)
3. [Usage](https://github.com/aromajoin/controller-sdk-android#usage)
    * [Connect devices](https://github.com/aromajoin/controller-sdk-android#connect-devices)
    * [Diffuse scents](https://github.com/aromajoin/controller-sdk-android#diffuse-scents)
4. [License](https://github.com/aromajoin/controller-sdk-android#license)

---

## Supported devices
* Aroma Shooter Bluetooth version 

---

## Dependency  

The Gradle dependency is available via Maven Central. 

Firstly, add this to your app/build.gradle `repositories`:

```gradle
maven {
    url  "http://dl.bintray.com/quangnguyen/maven"
}
```

Then, add the `controller-sdk` dependence in your module's `build.gradle` file:

```gradle
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:controller:1.0.0'
}
```

---

## Usage  

### Connect devices
There are 3 options to have *connection screen* in your application.  

* Extend **ASControllerBaseActivity** which has a bar button to go to the default connection Screen.  

* Use Intent to go to the default connection screen normally

```java
	Intent intent = new Intent(YourCurrentActivity.this, ASControllerConnectionActivity.class);  
	startActivity(intent);
```

* Write your own connection part using APIs  
    - *Get the reference of AromaShooterController*
    ```java
    AromaShooterController aromaShooterController = AromaShooterController.getInstance(); 
    ```
    - *Discover*  
    	```java
		aromaShooterController.startScanning(getApplicationContext());
		```  
		
		Don't forget to stop scanning when pause or stop activity/fragment:  
		```java
		protected void onPause() {
			super.onPause();
			aromaShooterController.stopScanning(getApplicationContext());
		}
		```
    - *Connect*  
	 	Multiple devices are allowed to connect at the same time.  
		
		```java
		aromaShooterController.connectDevice(aromaShooterList);  
		```
    - Disconnect:  
	 
	 	```java
		aromaShooterController.disconnectDevice(aromaShooter);  
		```
		

### Diffuse scents 

Using *Diffuse APIs*  :
```java
aromaShooterController.diffuse(aromaShooters, durration, speed, ports);
``` 
**For more information, please checkout this repository and refer to the [sample project](https://github.com/aromajoin/controller-sdk-android/tree/master/sample).**  
**If you get any issues or require any new features, please create a [new issue](https://github.com/aromajoin/controller-sdk-android/issues).**

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
