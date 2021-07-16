package Exceptions;

public class NoSuchIDException extends Exception{
    public NoSuchIDException() {
        super("NoSuchIDException");
    }

    public NoSuchIDException(String theClass , int id) {
        super("NoSuchIDException\nThere is no "+theClass+" with id : "+id);
    }
}
