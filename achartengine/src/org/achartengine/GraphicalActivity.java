/**
 * Copyright (C) 2009 - 2012 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.achartengine;

import org.achartengine.chart.AbstractChart;
import org.achartengine.model.SeriesSelection;
import org.achartengine.tools.PanListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;

/**
 * An activity that encapsulates a graphical view of the chart.
 */
public class GraphicalActivity extends Activity {
  /** The encapsulated graphical view. */
  private GraphicalView mView;
  /** The chart to be drawn. */
  private AbstractChart mChart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle extras = getIntent().getExtras();
    mChart = (AbstractChart) extras.getSerializable(ChartFactory.CHART);
    mView = new GraphicalView(this, mChart);
    mView.addPanListener(new PanListener() {
      
      @Override
      public void panApplied() {
//        Log.d("2TEST", "!!!!!!!!!!!");
        
      }
    });
    mView.setOnTouchListener(new OnTouchListener() {
      
      @Override
      public boolean onTouch(View v, MotionEvent event) {
//        Log.d("TEST", "$$$$$$$ " + v + ", event = " + event);
        return false;
      }
    });
    mView.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        GraphicalView view = (GraphicalView)v;
        SeriesSelection sec = view.getCurrentSeriesAndPoint();
        if (null != sec) {
          Log.d("TEST", "$$$$$$$ " + sec.getPointIndex());
          
        }
        
      }
    });
    String title = extras.getString(ChartFactory.TITLE);
    if (title == null) {
      requestWindowFeature(Window.FEATURE_NO_TITLE);
    } else if (title.length() > 0) {
      setTitle(title);
    }
    setContentView(mView);
  }

}