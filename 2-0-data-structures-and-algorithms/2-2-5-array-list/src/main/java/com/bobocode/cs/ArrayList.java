package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 */
public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 5;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("Incorrect capacity, should be > 0");
        }
        array = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        if (Objects.isNull(elements)) {
            throw new NullPointerException();
        }
        ArrayList<T> arrayList = new ArrayList<>(elements.length);
        for (int i = 0; i < arrayList.array.length; i++) {
            arrayList.array[i] = elements[i];
            arrayList.size++;
        }
        return arrayList;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        resizeArray();
        array[size] = element;
        size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        resizeArray();
        System.arraycopy(array, 0, array, 0, index);
        System.arraycopy(array, index, array, index + 1, array.length - 1 - index);
        array[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return (T) array[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return (T) array[array.length - 1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement = (T) array[index];
        System.arraycopy(array, 0, array, 0, index);
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        size--;
        return removedElement;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        return size >= 1 && Arrays.asList(array).contains(element);
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
     * @return amount of saved elements
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
        array = new Object[0];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            arrayString.append(array[i]).append(" ");
        }
        return arrayString.toString();
    }

    private void resizeArray() {
        if (array.length == size) {
            array = Arrays.copyOf(array, (array.length / 2) * 3);
        }
    }
}
