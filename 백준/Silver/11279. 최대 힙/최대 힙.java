import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Heap {
    ArrayList<Integer> heap;

    Heap() {
        this.heap = new ArrayList<>();
    }

    void swap(int idx1, int idx2) {
        int temp;
        temp = this.heap.get(idx1);
        this.heap.set(idx1, this.heap.get(idx2));
        this.heap.set(idx2, temp);
    }

    void push(int value) {
        this.heap.add(value);
        shiftUp();
    }

    void shiftUp() {
        int idx = this.heap.size() - 1;
        int parentIdx = (idx - 1) / 2;
        while (0 <= parentIdx
                && this.heap.get(idx) > this.heap.get(parentIdx)) {

            swap(idx, parentIdx);
            idx = parentIdx;
            parentIdx = (idx - 1) / 2;
        }
    }

    int peek() {
        if (this.heap.isEmpty()) return 0;
        if (this.heap.size() == 1) return this.heap.remove(0);
        int value = this.heap.get(0);
        this.heap.set(0, this.heap.remove(this.heap.size() - 1));
        shiftDown();
        return value;
    }

    void shiftDown() {
        int idx = 0;
        int leftIdx = idx * 2 + 1;
        int rightIdx = idx * 2 + 2;

        while (leftIdx < this.heap.size() && this.heap.get(leftIdx) > this.heap.get(idx) || rightIdx < this.heap.size() && this.heap.get(idx) < this.heap.get(rightIdx)) {
            int biggerIdx = leftIdx;
            if (rightIdx < this.heap.size() && this.heap.get(rightIdx) > this.heap.get(leftIdx)) {
                biggerIdx = rightIdx;
            }
            swap(idx, biggerIdx);
            idx = biggerIdx;
            leftIdx = idx * 2 + 1;
            rightIdx = idx * 2 + 2;
        }
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Heap heap = new Heap();
        List<Integer> answer = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                answer.add((heap.peek()));
            } else {
                heap.push(command);
            }
        }

        for(int result : answer){
            System.out.println(result);
        }

    }

}