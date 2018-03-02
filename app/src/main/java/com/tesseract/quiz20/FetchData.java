package com.tesseract.quiz20;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FetchData extends AsyncTask<Void,Void,Void>{
    String data="";
    String dataParsed = "";
    String singleParsed = "";
    static String link;

    public FetchData(String country){
        link = country;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://restcountries.eu/rest/v2/name/"+link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line!=null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
                for(int i = 0; i<JA.length(); i++){
                    JSONObject JO = (JSONObject)JA.get(i);
                    singleParsed = "Name: " + JO.get("name") + "\n" + "Capital: " + JO.get("capital") + "\n";

                    dataParsed = dataParsed + singleParsed;
                }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("DOODODODDOOD",this.data);
        ImageRestActivity.nameCountry.setText(this.dataParsed);
    }
}
