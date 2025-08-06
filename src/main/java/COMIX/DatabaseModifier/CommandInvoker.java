package COMIX.DatabaseModifier;

import java.util.Stack;

public interface CommandInvoker extends Command{
   // public Stack<Command> undoStack; // list of commands?
    //public Command currentCommand;
    //public void setCommand(Command command);
    public void execute();
    public void undo(); //stack = undoStack.pop();
}
