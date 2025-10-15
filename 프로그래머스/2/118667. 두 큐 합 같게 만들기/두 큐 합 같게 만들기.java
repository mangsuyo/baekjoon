import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        long sumA = Arrays.stream(queue1).sum();
        long sumB = Arrays.stream(queue2).sum();
        
        long totalSum = sumA + sumB;
        if(totalSum % 2 != 0) return -1;
        
        Queue<Integer> queueA = new ArrayDeque<>();
        Queue<Integer> queueB = new ArrayDeque<>();
        
        for(int i = 0; i < queue1.length; i++){
            queueA.offer(queue1[i]);
        }
        
        for(int i = 0; i < queue2.length; i++){
            queueB.offer(queue2[i]);
        }
        int i = 0;
        
        while(i < queue1.length * 4){
            if(sumA < sumB){
                int pop = queueB.poll();
                sumB -= pop;
                sumA += pop;
                queueA.offer(pop);
                i++;
            }
            else if(sumA > sumB){
                int pop = queueA.poll();
                sumB += pop;
                sumA -= pop;
                queueB.offer(pop);
                i++;    
            }
            else{
                return i;
            }
        }
        
        return -1;
    }
}