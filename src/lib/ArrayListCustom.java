package lib;

public class ArrayListCustom<Type> {
    private Type[] elements;
    private int size; // реальный
    private int capacity; // максимальный

    public int getSize() {
        return size;
    }

    public boolean contains(Type element) {
//        if(getIndex(element)!=-1) return false;
//        else return false;
        return getIndex(element) != -1;
    }

    public ArrayListCustom(int capacity) {
        this.capacity = capacity;
        size = 0;
        elements = (Type[]) new Object[capacity];
    }

    public void add(Type newElement) {
        if (size == capacity)
            resize();

        elements[size++] = newElement;
    }

    public void add(int index, Type newElement) {
        if (index > capacity || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == capacity)
            resize();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = newElement;
        size++;
    }

    private void resize() {
        Type[] newArray = (Type[]) new Object[capacity * 2];

        for (int i = 0; i < capacity; i++) {
            newArray[i] = elements[i];
        }

        capacity = capacity * 2;

        elements = newArray;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            s.append(elements[i]);
            s.append(", ");
        }
        s.append("]");

        return s.toString();
    }


    public Type get(int index) {
        if (index < 0 || index >= capacity)
            throw new IndexOutOfBoundsException();

        return elements[index];
    }

    public int getIndex(Type element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element))
                return i;
        }

        return -1;
    }

    public void delete(int index) {
        for (int j = index; j < size - 1; j++) {
            elements[j] = elements[j + 1];
        }
        size--;
    }
}
