package il.ac.shenkar.project.costmanager.model;

public class CostManagerException extends Exception{
    public CostManagerException(String message) { super(message); }
    public CostManagerException(String message, Throwable cause) { super(message, cause); }
}