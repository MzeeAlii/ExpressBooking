package creationsofali.expressbooking;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class TransactionActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    EditText etTransId;
    String transId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        etTransId = (EditText) findViewById(R.id.id);

        etTransId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                transId = null;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                transId = etTransId.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                etTransId.setError(null);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.submitButton);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(transId)){
                    etTransId.setError("You must enter the transaction ID.");
                    return;

                } else{
                    Intent i = new Intent(TransactionActivity.this, TicketActivity.class);
                    i.putExtra("transId", transId);
                    startActivity(i);
                }
            }
        });
    }
}
