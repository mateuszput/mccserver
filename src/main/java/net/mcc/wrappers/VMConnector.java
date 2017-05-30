package net.mcc.wrappers;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class VMConnector {

    public void startTask(String server, Long taskID) throws IOException {
        StringBuffer body = new StringBuffer();
        body.append("{\"taskID\": \"").append(taskID).append("\"");
        String path = "/startTask/" + taskID.toString();

        HttpURLConnection httpURLConnection = createPOSTURL(server, taskID, body.toString(), path);
        int responseCode = httpURLConnection.getResponseCode();

        // Note: Czytanie odpowiedzi - nie potrzebne raczej - tylko response code!
//        StringBuffer response = readResponse(httpURLConnection);
    }

    private HttpURLConnection createPOSTURL(String server, Long taskID, String postBody, String path) throws IOException {
        StringBuffer urlAddress = new StringBuffer(server);
        urlAddress.append(path);

        URL url = new URL(urlAddress.toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        DataOutputStream postBodyOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        postBodyOutputStream.writeBytes(postBody.toString());
        postBodyOutputStream.close();

        return httpURLConnection;
    }


    private StringBuffer readResponse(HttpURLConnection httpURLConnection) throws IOException {
        StringBuffer response = new StringBuffer();
        BufferedReader responseBuffer = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));

        String inputLine;
        while ((inputLine = responseBuffer.readLine()) != null) {
            response.append(inputLine);
        }
        responseBuffer.close();
        return response;
    }

}
