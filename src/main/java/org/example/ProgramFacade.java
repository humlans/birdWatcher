package org.example;

import org.example.builderPattern.MenuBuilder;
import org.example.items.Menu;

public class ProgramFacade {
    private Menu menu;
    private boolean running;

    public ProgramFacade() {
        running = true;
        MenuBuilder menuBuilder = new MenuBuilder(this);
        menu = menuBuilder.setNotLoggedInMenu().build();
        menu.printMenuOptionsAndExecute();
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
