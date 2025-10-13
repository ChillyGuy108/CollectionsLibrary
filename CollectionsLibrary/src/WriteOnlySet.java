import java.util.Set;

public final class WriteOnlySet<E> extends WriteOnlyCollection<E> implements Set<E> {
    public WriteOnlySet(Set<E> set) {
        super(set);
    }


}
