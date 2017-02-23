package io.github.roelvanendhoven.zrbarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class HourRegistration extends AppCompatActivity {

    static final int SCAN_PERMIT_REQUEST = 1;
    static final int SCAN_ID_REQUEST = 2;

    private HourRegistrationData registrationData;
    private TextView workHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registrationData = new HourRegistrationData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_hour_registration);

        workHours = (TextView) findViewById(R.id.scan_text_view);
        updateScanResults();

        final Button scanPermitButton = (Button) findViewById(R.id.scan_workpermit_button);
        scanPermitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startBarcodeScan(v, SCAN_PERMIT_REQUEST);
            }
        });

        final Button scanIdButton = (Button) findViewById(R.id.scan_idcard_button);
        scanIdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startBarcodeScan(v, SCAN_ID_REQUEST);
            }
        });


    }

    private void startBarcodeScan(View v, int requestType) {
        Intent intent = new Intent(v.getContext(), ScanBarcode.class);
        startActivityForResult(intent, requestType);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hour_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            String scanResult = data.getStringExtra("scan_result");
            switch (requestCode) {
                case SCAN_PERMIT_REQUEST:
                    registrationData.setWorkPermit(scanResult);
                    break;
                case SCAN_ID_REQUEST:
                    registrationData.setEmployee(scanResult);
                    break;
            }
            updateScanResults();
        }
    }

    public void updateScanResults() {
        String registrationDetails, employeeDetails;
        if (registrationData.getWorkPermit().equals("")) registrationDetails = "Not Scanned";
        else registrationDetails = registrationData.getWorkPermit();
        if (registrationData.getEmployee().equals("")) employeeDetails = "Not Scanned";
        else employeeDetails = registrationData.getEmployee();

        workHours.setText("Permit details: \n\n - " + registrationDetails + "\n\n - " + employeeDetails);
    }
}
