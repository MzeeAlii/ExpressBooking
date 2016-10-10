package creationsofali.expressbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button bookNowBtn;
    String[] sampleData = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adding my custom toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        bookNowBtn = (Button) findViewById(R.id.bookNowBtn);
        bookNowBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, Booking.class));
            }
        });


    }

    //Inflating the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Setting responses
        if(id == R.id.action_settings){
            Toast.makeText(this, "Settings function not defined yet!", Toast.LENGTH_SHORT).show();

        } else if(id == R.id.action_exit){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
