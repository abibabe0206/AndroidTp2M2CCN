package com.example.androidtp1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Tp2RecyclerView extends AppCompatActivity implements
PersonAdapter.PersonsAdapterListener {

    private RecyclerView recyclerView;
    private List<Person> personList;
    private PersonAdapter personAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_tp2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // create the data for person
        personList = new ArrayList<>();
        personList.add(new Person("https://voi.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fvoi.2F2018.2F03.2F20.2Fd3bc6b27-9de7-4fd2-a9d5-010f117d02fa.2Ejpeg/480x270/quality/80/photos-nabilla-affiche-ses-formes-dans-un-bikini-qui-ne-cache-pas-grand-chose.jpg",
                "Nabila","Jean", "02/Feb/1993", "Rennes","06 66 88"));
        personList.add(new Person("https://loopnewslive.blob.core.windows.net/liveimage/sites/default/files/2017-12/tb4klb9tqc.jpg",
                "Valantin","Jean-Claude", "02/Jun/1988", "Brest","06 66 99"));
        personList.add(new Person("https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Patrick_Bruel_Cabourg_2012.jpg/220px-Patrick_Bruel_Cabourg_2012.jpg",
                "Patrick","Peter", "02/Oct/1998", "Paris","06 55 99"));
        personList.add(new Person("https://upload.wikimedia.org/wikipedia/commons/d/da/Britney_Spears_2013_%28Straighten_Crop%29.jpg",
                "Joy","Brit", "02/Oct/1998", "Nantes","07 55 99"));


        // handle the recycler view
        recyclerView = findViewById(R.id.recyclerViewNEwClient);
        personAdapter = new PersonAdapter(this, personList,  this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(personAdapter);



        // handle the button action menu
        Button buttonNewClient = (Button) findViewById(R.id.buttonNewClient);
        buttonNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                personAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                personAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onContactSelected(Person person) {
        String text = "Selected :\t" + person.getNom() + "\nPrenom :\t" + person.getPrenom() + "\nDate de Naissance : \t" + person.getDdn() + "\nVille deNaissance :\t" + person.getVdn() + "\nPhone Number :\t" + person.getPhone();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


}
