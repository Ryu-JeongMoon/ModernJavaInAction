package JavaInAction.chap09;

public interface Subject {

    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}
