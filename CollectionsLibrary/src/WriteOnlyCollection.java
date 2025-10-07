import java.util.Collection;
import java.util.Iterator;

final class WriteOnlyCollection<E> extends DelegateCollection<E> {
    public WriteOnlyCollection(Collection<E> delegate){
        super(delegate);
    }
    private UnsupportedOperationException unsupported(){
        return new UnsupportedOperationException("This collection is write-only");
    }
    @Override
    public boolean isEmpty(){
        throw unsupported();
    }
    @Override
    public boolean contains(Object o){
        throw unsupported();
    }
    @Override
    public Iterator<E> iterator(){
        throw unsupported();
    }
    @Override
    public boolean add(E e){
        return delegate.add(e);
    }
    @Override
    public boolean remove(Object o){
        return delegate.remove(o);
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        return delegate.addAll(c);
    }
    @Override
    public boolean removeAll(Collection<?> c){
        return delegate.removeAll(c);

    }
    @Override
    public boolean retainAll(Collection<?> c){
        return delegate.retainAll(c);
    }
    @Override
    public void clear(){
        delegate.clear();
    }
}
