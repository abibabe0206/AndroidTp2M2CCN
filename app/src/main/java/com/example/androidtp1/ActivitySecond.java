package com.example.androidtp1;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitySecond extends AppCompatActivity {
    @BindView(R.id.textResult)
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("Valide");
        textResult.setText(value1);
        //setResult(RESULT_OK);
        /*Toast.makeText(getApplicationContext(),"First value: "+value1,
                +Toast.LENGTH_LONG).show();*/

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
