package org.example;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;

abstract class DelegateCollection<E> implements Collection<E> {
    protected final Collection<E> delegate;

    protected DelegateCollection(Collection<E> delegate){
        if(delegate==null){
            throw new NullPointerException("Delegate cannot be null");
        }
        this.delegate=delegate;




    }
    @Override
    public int size(){
        return delegate.size();
    }
    @Override
    public boolean isEmpty(){
        return delegate.isEmpty();
    }
    @Override
    public boolean contains(Object o){
        return delegate.contains(o);
    }
    @Override
    public Iterator<E> iterator(){
        return delegate.iterator();
    }
    @Override
    public Object[] toArray(){
        return delegate.toArray();
    }
    @Override
    public <T> T[] toArray(T[] a){
        return delegate.toArray(a);
    }
    @Override
    public boolean containsAll(Collection<?> c){
        return delegate.containsAll(c);
    }
    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }
    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public Spliterator<E> spliterator() {
        return delegate.spliterator();
    }

    @Override
    public abstract boolean add(E e);
    @Override
    public abstract boolean remove(Object o);
    @Override
    public abstract boolean addAll(Collection<? extends E> c);
    @Override
    public abstract  boolean removeAll(Collection<?> c);
    @Override
    public abstract boolean retainAll(Collection<?> c);
    @Override
    public abstract void clear();

}
