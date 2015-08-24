/*
 * Copyright 2015 Tinbytes Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tinbytes.simplerandomcolorapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
  public static final String[] COLORS = new String[]{"Red", "Green", "Blue", "Darkgray",
      "Cyan", "Silver", "Fuchsia", "Lime", "Purple"};
  private TextView tvNextChangeIn;
  private int counter = 5;
  private Random random;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvNextChangeIn = (TextView) findViewById(R.id.tvNextChangeIn);
    findViewById(R.id.bChangeIt).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeBackgroundColor();
      }
    });

    random = new Random();
    Timer timer = new Timer("Random Color Changer");
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        tick();
      }
    }, 1000, 1000);
  }

  private void tick() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (counter == 0) {
          changeBackgroundColor();
        }
        tvNextChangeIn.setText(MessageFormat.format(getString(R.string.next_change_in), counter--));
      }
    });
  }

  private void changeBackgroundColor() {
    String nextColor = COLORS[random.nextInt(COLORS.length)];
    getWindow().getDecorView().setBackgroundColor(Color.parseColor(nextColor));
    getActionBar().setTitle(nextColor);
    counter = 5;
  }
}
