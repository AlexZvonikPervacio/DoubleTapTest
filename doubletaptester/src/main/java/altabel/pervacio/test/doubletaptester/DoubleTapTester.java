package altabel.pervacio.test.doubletaptester;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import altabel.pervacio.test.doubletaptester.listener.DoubleTapTestListener;

/**
 * Created by alex on 14.9.16.
 */
public class DoubleTapTester {

    private static final String TAG = DoubleTapTester.class.getSimpleName();

    private GestureDetector mGestureDetector;

    public void testDoubleTap(@NonNull final Activity activity, @Nullable final DoubleTapTestListener doubleTapTestListener) {
        if(activity != null) {
            testDoubleTap(activity.getWindow().getDecorView().getRootView(), doubleTapTestListener);
        }
    }

    public void testDoubleTap(@NonNull final View testView, @Nullable final DoubleTapTestListener doubleTapTestListener) {
        if(testView != null) {
            testView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    getGestureDetector(testView.getContext(), doubleTapTestListener).onTouchEvent(motionEvent);
                    return true;
                }
            });
        }
    }

    private GestureDetector getGestureDetector(@NonNull final Context context, final DoubleTapTestListener doubleTapListener) {
        if(mGestureDetector == null) {
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if(doubleTapListener != null) {
                        doubleTapListener.onDoubleTap(e.getX(), e.getY());
                    }
                    logMessage("Double tap event");
                    return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    if(doubleTapListener != null) {
                        doubleTapListener.onSingleTap(e.getX(), e.getY());
                    }
                    logMessage("Single tap event");
                    return super.onSingleTapConfirmed(e);
                }
            });
        }
        return mGestureDetector;
    }

    private void logMessage(final String message) {
        Log.i(TAG, message);
    }

}
