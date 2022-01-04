package Arrays;
/**
 * A wrapper for the Java array just for this application.
 */
public class Array<E> {
    private static final int DEFAULT_SIZE = 3; // Small to make it easier to observe the shifting effect.
    private E[] arr;
    int size;

    public Array() {
        arr = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        return arr[0];
    }

    public void clear(){
        arr = (E[]) new Object[DEFAULT_SIZE];;
        size = 0;
    }

    public void add(int index, E value) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException("");
        if (size < arr.length) { // No need to expand, shift right away.
            for (int i = size; i >= Math.max(1, index); i--) { // Don't let i reach zero!!!
                arr[i] = arr[i - 1];
            }
            arr[index] = value;
        } else {
            E[] newArr = (E[]) new Object[arr.length * 2 + 1];
            for (int i = 0; i < index; i++)
                newArr[i] = arr[i];
            newArr[index] = value;
            for (int i = index + 1; i < size + 1; i++)
                newArr[i] = arr[i - 1];
            arr = newArr;
        }
        size++;
    }

    /**
     * Removes an element from the array given its index.
     */
    public void remove(int index) throws IllegalArgumentException {
        if (index < 0 || index > size - 1) throw new IllegalArgumentException("");
        size--;
        for (int i = index; i < size; i++)
            arr[i] = arr[i + 1];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public String displayArr() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += arr[i] + " ";
        }
        return s;
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        arr.add(arr.size(), 5);
        arr.add(arr.size(), 41);
        arr.add(arr.size(), 63);
        arr.add(arr.size(), 78);
        arr.add(arr.size(), 6);
        arr.add(arr.size(), 42);


        System.out.println(arr.displayArr());
    }
}
