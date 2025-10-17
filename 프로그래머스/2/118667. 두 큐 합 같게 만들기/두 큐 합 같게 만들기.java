import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        Queue<Integer> queueA = new ArrayDeque<>();
        Queue<Integer> queueB = new ArrayDeque<>();
        
        long sum = 0;
        long sumA = 0;
        long sumB = 0;
        
        for(int i = 0; i < queue1.length; i++){
            sum += queue1[i];
            sumA += queue1[i];
            queueA.add(queue1[i]);
        }
        
        for(int i = 0; i < queue2.length; i++){
            sum += queue2[i];
            sumB += queue2[i];
            queueB.add(queue2[i]);
        }
        
        int count = 0;
        
        if(sum % 2 != 0) return -1;
        
        while(count < 3 * queue1.length){
            if(sumA > sumB){
                int num = queueA.poll();
                queueB.add(num);
                sumB += num;
                sumA -= num;
                count += 1;
            }
            else if(sumA < sumB){
                int num = queueB.poll();
                queueA.add(num);
                sumA += num;
                sumB -= num;
                count += 1;
            }
            else{
                return count;
            }
        }
        
        return -1;
    }
}