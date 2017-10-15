package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    // Member Variables:
    TextView mLastPriceTextView; //Last Price Text View
    TextView mHighPriceTextView; //High Price Text View
    TextView mLowPriceTextView; //Low Price Text View

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLastPriceTextView = (TextView) findViewById(R.id.txtViewLast);
        mHighPriceTextView = (TextView) findViewById(R.id.txtViewHigh);
        mLowPriceTextView = (TextView) findViewById(R.id.txtViewLow);

        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner

        // Spinner click listener
        spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // An item was selected.
                Log.d("Bitcoin", "" + parent.getItemAtPosition(position));

                String mCurrency = String.valueOf(parent.getItemAtPosition(position));
                String EXTENDED_URL = BASE_URL + mCurrency;

                Log.d("Bitcoin", EXTENDED_URL);

               letsDoSomeNetworking(EXTENDED_URL);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // If nothing was selected
                Log.d("Bitcoin", "Nothing selected");
            }
        });


    }

    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"Hea
                Log.d("Bitcoin", "JSON: " + response.toString());

                CurrencyDataModel currencyData = CurrencyDataModel.fromJson(response);

//                Toast.makeText(MainActivity.this, "Success! JSON: " + response.toString(),
//                        Toast.LENGTH_SHORT).show();
                updateUI(currencyData);
//                WeatherDataModel weatherData = WeatherDataModel.fromJson(response);
//                updateUI(weatherData);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("Bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());
//                Toast.makeText(WeatherController.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
        // updateUI():

        private void updateUI(CurrencyDataModel objCurrency){
            mLastPriceTextView.setText(objCurrency.getLastCurrency());
            mHighPriceTextView.setText(objCurrency.getHighCurrency());
            mLowPriceTextView.setText(objCurrency.getLowCurrency());
     }

}
