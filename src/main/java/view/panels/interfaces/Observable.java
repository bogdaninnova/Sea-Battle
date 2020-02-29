package view.panels.interfaces;

public interface Observable {
    void addObserver(Observer... obs);
    boolean removeObserver(Observer o);
    void notifyObservers();
}
