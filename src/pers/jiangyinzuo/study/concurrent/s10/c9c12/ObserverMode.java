package pers.jiangyinzuo.study.concurrent.s10.c9c12;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式中的对象逸出
 * @author Jiang Yinzuo
 */
public class ObserverMode {
    public static void main(String[] args) throws InterruptedException {
        Subject subject = new Subject();
        new Thread(() -> subject.addObserver(() -> System.out.println(subject.getState()))
        ).start();
        Thread.sleep(300);
        subject.setstate();
    }
}

class Subject {
    private List<Observer> observerList;
    private String state = "no";

    public Subject() {
        observerList = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state = "2333";
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    public String getState() {
        return state;
    }

    public void setstate() {
        state = "yes";
        notifyObservers();
    }

    interface Observer {
        void update();
    }
}
