package com.example;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeRates {
    @SerializedName("result")
    private String result;

    @SerializadName("base_code")
    private String baseCode;

    @SerizedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getConversionRates() {
        return conversionRates;
    }

    public double getRateFor(String currency){
        return conversionRates.get(currency, 0.0);
    }
}