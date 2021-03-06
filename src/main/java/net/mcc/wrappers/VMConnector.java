package net.mcc.wrappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mcc.dto.StartTaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class VMConnector {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void startTask(String server, Long taskID, StartTaskRequest startTaskRequestData) throws IOException {
        StringBuffer body = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(startTaskRequestData);

        body.append(jsonInString);
//        body.append("{\"taskID\": \"").append(taskID).append("\"}");
//        String path = "/startTask/" + taskID.toString();

        log.info("sending to: " + server);
        log.info("sending json: " + body.toString());
        String path = "/startTask/" + taskID.toString();

        HttpURLConnection httpURLConnection = createPOSTURL(server, taskID, body.toString(), path);
        int responseCode = httpURLConnection.getResponseCode();

        log.info("response code: " + responseCode);
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
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        DataOutputStream postBodyOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        postBodyOutputStream.writeBytes(postBody);
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
