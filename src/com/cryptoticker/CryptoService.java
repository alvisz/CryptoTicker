package com.cryptoticker;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class CryptoService {
    String apiKey = "&CMC_PRO_API_KEY=1df82b74-753b-4ae6-979d-325b483d5db4";
    String listURL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?";


    public ArrayList<Crypto> getCurrencyList() throws Exception{
        ArrayList<Crypto> currencyList = new ArrayList<>();
        URL url = new URL(makeURL(listURL));
        URLConnection request = url.openConnection();
        try {
            request.connect();
        } catch (IOException e){
            System.out.println("No connection.");
            System.exit(0);
        }
        JsonParser jp = new JsonParser();
        JsonElement obj = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject objOfObj = obj.getAsJsonObject();
        JsonArray lists = objOfObj.get("data").getAsJsonArray();
        for (JsonElement elem: lists){
            JsonObject objobj = elem.getAsJsonObject();
            JsonObject objobjobj = objobj.get("quote").getAsJsonObject().get("USD").getAsJsonObject();
            double price = objobjobj.get("price").getAsDouble();
            double volume = objobjobj.get("volume_24h").getAsDouble();
            double percent_change_1h = objobjobj.get("percent_change_1h").getAsDouble();
            double percent_change_24h = objobjobj.get("percent_change_24h").getAsDouble();
            double percent_change_7d = objobjobj.get("percent_change_7d").getAsDouble();
            double market_cap = objobjobj.get("market_cap").getAsDouble();
            String last_updated = objobjobj.get("last_updated").getAsString();
            int id = objobj.get("id").getAsInt();
            String namee = objobj.get("name").getAsString();
            String urll = "https://s2.coinmarketcap.com/static/img/coins/64x64/"+id+".png";
            Crypto newelem = new Crypto(id, namee, urll,price,Math.floor(volume),percent_change_1h,percent_change_24h,percent_change_7d,market_cap,last_updated);
            currencyList.add(newelem);
        }
        return currencyList;

    }

    private String makeURL(String url){
        return url+apiKey;
    }
}

