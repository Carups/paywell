package ru.spbau.mit.hackathon.paywell.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class AppClient {
    public static String get() throws IOException {
        //String hostName = args[0];
        //int portNumber = Integer.parseInt(args[1]);
        String hostName = "127.0.0.1";
        int portNumber = 4321;
        String fromServer = null;
        
        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
        ) {


            //while ((fromServer = in.readLine()) != null) {
            fromServer = in.readLine();
                //System.out.println("Server: " + fromServer);

            out.println("{\"purpose\" : \"orgList\"}");

            fromServer = in.readLine();



        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
        return fromServer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(get());
    }
}