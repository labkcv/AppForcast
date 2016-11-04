package id.ac.ub.filkom.se.kcv.astechlauncher.controller.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import id.ac.ub.filkom.se.kcv.astechlauncher.R;


public class Help extends AppCompatActivity
{
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainapp_activity_help);

        final Toolbar toolbar = (Toolbar) super.findViewById(R.id.toolbarHelp);
        super.setSupportActionBar(toolbar);
        final ActionBar actionBar = super.getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            toolbar.setContentInsetStartWithNavigation(4);
        }

        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Mulai aplikasi",
                "Memilih aplikasi"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                switch(itemPosition)
                {
                    case 0:
                        Intent intent = new Intent(Help.this, HelpLogin.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent penggunaan = new Intent(Help.this, HelpPenggunaan.class);
                        startActivity(penggunaan);
                        break;
                    case android.R.id.home:
                        //perhaps use intent if needed but i'm sure there's a specific intent action for up you can use to handle
                        Help.this.onBackButtonPressed();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                             .show();
                }

            }

        });
    }

    private void onBackButtonPressed()
    {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                //perhaps use intent if needed but i'm sure there's a specific intent action for up you can use to handle
                this.onBackButtonPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
