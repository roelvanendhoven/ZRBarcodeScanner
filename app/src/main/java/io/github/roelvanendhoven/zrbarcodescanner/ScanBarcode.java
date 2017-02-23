package io.github.roelvanendhoven.zrbarcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanBarcode extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private static String CAMERA_LOG_TAG = "Camera";

    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getContents();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("scan_result", result);
        setResult(Activity.RESULT_OK, returnIntent);
        mScannerView.resumeCameraPreview(this);
        finish();
    }
}
