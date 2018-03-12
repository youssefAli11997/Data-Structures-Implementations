package Heap;

public class MaxHeap {
    int[] A;
    int n;
    int heapSize;

    public MaxHeap(int length){
        n = length;
        A = new int[n];
        heapSize = 0;
    }

    public MaxHeap(int[] A){
        this.A = A;
        n = A.length;
        heapSize = A.length;
    }

    public void Heapsort(){
        Build_Max_Heap();
        for(int i=n-1; i>=2; i--){
            swap(0,i);
            heapSize--;
            max_Heapify(0);
        }
    }

    private void Build_Max_Heap(){
        for(int i=n/2; i>=0; i--){
            max_Heapify(i);
        }
    }

    private void max_Heapify(int index){
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        System.out.println(index);
        if(left < heapSize) if(A[left] > A[largest])
            largest = left;
        if(right < heapSize) if(A[right] > A[largest])
            largest = right;
        
        if(largest != index){
            swap(largest, index);
            max_Heapify(largest);
        }
    }

    private void swap(int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }


    public int[] getA() {
        return A;
    }

    public int getN() {
        return n;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public static void main(String[] args){
        int[] unsortedArr =  {1,1,0,0,1,1,1,1};//{10,4,3,15,0,-1,2,5};
        MaxHeap heap = new MaxHeap(unsortedArr);
        heap.Heapsort();
        int[] sortedArr = heap.getA();
        for (int x : sortedArr) {
            System.out.print(x + " ");
        }
    }
}
