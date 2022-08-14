package com.cck.websocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class TestCase {

    void callAlgoFramework(String uri, String content) throws InterruptedException, URISyntaxException {

        URI uri1 = new URI(uri);
        Client client = new Client(uri1);
        client.connect();

        while (true) {
            if (client.isOpen()) {
                client.send(content);
            }
        }
    }

    public static void main(String[] args) {
        String uri = "ws://127.0.0.1:8000/ws";
        String content = "msg from client";

        TestCase testCase1 = new TestCase();
        try {
            System.out.println("callAlgoFramework");
            testCase1.callAlgoFramework(uri, content);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}


