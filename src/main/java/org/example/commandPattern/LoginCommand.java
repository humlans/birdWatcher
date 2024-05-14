package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.builderPattern.MenuBuilder;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.Menu;
import org.example.items.User;
import org.example.items.UserInput;

public class LoginCommand implements ICommand{
    private ProgramFacade program;

    public LoginCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        UserInput userInput = new UserInput();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        System.out.println("Write your username: ");
        String username = userInput.getCanNotBeBlankStringInput();
        System.out.println("Write your password: ");
        String password = userInput.getCanNotBeBlankStringInput();
        User user = databaseConnection.login(username, password);
        if(user != null) {
            MenuBuilder menuBuilder = new MenuBuilder(program);
            menuBuilder.emptyOptionsAndCommands();
            Menu newLoggedInMenu = menuBuilder.setLoggedInMenu(user).build();
            program.setMenu(newLoggedInMenu);
            return;
        }
        System.out.println("Invalid username or password, try again.");
    }
}
