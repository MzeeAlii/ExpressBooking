package creationsofali.expressbooking;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetailsActivity extends AppCompatActivity {

    TextView companyName;
    TextView busModel;
    TextView fare;
    TextView origin;
    TextView destination;

    EditText firstNameEd;
    EditText lastNameEd;
    EditText phoneNoEd;
    EditText seatNoEd;
    EditText dateEd;

    FloatingActionButton submitButton;
    Toolbar toolbar;

    String firstName;
    String lastName;
    String travelDate;
    String phoneNo;
    String seatNo;

    String selectedOnSpinner;

    Spinner seatNoSpinner;

    String[] seats = {"A2", "A4", "B2", "B4", "C2", "C4", "D2", "D4", "E2", "E4", "F2", "F4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Getting passed values
        final String name = getIntent().getStringExtra("companyName");
        final String model = getIntent().getStringExtra("model");
        final String og = getIntent().getStringExtra("origin");
        final String dn = getIntent().getStringExtra("destination");
        final String fare = getIntent().getStringExtra("amount");

        //Reference to views
        companyName = (TextView) findViewById(R.id.companyName);
        busModel = (TextView) findViewById(R.id.busType);
        origin = (TextView) findViewById(R.id.origin);
        destination = (TextView) findViewById(R.id.destination);
        firstNameEd = (EditText) findViewById(R.id.firstNameText);
        lastNameEd = (EditText) findViewById(R.id.lastNameText);
        phoneNoEd = (EditText) findViewById(R.id.phoneNoText);
        dateEd = (EditText) findViewById(R.id.dateText);

        seatNoSpinner = (Spinner) findViewById(R.id.seatNoDropDown);
        submitButton = (FloatingActionButton) findViewById(R.id.submitButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserDetailsActivity.this,
                android.R.layout.simple_spinner_item);
        adapter.add("Select seat number");
        adapter.addAll(seats);


        seatNoSpinner.setAdapter(adapter);

        seatNoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> viewGroup, View view, int position, long id) {

                if(seatNoSpinner.getSelectedItem() == "Select seat number"){
                    //
                    selectedOnSpinner = seatNoSpinner.getSelectedItem().toString();

                } else {
                    TicketActivity.passedSeatNo = seatNoSpinner.getSelectedItem().toString();
                    selectedOnSpinner = seatNoSpinner.getSelectedItem().toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                seatNoSpinner.setPrompt("Please select seat number");
            }
        });


        //Setting values to views
        companyName.setText(name);
        busModel.setText(model);
        origin.setText(og);
        destination.setText(dn);

        //Passing values to tickets
        TicketActivity.passedCName = name;
        TicketActivity.passedFare = fare;
        TicketActivity.passedOrigin = og;
        TicketActivity.passedDestination = dn;

        //Getting values in EditText fields
//        firstName = firstNameEd.getText().toString();
//        lastName = lastNameEd.getText().toString();
//        phoneNo = phoneNoEd.getText().toString();
//        seatNo = seatNoEd.getText().toString();
//        travelDate = " "+ dateEd.getText().toString();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDetailsActivity.this, PayUpActivity.class);
                //Pass values here
                i.putExtra("companyName", name);
                i.putExtra("amount", fare);

                if(TextUtils.isEmpty(firstName)){
                    firstNameEd.setError("Your first name can not be empty.");
                    return;

                } else if (TextUtils.isEmpty(lastName)){
                    lastNameEd.setError("Your last name can not be empty.");
                    return;

                } else if (TextUtils.isEmpty(phoneNo)){
                    phoneNoEd.setError("Please enter your phone number.");
                    return;

                } else if (TextUtils.isEmpty(travelDate)){
                    dateEd.setError("This field can't be empty. Travel date must be known.");
                    return;

                }
//                else if (TextUtils.isEmpty(seatNo)){
//                    seatNoEd.setError("This field can't be empty. You must choose seat number.");
//                    return;
//
//                }
                else {

                    if(selectedOnSpinner.equals("Select seat number")){
                        Toast.makeText(UserDetailsActivity.this,
                                "Please select a seat number from available seats.",
                                Toast.LENGTH_LONG).show();
                    } else {

                        startActivity(i);
                    }
                }
//                else{
//                    Toast.makeText(UserDetailsActivity.this,
//                            "Sorry, all fields must be filled.\nPlease complete all required details.",
//                            Toast.LENGTH_LONG).show();
//                }

            }
        });


        /**
         * Getting fields values and error corrections */

        //firstName
        firstNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                firstName = "";
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                firstName = firstNameEd.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                firstNameEd.setError(null);
                TicketActivity.passedFName = firstName;
            }
        });


        //lastName error corrections
        lastNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lastName = "";
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lastName = lastNameEd.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                lastNameEd.setError(null);
                TicketActivity.passedLName = lastName;
            }
        });


        //phoneNo error corrections
        phoneNoEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phoneNo = "";

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phoneNo = phoneNoEd.getText().toString();
//                if(p.length() > 0)
//                    phoneNo = p;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneNoEd.setError(null);
            }
        });


        //travelDate error corrections
        dateEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                travelDate = "";
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                travelDate = dateEd.getText().toString();
//                if(d.length() > 0)
//                    travelDate = d;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                dateEd.setError(null);
                TicketActivity.passedDate = travelDate;
            }
        });


        //seatNo error corrections
//        seatNoEd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                seatNo = "";
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                seatNo =  seatNoEd.getText().toString();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                seatNoEd.setError(null);
//                TicketActivity.passedSeatNo = seatNo;
//            }
//        });





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
            startActivity(new Intent(UserDetailsActivity.this, HelpActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
