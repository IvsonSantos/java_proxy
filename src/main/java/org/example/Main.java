package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://localhost");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("http://proxyaddress", 8080));
        HttpURLConnection webProxyConnection = (HttpURLConnection) url.openConnection(proxy);
        webProxyConnection.setRequestMethod("GET");
        String valueToEncode = "username" + ":" + "password";
        webProxyConnection.setRequestProperty("Authorization", "Basic" + Base64.getEncoder().encodeToString(valueToEncode.getBytes()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(webProxyConnection.getInputStream()));
        StringBuffer jsonData = getJsonData(reader);
        reader.close();
        webProxyConnection.disconnect();


        System.out.println("Hello world!");
    }

    private static StringBuffer getJsonData(BufferedReader reader) throws IOException {
        String inputLine;
        StringBuffer jsonData = new StringBuffer();
        while((inputLine = reader.readLine()) != null) {
            jsonData.append(inputLine);
        }

        return jsonData;
    // ou replace se tiver uma chave

    }
}