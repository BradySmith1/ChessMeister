package controller;

import interfaces.BoardIF;

public class BoardMementoCaretaker {

    private Element top;

    public BoardMementoCaretaker(BoardIF.BoardMemento memento) {
        this.top = new Element(memento);
    }

    public void push(BoardIF.BoardMemento memento) {
        Element toPush = new Element(memento);
        toPush.downElement = this.top;
        this.top.upElement = toPush;
        this.top = toPush;
    }

    public BoardIF.BoardMemento down() {
        BoardIF.BoardMemento memento = null;
        if(this.top.downElement != null) {
            this.top = this.top.downElement;
            memento = this.top.data;
        }
        return memento;
    }

    public BoardIF.BoardMemento up() {
        BoardIF.BoardMemento memento = null;
        if(this.top.upElement != null) {
            this.top = this.top.upElement;
            memento = this.top.data;
        }
        return memento;
    }

    private static class Element {

        private final BoardIF.BoardMemento data;
        private Element upElement;
        private Element downElement;

        Element(BoardIF.BoardMemento memento) {
            this.data = memento;
            this.upElement = null;
            this.downElement = null;
        }
    }
}
