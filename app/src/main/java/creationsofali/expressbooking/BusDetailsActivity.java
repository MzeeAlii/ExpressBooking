package creationsofali.expressbooking;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BusDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        String availableServices = getIntent().getStringExtra("available");
        final String origin = getIntent().getStringExtra("origin");
        final String destination = getIntent().getStringExtra("destination");
        final int index = getIntent().getIntExtra("index", 0);

        //Extracting data from array of strings
        final String[] splitArray = availableServices.split(" - "); // index 0 = companyName, index 1 = time, index 2 = busType


        TextView companyName = (TextView) findViewById(R.id.companyName);
        TextView time = (TextView) findViewById(R.id.time);
        TextView from = (TextView) findViewById(R.id.origin);
        TextView to = (TextView) findViewById(R.id.destination);
        TextView busType = (TextView) findViewById(R.id.busType);
        TextView fare = (TextView) findViewById(R.id.fare);
        ImageView busImage = (ImageView) findViewById(R.id.imageBusInDetails);

        //Setting values to corresponding widgets
        companyName.setText(splitArray[0]);
        time.setText(splitArray[1]);
        TicketActivity.passedTime = splitArray[1];
        busType.setText(splitArray[2]);
        from.setText(origin);
        to.setText(destination);

        //price
        if(splitArray[2].toLowerCase().equals("luxury")){
            fare.setText("35,000/=");

        } else if(splitArray[2].toLowerCase().equals("semi-luxury")){
            fare.setText("25,000/=");

        } else if(splitArray[2].toLowerCase().equals("ordinary")){
            fare.setText("20,000/=");

        }

        //Altering the image
        if(index % 2 == 0){
            busImage.setImageResource(R.drawable.abstract_fast);

        } else{
            busImage.setImageResource(R.drawable.abstract_inside_car);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Listening to clicks on fab
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast.makeText(BusDetailsActivity.this,
//                        "Oups! Booking service currently unavailable. The system is under construction.",
//                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(BusDetailsActivity.this, UserDetailsActivity.class);
                intent.putExtra("companyName", splitArray[0]);
                intent.putExtra("model", splitArray[2]);
                intent.putExtra("origin", origin);
                intent.putExtra("destination", destination);


                if(splitArray[2].toLowerCase().equals("luxury")){
                    intent.putExtra("amount", "35,000/=");

                } else if(splitArray[2].toLowerCase().equals("semi-luxury")){
                    intent.putExtra("amount", "25,000/=");

                } else if(splitArray[2].toLowerCase().equals("ordinary")){
                    intent.putExtra("amount", "20,000/=");

                }

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sub, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(BusDetailsActivity.this);
        }

        return super.onOptionsItemSelected(item);
    }
}
