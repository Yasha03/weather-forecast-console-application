package src;

import user_interface.UserInteractorPrintException;
import user_interface.UserInteractorReadException;

import java.io.IOException;

public abstract  class Application {
    protected String[] args;

    public Application(String[] args) throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        this.args = args;
        this.init();
        this.start();
    }

    public abstract void init();
    public abstract void start() throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException;
}
