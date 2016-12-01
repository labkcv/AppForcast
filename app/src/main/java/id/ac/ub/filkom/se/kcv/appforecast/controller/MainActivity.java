package id.ac.ub.filkom.se.kcv.appforecast.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import id.ac.ub.filkom.se.kcv.appforecast.R;

public class MainActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener,
                                                               Prediksi.OnFragmentInteractionListener, Info_Kurs.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appforecast_activity_main);

        setToolbar();


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                                                               .add("Home", Home.class)
                                                               .add("Prediksi", Prediksi.class)
                .add("Info Kurs", Info_Kurs.class)
                                                               .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

    private void setToolbar()
    {

        final Toolbar toolbar = (Toolbar) super.findViewById(R.id.appforecast_activity_main_toolbar);
        super.setSupportActionBar(toolbar);
        final ActionBar actionBar = super.getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            toolbar.setContentInsetStartWithNavigation(4);

            toolbar.setNavigationOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    MainActivity.this.onBackPressed();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int                      id     = item.getItemId();
        SharedPreferences        pref   = getApplicationContext().getSharedPreferences("CekLogin", MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = pref.edit();
        //noinspection SimplifiableIfStatement
        if(id == R.id.help)
        {
            startActivity(new Intent(this, Help.class));
            return true;
        }
        else if(id == R.id.about)
        {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);

            return true;
        }
        else if(id == R.id.logout)
        {
            Toast.makeText(this, "You're have logout", Toast.LENGTH_LONG).show();
            editor.clear();
            editor.commit();
//            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        else if(id == R.id.exit)
        {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        super.finish();
    }
}
