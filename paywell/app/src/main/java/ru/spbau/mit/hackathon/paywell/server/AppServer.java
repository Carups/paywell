package ru.spbau.mit.hackathon.paywell.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

class orgInfo {
    orgInfo(String orgNname, String shortDescription, String fullDescription, String cardnum) {
        this.cardnum = cardnum;
        this.orgNname = orgNname;
        this.fullDescription = fullDescription;
        this.shortDescription = shortDescription;
    }
    String orgNname;
    String shortDescription;
    String fullDescription;
    String cardnum;
}

public class AppServer {
    static ArrayList<orgInfo> orgList = new ArrayList<>();
    static {
        orgList.add(new orgInfo("a", "b", "c", "d"));
    }
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java "
                    + AppServer.class.getSimpleName()
                    + " <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new AppServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}