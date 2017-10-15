package com.londonappbrewery.bitcointicker;

//

//Created by DanielD on 15/10/2017.

//

import org.json.JSONException;
import org.json.JSONObject;

class CurrencyDataModel {

    // Member variables

    private String mLastCurrency; //Last currency
    private String mHighCurrency; //High currency
    private String mLowCurrency; //Low currency


    // CurrencyDataModel from a JSON:

    static CurrencyDataModel fromJson(JSONObject jsonObject){

        try {
            CurrencyDataModel currencyData = new CurrencyDataModel();

            currencyData.mLastCurrency = jsonObject.getString("last");
            currencyData.mHighCurrency = jsonObject.getString("high");
            currencyData.mLowCurrency = jsonObject.getString("low");

            return currencyData;

        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    // Getter methods for Last currency

    String getLastCurrency() {
        return mLastCurrency;
    }

    // Getter methods for High currency

    String getHighCurrency() {
        return mHighCurrency;
    }

    // Getter methods for Low currency

    String getLowCurrency() {
        return mLowCurrency;
    }

}
