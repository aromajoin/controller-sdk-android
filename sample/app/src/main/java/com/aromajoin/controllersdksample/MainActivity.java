package com.aromajoin.controllersdksample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import com.aromajoin.aromashootercontroller.connection.AromaShooterController;
import com.aromajoin.aromashootercontroller.connection.model.AromaShooter;
import com.aromajoin.aromashootercontroller.ui.activity.ASControllerBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * The class extends the existing activity
 * which contains a bar item menu to go Connection Screen
 *
 * @author Quang Nguyen
 * © Aromajoin Corporation
 */
public class MainActivity extends ASControllerBaseActivity implements View.OnClickListener {

  private final int DEFAULT_DURATION = 3000; // Unit: millisecond
  List<Integer> ports = new ArrayList<>(); // port-to-diffuse list

  //Get the instance of AromaShooterController
  private AromaShooterController aromaShooterController = AromaShooterController.getInstance();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    init();
  }

  private void init() {
    findViewById(R.id.port1Button).setOnClickListener(this);
    findViewById(R.id.port2Button).setOnClickListener(this);
    findViewById(R.id.port3Button).setOnClickListener(this);
    findViewById(R.id.port4Button).setOnClickListener(this);
    findViewById(R.id.port5Button).setOnClickListener(this);
    findViewById(R.id.port6Button).setOnClickListener(this);
  }

  /**
   * Gets ports and trigger diffusing scents
   */
  @Override public void onClick(View view) {
    int viewId = view.getId();
    switch (viewId) {
      case R.id.port1Button:
        ports.add(1);
        break;
      case R.id.port2Button:
        ports.add(2);
        break;
      case R.id.port3Button:
        ports.add(3);
        break;
      case R.id.port4Button:
        ports.add(4);
        break;
      case R.id.port5Button:
        ports.add(5);
        break;
      case R.id.port6Button:
        ports.add(6);
        break;
    }
    new Handler().postDelayed(diffuseTask, 10);
  }

  /**
   * Uses runnable to send command which allows to diffuse multiple ports at the same time.
   */
  List<AromaShooter> aromaShooters;
  private Runnable diffuseTask = new Runnable() {
    @Override public void run() {
      if (ports.size() == 0) {
        ports.clear();  // Clear buffered ports.
        return;
      }
      aromaShooters = aromaShooterController.getConnectedDevices();
      if (aromaShooters == null || aromaShooters.size() == 0) { // check whether there is any connected devices.
        ports.clear();  // Clear buffered ports.
        return;
      }
      // Diffuse scents from selected ports of all connected devices.
      aromaShooterController.diffuse(aromaShooters, DEFAULT_DURATION, 1, convertIntegers(ports));
      ports.clear();
    }
  };

  // A helper method to convert List<Integer> to primitive integer array.
  private int[] convertIntegers(List<Integer> integers) {
    int[] ret = new int[integers.size()];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = integers.get(i);
    }
    return ret;
  }
}
