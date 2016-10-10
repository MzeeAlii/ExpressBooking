package creationsofali.expressbooking;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Booking extends AppCompatActivity {

    private static final String[] routes = {"Arusha", "Dar es salaam", "Dodoma", "Singida", "Moshi", "Mwanza"};

    Spinner dropDownFrom;
    Spinner dropDownTo;
    private static String fromTextSelected;
    private static String toTextSelected;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_route);
        //Adding my custom toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        dropDownFrom = (Spinner) findViewById(R.id.spinnerFrom);
        dropDownTo = (Spinner) findViewById(R.id.spinnerTo);

        //Setting Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Booking.this, android.R.layout.simple_spinner_item, routes);

        dropDownFrom.setAdapter(adapter);
        dropDownTo.setAdapter(adapter);
        Log.d("Spinner item counts ", "= "+ dropDownFrom.getCount());


        //Listening the origin location selected
        dropDownFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromTextSelected = routes[position];
                Log.d("Origin location: ", fromTextSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Listening the destination location selected
        dropDownTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toTextSelected = routes[position];
                Log.d("Destination location: ", toTextSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /**   Works like charm!! :-P    */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booking.this, AvailableServices.class);
                intent.putExtra("origin", fromTextSelected);
                intent.putExtra("destination", toTextSelected);
                startActivity(intent);
            }
        });

    }

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
        }

        return super.onOptionsItemSelected(item);
    }
}
