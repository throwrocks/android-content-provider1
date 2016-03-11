package rocks.throw20.contentprovider.Data;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by joselopez on 3/10/16.
 */
public class DataCountriesFetch extends AsyncTask<String, Void, Void> {

    private static final String LOG_TAG = DataCountriesFetch.class.getSimpleName();

    private final Context mContext;

        // Constructor
        public DataCountriesFetch(Context context){
        this.mContext = context;

        }

    @Override
    protected Void doInBackground(String... params) {

        String jsonResults;
        ContentValues[] parsedResults;

        // Create an API object
        DataCountriesAPI mAPI = new DataCountriesAPI(mContext);

        // Get the results from the API
        jsonResults = mAPI.callAPI();
        //Log.e(LOG_TAG, "results " + results);

        // Parse the results if not null
        if ( jsonResults != null ) {
            DataCountriesJSONParser parseCountries = new DataCountriesJSONParser(mContext);

            // Get the parsed results
            parsedResults = parseCountries.getCountriesFromJSON(jsonResults);

            if ( parsedResults != null ){
                // Bulk insert
                DataCountriesStore bulkInsert = new DataCountriesStore(mContext, parsedResults, "insert/update");

            }
        }



        return null;
    }


}
