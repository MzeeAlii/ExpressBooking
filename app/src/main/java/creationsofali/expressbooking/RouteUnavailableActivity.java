package creationsofali.expressbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RouteUnavailableActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button chooseAgainBtn;
    TextView noRouteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_unavailable);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        String origin = getIntent().getStringExtra("from");
        String destination = getIntent().getStringExtra("to");

        noRouteText = (TextView) findViewById(R.id.noRouteText);
        noRouteText.setText("Sorry, no such route found! \nThe route from "+ origin +" to "+ destination +" is not available.");


        chooseAgainBtn = (Button) findViewById(R.id.chooseAgainBtn);
        chooseAgainBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RouteUnavailableActivity.this, Booking.class));
            }
        });

    }
}
