package creationsofali.expressbooking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AvailableServices extends AppCompatActivity {

    TextView headerText;
    Toolbar toolbar;
    EBookingDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_services);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Getting values passed from MainActivity
        final String origin = getIntent().getStringExtra("origin");
        final String destination = getIntent().getStringExtra("destination");
        headerText = (TextView) findViewById(R.id.headerText);

        Button chooseAgainBtn = new Button(this);

        if(origin.equals(destination)){

           /**
            *
            * I tried to dynamically create a view with a text and a button
            * When a user has selected no-such-rout, as from Arusha to Arusha
            *
            * Kinda works but it has some bugs sometimes.. No navigateFromTheSameTask() support
            *
             headerText.setText("Oups! No such route from "+ origin +" to "+ destination);
            chooseAgainBtn.setText("        Correctly choose again please       ");

            int c = getColor(R.color.colorAccent);
            int t = getColor(R.color.aliceBlue);
            chooseAgainBtn.setBackgroundColor(c);
            chooseAgainBtn.setTextColor(t);

            //Button dimens
            RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            //Button position
            buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.av);
            relativeLayout.addView(chooseAgainBtn, buttonParams);

            //Listening to click on button... Then bring back the main screen to choose again
            chooseAgainBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AvailableServices.this, Booking.class));
                }
            });

            */

            Intent intent = new Intent(AvailableServices.this, RouteUnavailableActivity.class);
            intent.putExtra("from", origin);
            intent.putExtra("to", destination);
            startActivity(intent);

        } else {

            headerText.setText("Available services from "+origin +" to "+ destination);
            //Creating database object
            database = new EBookingDatabase(AvailableServices.this);

            Cursor checkRoutes = database.retrieveData("SELECT * FROM "+ EBookingDatabase.TABLE_ROUTE);

            if(checkRoutes.getCount() == 0){
                database.insertRouteDetails(1, "Dodoma", "Dar es salaam");
                database.insertRouteDetails(2, "Mwanza", "Arusha");
                database.insertRouteDetails(3, "Singida", "Dar es salaam");
                database.insertRouteDetails(4, "Dar es salaam", "Dodoma");

            }

            Cursor routeCursor = database.retrieveData("SELECT * FROM "+ EBookingDatabase.TABLE_ROUTE  +" WHERE "+ EBookingDatabase.COL_ROUTE_ID +" IS 1" );

            for(int i = 1; i < routeCursor.getColumnCount(); i++){
                Log.d("Route Id 2", " "+ routeCursor.getString(i));
            }
//            Log.d("Routes table", " count = "+ routeCursor.getCount());
//            routeCursor.moveToFirst();
//            while (!routeCursor.isAfterLast()) {
//                Log.d("Routes table", " id "+ routeCursor.getInt(routeCursor.getColumnIndex(EBookingDatabase.COL_ROUTE_ID)));
//                Log.d("Routes table",
//                        " origin: " + routeCursor.getString(routeCursor.getColumnIndex(EBookingDatabase.COL_ROUTE_ORIGIN)));
//                Log.d("Routes table",
//                        " destination: " + routeCursor.getString(routeCursor.getColumnIndex(EBookingDatabase.COL_ROUTE_DESTINATION)));
//                //Advance to the next row
//                routeCursor.moveToNext();
//
//            }

            Cursor checkCompany = database.retrieveData("SELECT * FROM "+ EBookingDatabase.TABLE_COMPANY);
            if(checkCompany.getCount() == 0){
                database.insertCompanyDetails(1, "Shabiby", "Dar es salaam");
                database.insertCompanyDetails(2, "Princess Muro", "Dar es salaam");
                database.insertCompanyDetails(3, "Mohamed Trans", "Mwanza");
                database.insertCompanyDetails(4, "Isamilo Express", "Mwanza");
                database.insertCompanyDetails(5, "Green Star", "Dar es salaam");
                database.insertCompanyDetails(6, "Kandahar Express", "Arusha");
                database.insertCompanyDetails(7, "Machame Express", "Moshi");
                database.insertCompanyDetails(8, "Kimbinyiko", "Arusha");
                database.insertCompanyDetails(9, "Arusha Express", "Arusha");
            }
            Cursor companyCursor = database.retrieveData("SELECT * FROM "+ EBookingDatabase.TABLE_COMPANY);
            Log.d("Company table", " count = "+ companyCursor.getCount());

            companyCursor.moveToFirst();
            while(!companyCursor.isAfterLast()){
                Log.d("Company table -", " id: "+ companyCursor.getInt(companyCursor.getColumnIndex(EBookingDatabase.COL_COMP_ID))
                        +" name: "+ companyCursor.getString(companyCursor.getColumnIndex(EBookingDatabase.COL_COMP_NAME))
                        + " loc: "+ companyCursor.getString(companyCursor.getColumnIndex(EBookingDatabase.COL_COMP_LOCATION))
                );

                companyCursor.moveToNext();
            }

            Cursor checkBus = database.retrieveData("SELECT * FROM "+ EBookingDatabase.TABLE_BUS);
            if(checkBus.getCount() == 0){
                database.insertBusDetails("T124 ACB", "Luxury", 60, 1, 35000, "06:00 am", "02:00 pm", 1);
                database.insertBusDetails("T317 AFE", "Semi-Luxury", 60, 1, 25000, "06:00 am", "02:00 pm", 1);
                database.insertBusDetails("T295 AQL", "Luxury", 60, 1, 35000, "06:00 am", "02:00 pm", 4);
                database.insertBusDetails("T295 AQL", "Semi-Luxury", 60, 1, 35000, "12:00 pm", "08:00 pm", 4);
                database.insertBusDetails("T295 AQL", "Semi-Luxury", 60, 1, 35000, "12:00 pm", "08:00 pm", 4);
                database.insertBusDetails("T295 AQL", "Semi-Luxury", 60, 1, 35000, "12:00 pm", "08:00 pm", 4);
            }


            final String[] availableSample = {
                    "Shabiby - 06:00 am - Luxury",
                    "ABC - 06:00 am - Ordinary",
                    "Princess Muro - 12:00pm - Semi-Luxury",
                    "Machame Express - 12:00pm - Semi-Luxury",
                    "Mohamed Trans - 02:30pm - Ordinary",
                    "Shabiby - 02:30pm - Luxury" };


            MyCustomAdapter adapter = new MyCustomAdapter(AvailableServices.this,availableSample, origin, destination);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(AvailableServices.this, BusDetailsActivity.class);
                    String data = availableSample[position];
                    //Sending data to activity
                    intent.putExtra("available", data);
                    intent.putExtra("origin", origin);
                    intent.putExtra("destination", destination);
                    intent.putExtra("index", position);
                    startActivity(intent);
                }
            });

            //Then SQLite shit ndo ifanyike humu.. Mutha Hubbard!! Still a long road to go :(

        }

    }

    //Adding actions on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sub, menu);

        return true;
    }

    //Setting listeners for actions on toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Toast.makeText(this, "Settings function not defined yet!", Toast.LENGTH_SHORT).show();
            //To add methods here

//        } else if(id == R.id.action_search){
//            Toast.makeText(this, "Search function not defined yet!", Toast.LENGTH_SHORT).show();
//            //To add methods here

        } else if(id == R.id.action_exit){
            finish();

        } else if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(AvailableServices.this);
        }

        return super.onOptionsItemSelected(item);
    }
}
