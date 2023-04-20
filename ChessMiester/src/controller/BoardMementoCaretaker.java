package controller;

import interfaces.BoardIF;
import model.Board;

/**
 * A stack that holds elements of mementos for the BoardIF interface. This class is used
 * to undo and redo moves in the game of chess.
 *
 * @author Colton Brooks (100%)
 * @version 2.0 (done in sprint 2)
 */
public class BoardMementoCaretaker {

    /** the current position in the stack that the board is at.
     * (Does not have to be at the literal top of the stack) */
    private Element top;
    private final Element bottom;

    /**
     * Constructor for the Caretaker. Only meant to be used at the beginning of a game
     * with the initial board state to be passed in.
     *
     * @param memento   the memento for the initial state of the board
     */
    public BoardMementoCaretaker(BoardIF.BoardMementoIF memento) {
        this.top = new Element(memento);
        this.bottom = this.top;
    }

    /**
     * This method pushes a new element onto this stack. This also serves to cut the
     * stack away from moves if undo has been used.
     *
     * @param memento the memento to be used to construct the Element
     */
    public void push(BoardIF.BoardMementoIF memento) {
        Element toPush = new Element(memento);
        toPush.downElement = this.top;
        this.top.upElement = toPush;
        this.top = toPush;
    }


    /**
     * Method to pop the top element off the stack.
     *
     * @return the memento of the element that was popped
     */
    public BoardIF.BoardMementoIF pop() {
        BoardIF.BoardMementoIF temp = this.top.data;
        this.top = this.top.downElement;
        this.top.upElement = null;
        return temp;
    }

    /**
     * Method to get the memento of the top element in the stack.
     *
     * @return the memento of the top element in the stack
     */
    public BoardIF.BoardMementoIF peek() {
        return this.top.data;
    }

    /**
     * Method to move down or "undo" in the stack.
     *
     * @return the memento of the element you are now at
     *         (returns null if there is now element below)
     */
    public Board.BoardMementoIF down() {
        BoardIF.BoardMementoIF memento = null;
        if(this.top.downElement != null) {
            this.top = this.top.downElement;
            memento = this.top.data;
        }
        return memento;
    }

    /**
     * Method to move up or "redo" in the stack.
     *
     * @return the memento of the element you are now at
     *         (returns null if there is now element above)
     */
    public BoardIF.BoardMementoIF up() {
        BoardIF.BoardMementoIF memento = null;
        if(this.top.upElement != null) {
            this.top = this.top.upElement;
            memento = this.top.data;
        }
        return memento;
    }

    public BoardIF.BoardMementoIF topToBottom() {
        this.top = this.bottom;
        return this.top.data;
    }

    /**
     * An element to be held in the stack. Holds a memento and pointer to the
     * above and below elements.
     */
    private static class Element {

        /** the elements data for the boards memento */
        private final BoardIF.BoardMementoIF data;
        /** the element above this one */
        private Element upElement;
        /** the element below this one */
        private Element downElement;

        /**
         * Constructor for the element.
         * @param memento a board memento to be used for the element.
         */
        Element(BoardIF.BoardMementoIF memento) {
            this.data = memento;
            this.upElement = null;
            this.downElement = null;
        }
    }
}
