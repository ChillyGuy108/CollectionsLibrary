package org.example;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

class WriteOnlyList<E> extends WriteOnlyCollection<E> implements List<E> {

    public WriteOnlyList(List<E> list){
        super(list);
    }

    private List<E> delegate() {
        return (List<E>) delegate;
    }

    @Override
    public void add(int index, E element) {
        delegate().add(index, element);
    }

    @Override
    public E remove(int index) {
        return delegate().remove(index);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return delegate().addAll(index, c);
    }


    @Override
    public E get(int index) {
        throw unsupported();
    }

    @Override
    public int indexOf(Object o) {
        throw unsupported();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw unsupported();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw unsupported();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw unsupported();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw unsupported();
    }

    @Override
    public E set(int index, E element) {
        throw unsupported();
    }
}