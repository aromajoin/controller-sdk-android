package com.aromajoin.controllersdksample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The screen which navigates to USB and BLE device control screen.
 */
public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void chooseBLE(View v) {
    Intent intent = new Intent(this, BLEActivity.class);
    startActivity(intent);
  }

  public void chooseUSB(View v) {
    Intent intent = new Intent(this, USBActivity.class);
    startActivity(intent);
  }
}
