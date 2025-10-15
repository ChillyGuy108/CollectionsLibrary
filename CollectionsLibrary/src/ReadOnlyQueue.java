import java.util.Collection;
import java.util.Queue;

class ReadOnlyQueue<E> extends ReadOnlyCollection<E> implements Queue<E> {

    public ReadOnlyQueue(Collection<E> delegate) {
        super(delegate);
    }

    private Queue<E> queue() {
        return (Queue<E>) delegate;
    }


    @Override
    public E element() {
        return queue().element();
    }

    @Override
    public E peek() {
        return queue().peek();
    }


    @Override
    public boolean offer(E e) {
        throw unsupported();
    }

    @Override
    public E remove() {
        throw unsupported();
    }

    @Override
    public E poll() {
        throw unsupported();
    }
}