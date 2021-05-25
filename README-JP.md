[English](https://github.com/aromajoin/controller-sdk-android) / [日本語](README-JP.md)

# ControllerSDK - Android版

[ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Aandroid/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Aandroid/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html) 
[![Join the community on Spectrum](https://withspectrum.github.io/badge/badge.svg)](https://spectrum.chat/aromajoin-software/)


**[Aroma Shooter](https://aromajoin.com/products/aroma-shooter)との通信に使用されるAromaShooterControllerSDKのAndroid版です。**  

# 目次
1. [対応デバイス](#対応デバイス)
2. [前提条件](#前提条件)
3. [インストール](#インストール)
4. [使用法](#使用法)
    * [接続する](#接続する)
      * [Bluetooth](#bluetooth)
      * [USB](#usb)
    * [接続されたデバイス](#接続されたデバイス)
    * [香りを噴射する](#香りを噴射する)
    * [噴射を止める](#噴射を止める)
5. [ライセンス](#ライセンス)

## 対応デバイス
* Aroma Shooter Bluetoothタイプ
* Aroma Shooter USBタイプ

## 前提条件
* Android 4.4以降
* USB接続の場合：USB OTGが必要です

## インストール
### #1。 ファイル
* [2つのファイル　controller-sdk-core.jarとcontroller-sdk.aar]（https://github.com/aromajoin/controller-sdk-android/releases/tag/v2.4.4）をダウンロードしてください。
* 次に、「app/libs/」フォルダーにコピーする。
* アプリのbuild.gradleファイルを更新して、これらの依存関係を含めます。
```gradle
dependencies {
    // ... other dependencies
  implementation files("libs/controller-sdk.aar")
  implementation files("libs/controller-sdk-core.jar")
}
```

### #2。 Maven Central経由（JCenter Bintray）- 非推奨
初めに以下のコードを `app/build.gradle`ファイルの上に追加してください。
```gradle
repositories {
    maven { url "http://dl.bintray.com/aromajoin/maven" }
}
```
次にモジュールの`build.gradle`ファイルに`controller-sdk`依存関係を追加します。
```gradle
dependencies {
    // ... other dependencies
    implementation 'com.aromajoin.sdk:core:2.5.1'
    implementation 'com.aromajoin.sdk:android:2.4.0'
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
Snackbar通知を表示するために`Android design support library`を使用しています。アプリがこれをまだ使用していない場合は、それを含めてください。
```gradle
dependencies {
    // ... other dependencies
    compile "com.android.support:design:27.1.0"
}
```
## 使用法  
### 接続する
Bluetooth接続とUSB接続ではわずかな違いがあります。
#### Bluetooth
Bluetooth接続を介してアロマシューターで作業している場合、アプリケーションに接続画面を表示するには、3つの選択肢があります。

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
	　Activity/Fragmentを停止するときに、スキャンを止めることを忘れないでください。

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
注：現在、制御できるアロマシューターは1つだけです。
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

### 香りを噴射する
  ```java
  /**
  * @param duration     噴射持続時間（ミリ秒）。
  * @param booster      ブースターが使用されているかどうかを判定する。(true: より強く噴射する, false: より弱く噴射する)
  * @param ports        カートリッジ番号を噴射する。値：1 ~ 6.
  */
  controller.diffuseAll(duration, booster, ports);
  ```  
* AS2（Aroma Shooter 2）デバイスのみのディフューザー香りメソッド
```java
/**
 * AS2のすべての接続デバイスからの特定のポートでの香りの拡散
 * @param duration              拡散時間（ミリ秒）
 * @param boosterIntensity      ブースターポート。値： 0~100.
 * @param fanIntensity          ファンポート。値： 0~100.
 * @param ports                 ポートの配列。値: Port(portNumber, portIntensity)
 */
 
controller.diffuseAll(duration, boosterIntensity, fanIntensity, ports);
```
### 噴射を止める
  ```java
  /**
   * 接続されているすべてのポートが噴射している場合は、それらを停止します。
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
