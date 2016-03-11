package rocks.throw20.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by josel on 3/10/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    // The databse version
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "countries.db";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_COUNTRIES_TABLE  =
                "CREATE TABLE " +
                        DataCountriesContract.CountryEntry.COUNTRIES_TABLE_NAME + " (" +
                        DataCountriesContract.CountryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DataCountriesContract.CountryEntry.countryId + " INTEGER NOT NULL, " +
                        DataCountriesContract.CountryEntry.countryName + " TEXT NOT NULL, " +
                        DataCountriesContract.CountryEntry.countryCapital + " TEXT NOT NULL, " +
                        DataCountriesContract.CountryEntry.countryRegion + " TEXT NOT NULL, " +
                        DataCountriesContract.CountryEntry.countryPopulation + " INTENER NOT NULL " +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_COUNTRIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataCountriesContract.CountryEntry.COUNTRIES_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
