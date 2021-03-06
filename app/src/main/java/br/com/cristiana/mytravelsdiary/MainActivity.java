package br.com.cristiana.mytravelsdiary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import br.com.cristiana.mytravelsdiary.adapter.TravelListAdapter;
import br.com.cristiana.mytravelsdiary.dao.TravelDAO;
import br.com.cristiana.mytravelsdiary.listener.OnClickListener;
import br.com.cristiana.mytravelsdiary.model.Travel;
import br.com.cristiana.mytravelsdiary.utils.Constantes;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvTravel;
    private TravelListAdapter adapter;
    TravelDAO dao = new TravelDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadTravels();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.insert) {
            Intent i = new Intent(this, InsertActivity.class);
            startActivity(i);
        } else if (id == R.id.about) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        } else if (id == R.id.exit) {
            finish();
            onDestroy();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadTravels(){
        rvTravel = (RecyclerView) findViewById(R.id.rvTravel);
        rvTravel.setLayoutManager(new LinearLayoutManager(this));
        rvTravel.setItemAnimator(new DefaultItemAnimator());
        rvTravel.setHasFixedSize(true);


        List<Travel> travels = dao.getAll();

        adapter = new TravelListAdapter(this, travels, onClickListener());

        rvTravel.setAdapter(adapter);
    }

    //Update
    private OnClickListener onClickListener(){
        return new OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                i.putExtra(Constantes.KEY_TRAVEL, adapter.getItem(position));
                startActivity(i);
            }
        };
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
