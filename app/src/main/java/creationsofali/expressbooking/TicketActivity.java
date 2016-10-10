package creationsofali.expressbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TicketActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView transactionView;
    TextView usernameView;
    TextView companyNameView;
    TextView dateView;
    TextView fareView;
    TextView timeView;
    TextView originView;
    TextView destinationView;
    TextView seatNoView;

    public static String passedFName;
    public static String passedLName;
    public static String passedCName;
    public static String passedDate;
    public static String passedOrigin;
    public static String passedDestination;
    public static String passedSeatNo;
    public static String passedFare;
    public static String passedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Referencing the views
        companyNameView = (TextView) findViewById(R.id.companyName);
        usernameView = (TextView) findViewById(R.id.username);
        originView = (TextView) findViewById(R.id.origin);
        destinationView = (TextView) findViewById(R.id.destination);
        seatNoView = (TextView) findViewById(R.id.seatNoDropDown);
        transactionView = (TextView) findViewById(R.id.transId);
        dateView = (TextView) findViewById(R.id.travelDate);
        fareView = (TextView) findViewById(R.id.fare);
        timeView = (TextView) findViewById(R.id.takeOffTime);


        String passedId = getIntent().getStringExtra("transId");

        //Filling values on ticket
        companyNameView.setText(passedCName);
        usernameView.setText(passedFName +" "+ passedLName);
        originView.setText(passedOrigin);
        destinationView.setText(passedDestination);
        seatNoView.setText(passedSeatNo);
        transactionView.setText(passedId);
        dateView.setText(passedDate);
        fareView.setText(passedFare);
        timeView.setText(passedTime);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ticket, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_exit){
            startActivity(new Intent(TicketActivity.this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
