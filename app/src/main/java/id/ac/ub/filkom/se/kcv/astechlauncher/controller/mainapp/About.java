package id.ac.ub.filkom.se.kcv.astechlauncher.controller.mainapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import id.ac.ub.filkom.se.kcv.astechlauncher.R;

/**
 * Created by k-dafi on 8/18/2016.
 */
public class About extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainapp_about);


        //        Intent intent = new Intent(this, About.class);
        //        startActivity(intent);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        findViewById(R.id.logo).startAnimation(animation);
        findViewById(R.id.card).startAnimation(animation);
        findViewById(R.id.footer).startAnimation(animation);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.help)
        {
            Toast.makeText(About.this, "Help", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(id == R.id.about)
        {
            //            Intent intent = new Intent(this, About.class);
            //            startActivity(intent);
            Toast.makeText(About.this, "You're in about info", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(id == R.id.logout)
        {
            Toast.makeText(About.this, "Logout", Toast.LENGTH_LONG).show();

            return true;
        }
        else if(id == R.id.exit)
        {
            Toast.makeText(About.this, "Exit", Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
