package ru.spbau.mit.hackathon.paywell.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppServerThread extends Thread {
    public final static String purpose = "purpose", orgList = "orgList", hello = "Hello";

    private Socket socket = null;
    private static String database = "";

    static {
        Scanner in = null;
        try {
            in = new Scanner(new File("app/src/main/java/ru/spbau/mit/hackathon/paywell/server/projects.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNext()) {
            database += in.nextLine();
        }
    }

    public AppServerThread(Socket socket) {
        super("AppServerThread");
        this.socket = socket;
    }

    public void run() {

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
        ) {
            String inputString, outputLine;
            out.println(hello);

            //while ((inputString = in.readLine()) != null) {
            inputString = in.readLine(); {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(inputString);
                JsonNode statusNode = jsonNode.get(purpose);
                String statusValue = statusNode.textValue();

                if (statusValue.equals(orgList)) {
                    out.println(database);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
