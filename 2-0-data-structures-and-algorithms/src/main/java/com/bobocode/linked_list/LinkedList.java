package com.bobocode.linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();

        if (elements.length == 0) {
            return linkedList;
        }

        linkedList.first = new Node<>(elements[0]);
        linkedList.size++;
        Node<T> current = linkedList.first;

        for (int i = 1; i < elements.length; i++) {
            current.next = new Node<>(elements[i]);
            current = current.next;
            linkedList.size++;
        }

        linkedList.last = current;

        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (first == null) {
            first = new Node<>(element);
            size++;
            return;
        }

        Node<T> previous = first;

        while (true) {
            if (previous.next == null) {
                previous.next = new Node<>(element);
                break;
            }
            previous = previous.next;
        }

        last = previous.next;

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
        Node<T> newNode = new Node<>(element);

        if (isEmpty() && index == 0) {
            first = newNode;
            size++;
            return;
        }

        if (isEmpty() || index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = first;
        Node<T> previous = first;

        for (int i = 0; i <= size; i++) {
            if (i == index) {
                if (index == 0) {
                    newNode.next = first;
                    first = newNode;
                } else if (index == size) {
                    last.next = newNode;
                    last = last.next;
                } else {
                    newNode.next = current;
                    previous.next = newNode;
                }
                break;
            }
            previous = current;
            current = current.next;
        }

        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = first;

        for (int i = 0; i < size; i++) {
            if (i == index) {
                current.element = element;
                break;
            }
            current = current.next;
        }
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
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = first;

        for (int i = 1; i <= index; i++) {
            current = current.next;
        }

        return current.element;
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

        return first.element;
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

        return last.element;
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
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = first;
        Node<T> previous = null;
        T removedElement = null;

        for (int i = 0; i < size; i++) {
            if (i == index) {
                if (Objects.equals(current.element, getFirst())) {
                    removedElement = first.element;
                    first = current.next;
                } else if (Objects.equals(current.element, getLast())) {
                    removedElement = current.element;
                    previous.next = null;
                } else {
                    removedElement = current.element;
                    previous.next = current.next;
                }
                break;
            }
            previous = current;
            current = current.next;
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
        if (isEmpty()) {
            return false;
        }

        Node<T> current = first;

        while (true) {
            if (Objects.equals(current.element, element)) {
                return true;
            }
            current = current.next;
            if (current == null) {
                return false;
            }
        }
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
        this.first = null;
        this.size = 0;
    }
}
