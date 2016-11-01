package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private TextView singleTapCountTextView;
    private TextView doubleTapCountTextView;
    private TextView longPressCountTextView;
    private TextView scrollCountTextView;
    private TextView flingCountTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a GestureDetector to listen to events on the ScrollView
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleTapCountTextView = (TextView) findViewById(R.id.singleTapCountTextView);
        doubleTapCountTextView = (TextView) findViewById(R.id.doubleTapCountTextView);
        longPressCountTextView = (TextView) findViewById(R.id.longPressCountTextView);
        scrollCountTextView = (TextView) findViewById(R.id.scrollCountTextView);
        flingCountTextView = (TextView) findViewById(R.id.flingsCountTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);

        // Hooking up the gesture detector to our scroll view
        // 4 out of 5 gestures handled
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this); //this b/c we implements OnGestureListener
        // Special case: double tap
        gestureDetector.setOnDoubleTapListener(this); // already implement
    }


    //Any touch event is now dispatched from Activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);

        return gestureDetector.onTouchEvent(ev);
        //return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        singleTapCountTextView.setText(String.valueOf(singleTaps));
        // Let's append to our gesture log:
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event.");

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        doubleTaps++;
        doubleTapCountTextView.setText(String.valueOf(doubleTaps));
        gesturesLogTextView.append("\nonDoubleTap touch event.");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTapEvent touch event.");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event.");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event.");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event.");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolls++;
        scrollCountTextView.setText(String.valueOf(scrolls));
        gesturesLogTextView.append("\nonScroll: distanceX is: " + v
                        + ", distanceY is: " + v1);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        longPressCountTextView.setText(String.valueOf(longPresses));
        gesturesLogTextView.append("\nonLongPress touch event.");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        flings++;
        flingCountTextView.setText(String.valueOf(flings));
        gesturesLogTextView.append("\nonFlings: velocityX is: " + v
                + ", velocityY is: " + v1);
        return false;
    }

    public void clearAll(View View)
    {
        flings = 0;
        scrolls = 0;
        singleTaps = -1;
        longPresses = 0;
        doubleTaps = 0;

        gesturesLogTextView.setText("");
        doubleTapCountTextView.setText("0");
        longPressCountTextView.setText("0");
        scrollCountTextView.setText("0");
        flingCountTextView.setText("0");
        singleTapCountTextView.setText("0");
    }
}
