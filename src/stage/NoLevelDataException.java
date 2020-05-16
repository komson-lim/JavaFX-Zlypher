package stage;

public class NoLevelDataException extends Exception{
    private String message;
    public NoLevelDataException(String message){
        this.message = message;
    }
}
