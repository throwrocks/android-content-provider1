package rocks.throw20.contentprovider;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joselopez on 3/10/16.
 */
public class DataCountriesJSONParser {

    private static final String LOG_TAG = DataCountriesAPI.class.getSimpleName();

    private final Context mContext;


    // Constructor
    public DataCountriesJSONParser(Context context){

        this.mContext = context;

    }

    public void getCountriesFromJSON(String countriesJSONString ){
        try {

            // Create the countriesJSON object with the countriesJSONString parameter
            JSONArray countriesArray = new JSONArray(countriesJSONString);
            final String countriesList = "";

            // These are the individual fields
            final String fieldCountryName = "name";
            final String fieldCountryCapital = "capital";
            final String fieldCountryRegion = "region";
            final String fieldCountryPopulation = "population";


            // Get the length of the array to set the index of the
            // resultStrs array that will be used by the adapter
            int countriesQty = countriesArray.length();
            //Log.e(LOG_TAG, "CountriesQty: " + countriesQty);

            // Loop through the countriesArray to parse each Json object needed
            for (int i = 0; i < countriesQty; i++) {
                JSONObject countryRecord = countriesArray.getJSONObject(i);
                //Log.e(LOG_TAG, "countryRecord: " + countryRecord);
                // Parse the individual data elements needed
                String countryName = countryRecord.getString(fieldCountryName);
                String countryCapital = countryRecord.getString(fieldCountryCapital);
                String countryRegion = countryRecord.getString(fieldCountryRegion);
                Long countryPopulation = countryRecord.getLong(fieldCountryPopulation);

                //Log.e(LOG_TAG, "Parsing string: " + countryName + " " + countryCapital + " " + countryRegion);
            }
        }
        catch (JSONException e) {
        e.printStackTrace();
        }
    }

}

