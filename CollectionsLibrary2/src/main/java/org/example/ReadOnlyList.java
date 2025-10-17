package org.example;

import java.util.List;
import java.util.ListIterator;
import java.util.Collection;

public final class ReadOnlyList<E> extends ReadOnlyCollection<E> implements List<E> {

    public ReadOnlyList(List<E> list){
        super(list);
    }

    private List<E> delegate() {
        return (List<E>) delegate;
    }

    @Override
    public E set(int index, E element) {
        throw unsupported();
    }

    @Override
    public void add(int index, E element) {
        throw unsupported();
    }

    @Override
    public E remove(int index) {
        throw unsupported();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw unsupported();
    }

    @Override
    public E get(int index) {
        return delegate().get(index);
    }

    @Override
    public int indexOf(Object o) {
        return delegate().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return delegate().lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return delegate().listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new ReadOnlyList<>(delegate().subList(fromIndex, toIndex));
    }


}