package org.example;

import java.util.Set;


public final class ReadOnlySet<E> extends ReadOnlyCollection<E> implements Set<E> {

    public ReadOnlySet(Set<E> set) {
        super(set);
    }

}