package ru.spbau.mit.hackathon.paywell.server;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;


public class AppClient extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            return getJsonFromString();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    private String getJsonFromString() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, KeyManagementException {
        final String hostName = "10.0.2.2";
        final int portNumber = 8080;

        String fromServer = null;

        //passphrase for keystore
        char[] keystorePass="changeit".toCharArray();
        SSLContext sslContext = null;

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //keyStore.load(Resources.getSystem().openRawResource(R.raw.client_trust_store), keystorePass);
        keyStore.load(new ByteArrayInputStream(PublicKeyHolder.certArr), keystorePass);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        sslContext = SSLContext.getInstance("TLS");

        sslContext.init(
                null,
                trustManagerFactory.getTrustManagers(),
                new SecureRandom()
        );

                SSLSocket kkSocket = (SSLSocket)sslContext.getSocketFactory().createSocket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));

            fromServer = in.readLine();//System.out.println("Server: " + fromServer);
            out.println("{\"purpose\" : \"orgList\"}");
            fromServer = in.readLine();

        return fromServer;
    }
}