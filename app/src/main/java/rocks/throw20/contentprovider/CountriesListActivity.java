package rocks.throw20.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import rocks.throw20.contentprovider.Data.Contract;
import rocks.throw20.contentprovider.Data.FetchTask;

public class CountriesListActivity extends AppCompatActivity {
    private final String LOG_TAG = CountriesListActivity.class.getSimpleName();

    private boolean mTwoPane;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Stetho.initializeWithDefaults(this);


        // Get cursor
        mCursor = this.getContentResolver().query(
                Contract.CountryEntry.buildCountries(),
                null,
                null,
                null,
                null);
        Log.e(LOG_TAG, "getItemCount -> " + mCursor.getCount());
        // Create a DataFetch Async task and execute it
        FetchTask fetchData = new FetchTask(this);
        fetchData.execute();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.e(LOG_TAG, "setupRecyclerView -> " + true);
        recyclerView.setAdapter(new CountriesListAdapter(this,mCursor));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
