package org.example;

import org.example.builderPattern.MenuBuilder;
import org.example.databaseConnections.NotificationConnection;
import org.example.items.Menu;
import org.example.items.Notification;

public class ProgramFacade {
    private Menu menu;
    private boolean running;

    public ProgramFacade() {
        running = true;
        MenuBuilder menuBuilder = new MenuBuilder(this);
        menu = menuBuilder.setNotLoggedInMenu().build();
    }

    public void run() {
        while(running) {
            menu.printMenuOptionsAndExecute();
        }
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }
}
