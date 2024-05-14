package org.example.commandPattern;

import org.example.ProgramFacade;

public class QuitCommand implements ICommand{
    private ProgramFacade program;

    public QuitCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        program.setRunning(false);
    }
}
