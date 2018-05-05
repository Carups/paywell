package ru.spbau.mit.hackathon.paywell.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class AppServer {

    public static void main(String[] args) throws IOException {

        new FileInputStream("serverKeyStore.jks");
        System.setProperty("javax.net.ssl.keyStore", "serverKeyStore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

        int portNumber = 8080;
        boolean listening = true;

        SSLServerSocketFactory sslSrvFact =
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

        try (SSLServerSocket serverSocket = (SSLServerSocket)sslSrvFact.createServerSocket(portNumber)) {
            while (listening) {
                new AppServerThread(serverSocket.accept()).start();
                System.out.println("Connection to server");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}