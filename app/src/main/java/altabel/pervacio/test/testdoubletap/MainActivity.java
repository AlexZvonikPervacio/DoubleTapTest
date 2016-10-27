package altabel.pervacio.test.testdoubletap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import altabel.pervacio.test.doubletaptester.DoubleTapTester;
import altabel.pervacio.test.doubletaptester.listener.DoubleTapTestListener;

public class MainActivity extends Activity implements DoubleTapTestListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mDoubleTapStatusTv;
    private TextView mDoubleTapCoordTv;

    private DoubleTapTester mDoubleTapTester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDoubleTapStatusTv = (TextView) findViewById(R.id.double_tap_status_tv);
        mDoubleTapCoordTv = (TextView) findViewById(R.id.double_tap_coordinates_tv);

        /*  Create new instance of DoubleTapTester class and
            then call testDoubleTap(Activity, DoubleTapListener) (can be null)
            Once user (double) tap on the activity the test listener will notify about corresponding event.
            If no listener specified then only message in logcat will be shown
         */
        mDoubleTapTester = new DoubleTapTester();
        mDoubleTapTester.testDoubleTap(MainActivity.this, this);
        /*
            also we can test double tap on any view.
         */
        mDoubleTapTester.testDoubleTap(findViewById(R.id.double_tap_on_view_tv), this);
    }

    @Override
    public void onDoubleTap(final float x, final float y) {
        logMessage(getString(R.string.double_tap_log, x, y));
        mDoubleTapStatusTv.setText(R.string.double_tap_succeed);
        mDoubleTapCoordTv.setText(getString(R.string.double_tap_coordinates, x, y));
        mDoubleTapStatusTv.setVisibility(View.VISIBLE);
        mDoubleTapCoordTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSingleTap(final float x, final float y) {
        logMessage(getString(R.string.double_tap_error));
        mDoubleTapStatusTv.setText(R.string.double_tap_error);
        mDoubleTapStatusTv.setVisibility(View.VISIBLE);
        mDoubleTapCoordTv.setVisibility(View.GONE);
    }

    private void logMessage(final String message) {
        Log.i(TAG, message);
    }
}