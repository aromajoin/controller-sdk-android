[English](https://github.com/aromajoin/controller-sdk-android) / [日本語](README-JP.md)

# ControllerSDK - Android版

[ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Aandroid/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Aandroid/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)


**[Aroma Shooter](https://aromajoin.com/hardware/shooters/aroma-shooter-1)との通信に使用されるAromaShooterControllerSDKのAndroid版です。**  

# 目次
1. [対応デバイス](#対応デバイス)  
2. [インストール](#インストール)
3. [使用法](#使用法)
    * [接続する](#接続する)
      * [BLE](#bluetooth-ble)
      * [USB](#usb)
    * [接続されたデバイス](#接続されたデバイス)
    * [香りを拡散する](#香りを拡散する)
    * [拡散を止める](#拡散を止める)
4. [ライセンス](#license)

## 対応デバイス
* Aroma Shooter Bluetooth BLE 
* Aroma Shooter USB

## インストール
Gradle

まず、これを `app/build.gradle`の上に追加してください。
```gradle
repositories {
    maven { url "http://dl.bintray.com/aromajoin/maven" }
}
```
次に、モジュールの`build.gradle`ファイルに`controller-sdk`依存関係を追加します。
```gradle
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:android:2.*.*'
}
```
`gradle.build`で**Java8コンパイル**を有効にしてください。
```gradle
  android {
    //...
    compileOptions {
      sourceCompatibility JavaVersion.VERSION_1_8
      targetCompatibility JavaVersion.VERSION_1_8
    }
  }
```
Snackbar通知を表示するために`Android design support library`を使用しています。あなたのアプリがこれをまだ使用していない場合は、それを含めてください。
```gradle
dependencies {
    // ... other dependencies
    compile "com.android.support:design:27.1.0"
}
```
## 使用法  
### 接続する
BLE接続とUSB接続には小さな違いがあります。
#### Bluetooth BLE
Bluetooth BLE接続を介してアロマシューターで作業している場合、アプリケーションに*接続画面*を持つ3つのオプションがあります。

1. Extend **ASBaseActivity**には、デフォルトの接続画面に移動するためのバーボタンがあります。
2. `Intent`を使用して、デフォルトの接続画面に正常に移動する。
```java
  Intent intent = new Intent(YourCurrentActivity.this, ASConnectionActivity.class);  
  startActivity(intent);
```
3. APIを使用して独自の接続部分を作成する。
	- *AndroidBLEControllerのリファレンスを入手する。*
		```java
		AndroidBLEController controller = AndroidBLEController.getInstance(); 
		```
	- *検索*
		```java
		controller.startScan(context, discoverCallback);
		```  
	　Activity/Fragmentを停止するときにスキャンを止めることを忘れないでください。

		```java
		protected void onPause() {
		  super.onPause();
		  controller.stopScan(context);
		}
		```
	- *接続*
		```java
		controller.connect(aromaShooter, connectCallback);  
		```
	- *切断*
		```java
		controller.disconnect(aromaShooter, disconnectCallback);  
		```

#### USB
- *AndroidUSBControllerオブジェクトを初期化する。*
  ```java
  AndroidUSBController controller =  new AndroidUSBController(usbManager);
  ```
- *検索*
  ```java
  controller.scan(discoverCallback);
  ```  
- *接続*
  ```java
  controller.connect(aromaShooter. connectCallback);  
  ```
- *切断*
  ```java
  controller.disconnect(aromaShooter, disconnectCallback);  
  ```
### 接続されたデバイス
  ```java
  List<AromaShooter> aromaShooters = controller.getConnectedDevices();
  ```

### 香りを拡散する
  ```java
  /**
  * @param duration     拡散持続時間（ミリ秒）。
  * @param booster      ブースターが使用されているかどうかを判定する。(true: より強く拡散する, false: より弱く拡散する)
  * @param ports        カートリッジ番号を拡散する。値：1 ~ 6.
  */
  controller.diffuseAll(duration, booster, ports);
  ```  
### 拡散を止める
  ```java
  /**
   * 接続されているすべてのポートが拡散している場合は、それらを停止します。
   */
  controller.stopAllPorts();
  
  /**
   * 特定のデバイスのすべてのポートを停止します。
   */
  controller.stopAllPorts(AromaShooter aromaShooter);
  ```

**詳細については、このリポジトリをチェックアウトし、[サンプルプロジェクト](https://github.com/aromajoin/controller-sdk-android/tree/master/sample)を参照してください。**  
**問題が発生したり、新機能が必要な場合は、[新しい問題](https://github.com/aromajoin/controller-sdk-android/issues)を作成してください。**

## ライセンス
[こちら](https://github.com/aromajoin/controller-sdk-/blob/master/LICENSE.md)を参照してください。
