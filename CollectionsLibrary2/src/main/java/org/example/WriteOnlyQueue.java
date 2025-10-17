package org.example;

import java.util.Collection;
import java.util.Queue;

class WriteOnlyQueue<E> extends WriteOnlyCollection<E> implements Queue<E> {

    public WriteOnlyQueue(Collection<E> delegate) {
        super(delegate);
    }

    private Queue<E> queue() {
        return (Queue<E>) delegate;
    }


    @Override
    public boolean offer(E e) {
        return queue().offer(e);
    }

    @Override
    public E poll() {
        throw unsupported();
    }

    @Override
    public E remove() {
        if (delegate.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        E element = poll();
        if (element == null) {
            throw new java.util.NoSuchElementException();
        }
        return element;
    }

    @Override
    public E element() {
        throw unsupported();
    }

    @Override
    public E peek() {
        throw unsupported();
    }
}