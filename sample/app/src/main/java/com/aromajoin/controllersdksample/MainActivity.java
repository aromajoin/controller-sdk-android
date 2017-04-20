package com.aromajoin.controllersdksample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.widget.Button;
import com.aromajoin.aromashootercontroller.connection.AromaShooterController;
import com.aromajoin.aromashootercontroller.connection.model.AromaShooter;
import com.aromajoin.aromashootercontroller.ui.activity.ASControllerBaseActivity;

import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The class extends the existing activity
 * which contains a bar item menu to go Connection Screen
 *
 * @author Quang Nguyen
 * © Aromajoin Corporation
 */
public class MainActivity extends ASControllerBaseActivity {

  private final int DEFAULT_DURATION = 3000; // Unit: millisecond

  //Get the instance of AromaShooterController
  private AromaShooterController aromaShooterController = AromaShooterController.getInstance();

  private List<Button> buttons = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    init();
  }

  private void init() {
    buttons.add((Button) findViewById(R.id.port1Button));
    buttons.add((Button) findViewById(R.id.port2Button));
    buttons.add((Button) findViewById(R.id.port3Button));
    buttons.add((Button) findViewById(R.id.port4Button));
    buttons.add((Button) findViewById(R.id.port5Button));
    buttons.add((Button) findViewById(R.id.port6Button));

    Observable.just(buttons)
        .flatMapIterable(list -> list)
        .flatMap(button -> RxView.clicks(button).map(aVoid -> button))
        .map(view -> {
          int index = buttons.indexOf(view);
          return index + 1; // port number
        })
        .buffer(100, TimeUnit.MILLISECONDS)
        .subscribe(ports ->
          diffuse(convertIntegers(ports))
        );
  }

  List<AromaShooter> aromaShooters; // connected device list.

  private void diffuse(int... ports) {
    aromaShooters = aromaShooterController.getConnectedDevices();
    if (aromaShooters != null && aromaShooters.size() > 0) {
      aromaShooterController.diffuse(aromaShooters, DEFAULT_DURATION, 1, ports);
    }
  }

  // A helper method to convert Integer list into primitive into array.
  private int[] convertIntegers(List<Integer> integers) {
    int[] ret = new int[integers.size()];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = integers.get(i).intValue();
    }
    return ret;
  }
}
