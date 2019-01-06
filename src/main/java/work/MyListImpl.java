package work;

public class MyListImpl<E> implements MyList<E> {
    private Object[] array;

    public MyListImpl(int size) {
        array = new Object[size];
    }

    @Override
    public void add(E object, int index) {
        array[index] = object;
    }

    @Override
    public E get(int index) {
        return (E) array[index];
    }

    @Override
    public E remove(int index) {
        E e = (E) array[index];
        array[index] = null;
        return e;
    }

    @Override
    public int size() {
        return array.length;
    }
}
