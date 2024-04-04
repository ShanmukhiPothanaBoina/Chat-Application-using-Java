package com.example.chatapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String inputLine, outputLine;

            while (true) {
                outputLine = consoleReader.readLine();
                writer.println(outputLine);

                if ((inputLine = reader.readLine()) != null) {
                    System.out.println("Server: " + inputLine);
                }

                if (outputLine.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
