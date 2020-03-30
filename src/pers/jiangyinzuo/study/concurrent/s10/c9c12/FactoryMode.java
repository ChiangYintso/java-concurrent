package pers.jiangyinzuo.study.concurrent.s10.c9c12;

import java.util.ArrayList;
import java.util.List;

/**
 * 工厂模式保证初始化的类完全发布
 * TODO
 * @author Jiang Yinzuo
 */
public class FactoryMode {
}

class Subject2 {
    private List<Observer> observerList;
    private String state = "no";

    private Subject2() {
        observerList = new ArrayList<>();
    }

    public static Subject2 getInstance() {
        return new Subject2();
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