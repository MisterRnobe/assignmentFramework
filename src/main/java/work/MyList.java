package work;

public interface MyList<E> {
    void add(E object, int index);
    E get(int index);
    E remove(int index);
    int size();
}
