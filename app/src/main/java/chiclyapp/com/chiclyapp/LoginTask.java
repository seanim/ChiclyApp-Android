package chiclyapp.com.chiclyapp;

import android.os.AsyncTask;
import android.util.Base64;

import com.scottyab.aescrypt.AESCrypt;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/**
 * Created by sumitlad on 10/26/16.
 */

public class LoginTask extends AsyncTask<String, String,String> {
    protected String doInBackground(String... urls) {

        String kHostHTTPPath = "https://staging.chicly.co/api/v1/sign_in";
        String kBasicAuthName = "F28ED5AD233DEF14AA68159895139";
        String kBasicAuthPassword = "11A9CD218A5CFAC4D1EB9E638F268";
        String kCryptPassword = "341B9B4C11116A2C6B37FE75CAECC";





        URL url = null;


        JSONObject user = null;
        JSONObject request = null;
        try {
            user = new JSONObject();
            user.put("email","sumit@chiclyapp.com");
            user.put("password", AESCrypt.encrypt(kCryptPassword,"ssssss"));
            request = new JSONObject();
            request.put("user",user);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


        try {
            url = new URL(kHostHTTPPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Authorization", "Basic " + Base64.encodeToString("F28ED5AD233DEF14AA68159895139:11A9CD218A5CFAC4D1EB9E638F268".getBytes("UTF8"),Base64.NO_WRAP));
            OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
            out.write(request.toString());
            out.close();
            System.out.println(request.toString());
            int HttpResult =urlConnection.getResponseCode();
            System.out.println("Login response code: "+HttpResult);
            return(String.valueOf(HttpResult));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return("");
    }

}