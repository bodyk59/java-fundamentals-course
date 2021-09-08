package com.bobocode.cs;

import java.util.Objects;
import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        if (Objects.isNull(elements)) {
            throw new NullPointerException();
        }
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        for (T element : elements) {
            tree.insert(element);
        }
        return tree;
    }

    @Override
    public boolean insert(T element) {
        var newNode = new Node<>(element);
        if (Objects.isNull(root)) {
            root = newNode;
            size++;
            return true;
        } else {
            return recursiveInsert(root, newNode);
        }
    }

    private boolean recursiveInsert(Node<T> current, Node<T> element) {
        if (element.element.compareTo(current.element) < 0) {
            if (Objects.isNull(current.left)) {
                current.left = element;
                size++;
                return true;
            } else {
                return recursiveInsert(current.left, element);
            }
        } else if (element.element.compareTo(current.element) > 0) {
            if (Objects.isNull(current.right)) {
                current.right = element;
                size++;
                return true;
            } else {
                return recursiveInsert(current.right, element);
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        return recursiveContains(root, element);
    }

    private boolean recursiveContains(Node<T> current, T element) {
        if (Objects.isNull(current)) {
            return false;
        } else if (element.compareTo(current.element) < 0) {
            return recursiveContains(current.left, element);
        } else if (element.compareTo(current.element) > 0) {
            return recursiveContains(current.right, element);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return Objects.isNull(root) ? 0 : recursiveDepth(root) - 1;
    }

    private int recursiveDepth(Node<T> current) {
        return Objects.isNull(current) ? 0 : 1 + Math.max(recursiveDepth(current.left), recursiveDepth(current.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        recursiveInOrderTraversal(root, consumer);
    }

    private void recursiveInOrderTraversal(Node<T> current, Consumer<T> consumer) {
        if (Objects.nonNull(current)) {
            recursiveInOrderTraversal(current.left, consumer);
            consumer.accept(current.element);
            recursiveInOrderTraversal(current.right, consumer);
        }
    }
}
