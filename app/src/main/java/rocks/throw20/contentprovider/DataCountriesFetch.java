package rocks.throw20.contentprovider;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

/**
 * Created by joselopez on 3/10/16.
 */
class DataCountriesFetch extends AsyncTask<String, Void, Void> {

    private static final String LOG_TAG = DataCountriesFetch.class.getSimpleName();

    private final Context mContext;

        // Constructor
        public DataCountriesFetch(Context context){
        this.mContext = context;

        }

    @Override
    protected Void doInBackground(String... params) {

        String results;

        DataCountriesAPI mAPI = new DataCountriesAPI(mContext);
        results = mAPI.callAPI();

        //Log.e(LOG_TAG, "results " + results);
        if ( results != null ) {
            DataCountriesJSONParser pareseCountries = new DataCountriesJSONParser(mContext);
            pareseCountries.getCountriesFromJSON(results);
        }
        return null;
    }


}
