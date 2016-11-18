package id.ac.ub.filkom.se.kcv.appforecast.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        setContentView(R.layout.mainapp_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainapp_activity_main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.prediksi);
        toolbar.setLogoDescription("AppForecast");
        toolbar.setTitle("AppForecast");


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
