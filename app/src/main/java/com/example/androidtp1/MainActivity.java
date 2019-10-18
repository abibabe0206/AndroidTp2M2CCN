package com.example.androidtp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.androidtp1.Services.Repository;
import com.example.androidtp1.model.PersonDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    /**
     *  the app without the use of Butter-knife
     */
    // Defineding the global variables to be used in any function

   /** EditText editNom, editPrenom, editVDN;
    TextView textResult;
    CalendarView calendarView;
    Button buttonValdie1;*/

    /**
     *  the app with the use of Butter-knife
     */
    @BindView(R.id.editNom)
    EditText editNom;

    @BindView(R.id.editPrenom)
    EditText editPrenom;

    @BindView(R.id.editVDN)
    EditText editVDN;

    @BindView(R.id.textResult)
    TextView textResult;

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @BindView(R.id.buttonValdie1)
    Button buttonValdie1;

    @BindView(R.id.editPhoneNumber)
    EditText editPhoneNumber;

    @BindView(R.id.spinnerDepDN)
    Spinner spinnerDepDN;

    String spinnerItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

       // butterknife call
        ButterKnife.bind(this);

        /**
         *  the app without the use of Butter-knife
         */
        /* editNom = (EditText) findViewById(R.id.editNom);
        editPrenom = (EditText) findViewById(R.id.editPrenom);
        editVDN = (EditText) findViewById(R.id.editVDN);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        textResult = (TextView) findViewById(R.id.textResult);
        buttonValdie1 = (Button) findViewById(R.id.buttonValdie1);

        Log.i("TAG"," is null? "+ buttonValdie1);


        the valide button


        buttonValdie1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nom = editNom.getText().toString();
                String prenom = editPrenom.getText().toString();
                // for the calender
               long ddn = calendarView.getDate();
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY"); // or dd-MMM-YYYY
                String currentDate = format.format(ddn);
                String vdn = editVDN.getText().toString();
                textResult.setText("\t" + nom + "\n\t" + prenom + "\n  Date de Naissance : \t" + currentDate + "\n\t" + vdn );

            }

        });*/


        // Spinner Element Part
        spinnerDepDN.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        // this method is for when you do not use resources
        /**List <String> departments = new ArrayList<String>();
        departments.add("Ajoutez votre département");
        departments.add("Rennes");
        departments.add("Brest");
        departments.add("Diguliville");
        departments.add("Paris");
        departments.add("Nantes");
        departments.add("Lyon");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departments);

        // Drop down layout style -list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // attaching data adapter to spinner
        //spinnerDepDN.setAdapter(dataAdapter);*/



    }


      /*  public void valider(View v) {
            Log.i("TAG"," valdier...");


        }*/

    @OnClick(R.id.buttonValdie1)
    public  void onClick(){
          String nom = editNom.getText().toString();
          String prenom = editPrenom.getText().toString();
          // for phone number
            boolean visible = editPhoneNumber.getVisibility() == View.VISIBLE;
            String phoneNumber = (visible) ? editPhoneNumber.getText().toString() : "";


          // for the calender
          long ddn = calendarView.getDate();
          SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY"); // or dd-MMM-YYYY
          String currentDate = format.format(ddn);
          String vdn = editVDN.getText().toString();

        String text = "Nom :\t" + nom + "\nPrenom :\t" + prenom + "\nDate de Naissance : \t" + currentDate + "\nVille deNaissance :\t" + vdn + "\nPhone Number :\t" + phoneNumber
                + "\nDepartment de Naissance :\t" + spinnerItem;
       // textResult.setText(text);
       // Log.i("TP1", text);
        // Displaying my work
        /*Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();*/


        // put all the data inside into PersonDB
   //PersonDB person = new PersonDB("Abiola","Babbage", "02/Oct/1995", "Rennes", "0668852", "ill-de-france");
        PersonDB person = new PersonDB(nom,prenom,phoneNumber,currentDate,vdn,spinnerItem);

        //add the user in the DB
        Repository repos = new Repository(getApplication());

        repos.insert(person);

        setResult(RESULT_OK);
        finish();
        // Using explicit intent
        //callSecondActivity();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optional_menu, menu);


        return super.onCreateOptionsMenu(menu);
        // return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch (id){
            case R.id.addPhoneNumber:
                if(editPhoneNumber.getVisibility() == View.GONE){
                    editPhoneNumber.setVisibility(View.VISIBLE);
                    item.setTitle("REMOVE PHONE");
                } else {
                    editPhoneNumber.setVisibility(View.GONE);
                    editPhoneNumber.setText("");
                    item.setTitle("ADD PHONE");
                }
                break;
            case R.id.aboutVille:
                String vdn = editVDN.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org/wiki/"+vdn));
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    // for spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On Selecting a spinner item
        spinnerItem = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // explicit intent part ActivitySecond.class
    public void callSecondActivity(){
        Intent intent = new Intent(getApplicationContext(), ActivitySecond.class);

        /*
        this inside the first activity (main activity)
         */
        String nom = editNom.getText().toString();
        String prenom = editPrenom.getText().toString();
        // for phone number
        boolean visible = editPhoneNumber.getVisibility() == View.VISIBLE;
        String phoneNumber = (visible) ? editPhoneNumber.getText().toString() : "";


        // for the calender
        long ddn = calendarView.getDate();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY"); // or dd-MMM-YYYY
        String currentDate = format.format(ddn);
        String vdn = editVDN.getText().toString();

        String text = "Nom :\t" + nom + "\nPrenom :\t" + prenom + "\nDate de Naissance : \t" + currentDate + "\nVille deNaissance :\t" + vdn + "\nPhone Number :\t" + phoneNumber
                + "\nDepartment de Naissance :\t" + spinnerItem;
        // textResult.setText(text);
        // Log.i("TP1", text);
        // Displaying my work
        //Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();




        intent.putExtra("Valide",text);
        startActivity(intent);
        //startActivityForResult(intent, 0);
    }
}
