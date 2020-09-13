package com.kharitonov.port.generator;

import com.kharitonov.port.entity.CargoContainer;
import com.kharitonov.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DockRequestGenerator{
    private static final Logger LOGGER =
            LogManager.getLogger(DockRequestGenerator.class);
    private static final String HOST = "localhost";
    private static final int SOCKET = 3333;

    public void generateRequest(Ship ship) {
        try (Socket clientSocket = new Socket(HOST, SOCKET);
             ObjectOutputStream oos =
                     new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream ois =
                     new ObjectInputStream(clientSocket.getInputStream())) {
            oos.writeObject(ship);
            int dockId = ois.readInt();
            ship.setDockId(dockId);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
