package com.wallet.bank.utils;

import java.util.HashMap;
import java.util.Map;

public class Params {
    private final Map<String, String> parametersMap = new HashMap<>();

    public Params(String[] parameters) {
        for (String param : parameters) {
            parametersMap.put(param.split("=")[0], param.split("=")[1]);
        }
    }

    public String get(String key) {
        return parametersMap.get(key);
    }
}
