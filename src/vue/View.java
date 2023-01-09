package src.vue;

import java.util.ArrayList;
import java.util.List;

public abstract class View implements ShowInterface, UserInteractionInterface, MenuObservable {
    List<MenuObserver> listeners;

    public View() {
        listeners = new ArrayList<>();
    }

    public void addMenuOberserver(MenuObserver menu) {
        listeners.add(menu);
    }

    public void removeMenuObserver(MenuObserver menu) {
        listeners.remove(menu);
    }

    public void notifyCloseAsked() {
        for(MenuObserver menu : listeners) {
            menu.onCloseAsked();
//            displayCloseAsked();
        }
    }

    public void notifyChangeLanguageAsked(String requestedLanguage){
        for(MenuObserver menu : listeners) {
            menu.onLanguageAsked();
//            displayLanguageAsked(requestedLanguage);
        }
    }

    public void notifyQuitAsked(){
        for(MenuObserver menu : listeners) {
            menu.onQuitAsked();
//            displayQuitAsked();
        }
    }
//
//    protected abstract void displayCloseAsked();
//    protected abstract void displayLanguageAsked(String pLanguage);
//    protected abstract void displayQuitAsked();
}
