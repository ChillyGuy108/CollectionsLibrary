package org.example;

import java.util.Collection;

abstract class ReadOnlyCollection<E> extends DelegateCollection<E> {
    public ReadOnlyCollection(Collection<E> delegate){
        super(delegate);
    }
    protected UnsupportedOperationException unsupported(){
        return new UnsupportedOperationException("This collection is read-only");
    }
    @Override
    public boolean add(E e){
        throw unsupported();
    }
    @Override
    public boolean remove(Object o){
        throw unsupported();
    }
    @Override
    public  boolean addAll(Collection<? extends E> c){
        throw unsupported();
    }
    @Override
    public  boolean removeAll(Collection<?> c){
        throw unsupported();
    }
    @Override
    public  boolean retainAll(Collection<?> c){
        throw unsupported();
    }
    @Override
    public  void clear(){
        throw unsupported();
    }
}
