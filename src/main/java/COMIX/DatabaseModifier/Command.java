package COMIX.DatabaseModifier;
public interface Command {
    public void execute();
    public void undo();  
}
