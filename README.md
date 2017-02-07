# Controller SDK for Android
---------
[ ![Download](https://api.bintray.com/packages/quangnguyen/maven/controller-sdk/images/download.svg) ](https://bintray.com/quangnguyen/maven/controller-sdk/_latestVersion)

**An Android version of AromaShooterController library which is used to communicate with Aroma Shooter devices**  

##Table of Contents
1. [Dependency](https://github.com/aromajoin/controller-sdk-android#dependency)
2. [Usage](https://github.com/aromajoin/controller-sdk-android#usage)
    * [Connect devices](https://github.com/aromajoin/controller-sdk-android#connect-devices)
    * [Diffuse scents](https://github.com/aromajoin/controller-sdk-android#diffuse-scents)
3. [License](https://github.com/aromajoin/controller-sdk-android#license)

###Dependency  

The Gradle dependency is available via jCenter. 
Add this in your module's `build.gradle` file:

```gradle
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:controller:1.0.0'
}
```
If it doesn't resolve through jCenter, please add this to your app/build.gradle file:

```gradle
repositories{
    maven {
        url  "http://dl.bintray.com/quangnguyen/maven"
    }
}
```

###Usage  
####Connect devices
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
		

####Diffuse scents 
Using *Diffuse APIs*  :
```java
aromaShooterController.diffuse(aromaShooters, durration, speed, ports);
``` 

###License  

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
