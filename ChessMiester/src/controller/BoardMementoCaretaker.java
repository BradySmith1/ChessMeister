package controller;

import interfaces.BoardIF;
import model.Board;

public class BoardMementoCaretaker {

    private Element top;

    public BoardMementoCaretaker(BoardIF.BoardMementoIF memento) {
        this.top = new Element(memento);
    }

    public void push(BoardIF.BoardMementoIF memento) {
        Element toPush = new Element(memento);
        toPush.downElement = this.top;
        this.top.upElement = toPush;
        this.top = toPush;
    }

    public BoardIF.BoardMementoIF pop() {
        BoardIF.BoardMementoIF temp = this.top.data;
        this.top = this.top.downElement;
        this.top.upElement = null;
        return temp;
    }

    public BoardIF.BoardMementoIF peek() {
        return this.top.data;
    }

    public Board.BoardMementoIF down() {
        BoardIF.BoardMementoIF memento = null;
        if(this.top.downElement != null) {
            this.top = this.top.downElement;
            memento = this.top.data;
        }
        return memento;
    }

    public BoardIF.BoardMementoIF up() {
        BoardIF.BoardMementoIF memento = null;
        if(this.top.upElement != null) {
            this.top = this.top.upElement;
            memento = this.top.data;
        }
        return memento;
    }

    private static class Element {

        private final BoardIF.BoardMementoIF data;
        private Element upElement;
        private Element downElement;

        Element(BoardIF.BoardMementoIF memento) {
            this.data = memento;
            this.upElement = null;
            this.downElement = null;
        }
    }
}
