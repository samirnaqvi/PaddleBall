/******************************************************************************
 * Detect touches to the screen.  This is used mostly for the motion of the
 * paddle.
 ******************************************************************************/

package com.example.paddleball;

import android.view.MotionEvent;
import android.view.View;

public class ActivitySwipeDetector implements View.OnTouchListener {

static final String logTag = "ActivitySwipeDetector";
private Main activity;
static final int MIN_DISTANCE = 5;
private float downX, downY, upX, upY;
private int paddle1X = 0;
private int paddle2X = 0;

public ActivitySwipeDetector(Main activity){
    this.activity = activity;
}

public void onRightToLeftSwipe(){
//??    Log.i(logTag, "RightToLeftSwipe!");
//???    activity.doSomething(finalX);
//???  	activity.startball();
}

public void onLeftToRightSwipe(){
//???    Log.i(logTag, "LeftToRightSwipe!");
//???    activity.doSomething(finalX);
//???	activity.startball();
}

public void onTopToBottomSwipe(){
//???    Log.i(logTag, "onTopToBottomSwipe!");
//???    activity.doSomething(finalX);
//???	activity.startball();
}

public void onBottomToTopSwipe(){
//???    Log.i(logTag, "onBottomToTopSwipe!");
//???    activity.doSomething(finalX);
//???	activity.startball();
}

/******************************************************************************
 * For any touch, set the current position of the paddle as the X position of
 * the end of the touch.  The Y position of the paddle is fixed.
 ******************************************************************************/
public boolean onTouch(View v, MotionEvent event)
{
	int courtTop = activity.getCourtTop();
	int courtHeight = activity.getCourtHeight();
	if ((int)event.getY() > courtTop)
	{
		if ((int)event.getY() > courtTop + courtHeight / 2)
		{
			paddle1X = (int)event.getX();
			activity.movePaddle1(paddle1X);
		}
		else
		{
			paddle2X = (int)event.getX();
			activity.movePaddle2(paddle2X);
		}
	}
    switch(event.getAction()){
        case MotionEvent.ACTION_DOWN: {
            downX = event.getX();
            downY = event.getY();
            return true;
        }
        case MotionEvent.ACTION_UP: {
            upX = event.getX();
            upY = event.getY();

            float deltaX = downX - upX;
            float deltaY = downY - upY;

            // swipe horizontal?
            if(Math.abs(deltaX) > MIN_DISTANCE){
                // left or right
                if(deltaX < 0) { this.onLeftToRightSwipe(); return true; }
                if(deltaX > 0) { this.onRightToLeftSwipe(); return true; }
            }
            else {
//???                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
                    return false; // We don't consume the event
            }

            // swipe vertical?
            if(Math.abs(deltaY) > MIN_DISTANCE){
                // top or down
                if(deltaY < 0) { this.onTopToBottomSwipe(); return true; }
                if(deltaY > 0) { this.onBottomToTopSwipe(); return true; }
            }
            else {
//???                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
                    return false; // We don't consume the event
            }

            return true;
        }
    }
    return false;
}

}