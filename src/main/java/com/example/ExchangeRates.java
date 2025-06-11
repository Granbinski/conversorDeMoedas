package com.example;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeRates {
    @SerializedName("result")
    private String result;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public double getRateFor(String currency) {
        return conversionRates.getOrDefault(currency, 0.0);
    }
}