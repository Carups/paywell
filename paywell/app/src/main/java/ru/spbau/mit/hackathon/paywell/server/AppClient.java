package ru.spbau.mit.hackathon.paywell.server;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class AppClient extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            return getJsonFromString();
        } catch (IOException e) {
            return "-1";
        }
    }

    private String getJsonFromString() throws IOException {
        final String hostName = "10.0.2.2";
        final int portNumber = 8080;
        String fromServer = null;
//
//        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
//        ) {
            fromServer = in.readLine();//System.out.println("Server: " + fromServer);
            out.println("{\"purpose\" : \"orgList\"}");
            fromServer = in.readLine();
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host " + hostName);
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for the connection to " +
//                    hostName);
//            System.exit(1);
//        }
        return fromServer;
    }
}