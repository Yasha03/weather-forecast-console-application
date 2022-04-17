package user_interface;

public interface UserInteractor {
    public void print(String value) throws UserInteractorPrintException;
    public String readCommand() throws UserInteractorReadException, UserInteractorPrintException;
}
