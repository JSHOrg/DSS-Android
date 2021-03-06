package mx.org.bamx.jsh.project.NetWorking;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import mx.org.bamx.jsh.project.Utils.Constants;

/**
 * Created by PC on 05/07/2018.
 */

public class RequestPost {
    private Context context = null;
    private URL url = null;
    private HttpURLConnection httpURLConnection = null;
    private OutputStreamWriter writer = null;
    private URLConnection urlConnection = null;
    private JSONObject jsonObject = null;
    private InputStream inputStream = null;


    String parameters = "";
    String result = "";

    public String requestPost(String[] values) {

        try {
            url = new URL(Constants.URL_BASE.concat(values[1]));
            urlConnection = url.openConnection();

            httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("POST");

            new SetHeaderes().setLoginHeader(httpURLConnection);

            if (SetHeaderes.TokenServicios != null) {
                new SetHeaderes().setToken(httpURLConnection);
            }
            if (SetHeaderes.cookieManager != null)
                new SetHeaderes().setCookie(httpURLConnection);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();


            writer = new OutputStreamWriter(httpURLConnection.getOutputStream());

            jsonObject = new JSONObject();

            for (int index = 2; index <= values.length - 1; index++) {
                if (index != 2)
                    parameters += "&";
                parameters += Constants.KEY_NAME.get(index - 2) +"="+ values[index].toString();

                //jsonObject.put(Constants.KEY_NAME.get(index - 2), values[index].toString());
            }

            //parameters = jsonObject.toString();
            writer.write(parameters);
            writer.flush();

            if (httpURLConnection.getResponseCode() != 200)
                inputStream = httpURLConnection.getErrorStream();
            else if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                if (SetHeaderes.cookieManager == null)
                    new SetHeaderes().getCookie(httpURLConnection);
            }
            //Constants.RESPONSE_CODE = httpURLConnection.getResponseCode();
            result = convertStreamToString(inputStream);

            inputStream.close();
        } catch (Exception ex) {
            Log.d("error", ex.toString());
        } finally {
            httpURLConnection.disconnect();
        }
        return result;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("respuesta", sb.toString());
        return sb.toString();
    }
}
