package tbooop.controller;

import javafx.application.Application;
import tbooop.view.ViewUpdater;

/**
 * Entry point of the TBOOOP application.
 */
public final class AppLauncher {

    private AppLauncher() { }

    /**
     * Starts the game.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(ViewUpdater.class, args);
    }

}
