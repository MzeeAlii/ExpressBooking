package creationsofali.expressbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PayUpActivity extends AppCompatActivity {
    Toolbar toolbar;
    String passedName;
    String passedAmount;
    TextView airtelNo;
    TextView vodaNo;
    TextView tigoNo;
    TextView companyName;
    TextView amount;
    Button completePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payup);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        airtelNo = (TextView) findViewById(R.id.airtelNo);
        tigoNo = (TextView) findViewById(R.id.tigoNo);
        vodaNo = (TextView) findViewById(R.id.vodaNo);

        passedName = getIntent().getStringExtra("companyName");
        passedAmount = getIntent().getStringExtra("amount");

        companyName = (TextView) findViewById(R.id.companyName);
        amount = (TextView) findViewById(R.id.amountToPay);
        companyName.setText(passedName);
        amount.setText(passedAmount);


        if(passedName.toLowerCase().equals("shabiby")){
            //Show Shabiby numbers to pay
            airtelNo.setText("0688 222 222");
            tigoNo.setText("0715 222 222");
            vodaNo.setText("0755 222 222");

        } else if(passedName.toLowerCase().equals("abc")){
            //Show ABC numbers to pay
            airtelNo.setText("0682 003 003");
            tigoNo.setText("0712 003 003");
            vodaNo.setText("0752 003 003");

        }else if(passedName.toLowerCase().equals("kimbinyiko")){
            //Show Kimbinyiko numbers to pay
            airtelNo.setText("0685 444 444");
            tigoNo.setText("0715 444 444");
            vodaNo.setText("0755 444 444");

        }else if(passedName.toLowerCase().equals("machame express")){
            //Show ABC numbers to pay
            airtelNo.setText("0684 500 500");
            tigoNo.setText("0714 500 500");
            vodaNo.setText("0754 500 500");

        } else {
            airtelNo.setText("0688 111 111");
            tigoNo.setText("0715 111 111");
            vodaNo.setText("0755 111 111");
        }

        // And the rest of companies


        completePayment = (Button) findViewById(R.id.completeButton);
        completePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayUpActivity.this, TransactionActivity.class));
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_help){
            startActivity(new Intent(PayUpActivity.this, HelpActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
