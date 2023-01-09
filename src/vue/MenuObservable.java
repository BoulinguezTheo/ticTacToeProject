package src.vue;

public interface MenuObservable {
    void addMenuOberserver(MenuObserver menu);
    void removeMenuObserver(MenuObserver menu);
    void notifyCloseAsked();
    void notifyChangeLanguageAsked(String requestedLanguage);
    void notifyQuitAsked();
}
