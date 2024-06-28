public class DynamicArray implements Iterable<Integer>{
    private int[] array;
    private int length=0;
    private int capacity=0;

    public DynamicArray(){
        this(5);
    }

    public DynamicArray(int capacity){
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity : " + capacity); 
        this.capacity=capacity;
        array = new int[capacity];
    }

    public int get(int index){
        return array[index];
    }

    public void set(int index , int element){
        if(index >= 0 && index < length) array[index]=element;
    }

    public boolean isEmpty(){
        return length==0;
    }

    public int size(){
        return length;
    }

    public void add(int element){
        if(length+1 > capacity){
            if(capacity == 0) capacity = 1;
            if(capacity > 0) capacity *=2;
            array = java.util.Arrays.copyOf(array , capacity);
            // int[] newarray = new int[capacity];
            // System.arraycopy(array, 0, newarray, 0, length);
            // for (int i = 0; i < length; i++) {
            //     newarray[i] = array[i];
            // }
        }
        array[length++] = element;
    }

    public int removeAt(int index){
        if(index < 0 && index > length) throw new IndexOutOfBoundsException();
            int data = array[index];
            System.arraycopy(array, index+1, array, index, length-index-1);
            // int[] newarray = new int[length-1];
            // for(int i = 0 , j = 0 ; i < length ; i++ , j++){
            //     if(i == index) j--;
            //     else
            //     newarray[i] = array[j];
            // }
            // array = newarray;
            capacity = --length;
            return data;
    }

    public boolean remove(int element){
        for(int i = 0 ; i < length ; i++){
            if(array[i] == element){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexof(int element){
        for(int i = 0 ; i < length ; i++){
            if(array[i] == element){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int element){
        return indexof(element) != -1;
    }

    public void sort(){
        java.util.Arrays.sort(array , 0 , length);
        //Dual-Pivot Quicksort or Merge Sort
    }

    public void reverse(){
        for (int i = 0; i < length/2; i++) {
            int temp = array[i];
            array[i] = array[length-i-1];
            array[length-i-1] = temp;
        }
    }

    @Override 
    public java.util.Iterator<Integer> iterator(){
        return new java.util.Iterator<Integer>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < length;
            }
            @Override
            public Integer next() {
                return array[index];
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString(){
        if(length == 0) return "[]";
        else{
        StringBuilder sb = new StringBuilder(length).append("[");
        for(int i = 0 ; i < length - 1 ; i++){
            sb.append(array[i] + " , ");
        }
        return sb.append(array[length-1] + "]").toString();
        }
    }

    public static void main(String[] args) {

        DynamicArray ar = new DynamicArray(50);
        ar.add(3);
        ar.add(7);
        ar.add(6);
        ar.add(-2);
        ar.add(0);
    
        ar.sort(); // [-2, 3, 6, 7]
    
        // Prints [-2, 3, 6, 7]
        for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

        ar.reverse();
    
        // Prints [-2, 3, 6, 7]
        System.out.println(ar.toString());
      }
    }