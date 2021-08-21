package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;

import java.util.Objects;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedStack<T> implements Stack<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        var linkedStack = new LinkedStack<T>();
        if (Objects.isNull(elements)) {
            throw new NullPointerException();
        } else if (elements.length < 1) {
            return linkedStack;
        }
        Node<T> nodeTemp = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            if (Objects.nonNull(elements[i])) {
                var newNode = new Node<>(elements[i]);
                if (i != elements.length - 1) {
                    nodeTemp.next = newNode;
                    nodeTemp = nodeTemp.next;
                } else {
                    linkedStack.head = newNode;
                    nodeTemp = linkedStack.head;
                }
                linkedStack.size++;
            } else {
                throw new NullPointerException();
            }
        }
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        var node = new Node<>(element);
        if (Objects.nonNull(head)) {
            node.next = head;
        }
        head = node;
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (!isEmpty()) {
            T deleted = head.element;
            head = head.next;
            size--;
            return deleted;
        }
        throw new EmptyStackException();
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return Objects.isNull(head);
    }
}
