package src;

import user_interface.UserInteractorPrintException;
import user_interface.UserInteractorReadException;
import user_interface.UserTerminal;

import java.io.IOException;

public class App extends Application{

    public static final String API_KEY = "GtJsZWFh2YuN02Ab3YG7t0N5y5Ydv0Lj";
    public static UserTerminal terminal;

    public static void main(String[] args) throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        App app = new App(args);
    }

    public App(String[] args) throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        super(args);
    }

    @Override
    public void init() {
        terminal = new UserTerminal();
    }

    @Override
    public void start() throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        String res = terminal.whileRead();

    }
}