import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public final class ReadOnlySet<E> extends ReadOnlyCollection<E> implements Set<E> {

    public ReadOnlySet(Set<E> set) {
        super(set);
    }

    private Set<E> delegate() {
        return (Set<E>) delegate;
    }
    @Override
    public Iterator<E> iterator() {
        return delegate().iterator();
    }

    @Override
    public int size() {
        return delegate().size();
    }

    @Override
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate().contains(o);
    }

    @Override
    public Object[] toArray() {
        return delegate().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegate().toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegate().containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return delegate().equals(o);
    }

    @Override
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override
    public Spliterator<E> spliterator() {
        return delegate().spliterator();
    }
}