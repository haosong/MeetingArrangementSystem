package src;

/**
 * Created by ddz on 16/6/25.
 */
public interface Subject {
    public void add(Observer observer);

    public void del(Observer observer);

    public void notifyObservers();
}
