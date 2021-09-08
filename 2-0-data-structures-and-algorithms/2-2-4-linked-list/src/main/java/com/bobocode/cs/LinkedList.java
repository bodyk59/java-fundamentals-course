package com.bobocode.cs;


import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        var linkedList = new LinkedList<T>();
        for (T element : elements) {
            linkedList.add(element);
        }
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        var newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        var tempNode = head;
        var newNode = new Node<>(element);
        if (index != 0) {
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            newNode.next = tempNode.next;
            tempNode.next = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Changes the value of a list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        correctIndex(index);
        var tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        tempNode.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        correctIndex(index);
        var tempNode = head;
        if (index == 0) {
            return head.element;
        }
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        correctIndex(index);
        var removedElement = (T) null;
        if (index != 0) {
            var temp = head;
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            removedElement = temp.next.element;
            temp.next = temp.next.next;
            if (index == size - 1) {
                tail = temp;
            }
        } else {
            removedElement = head.element;
            head = head.next;
        }
        size--;
        return removedElement;
    }

    /**
     * Checks if a specific exists in the list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        var tempElement = head;
        while (Objects.nonNull(tempElement)) {
            if (Objects.equals(tempElement.element, element)) {
                return true;
            }
            tempElement = tempElement.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private void correctIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
