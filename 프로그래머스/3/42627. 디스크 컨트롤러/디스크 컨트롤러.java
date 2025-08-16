import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int count = 0;
        Arrays.sort(jobs, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        Queue<int[]> queue = new PriorityQueue<>((t1, t2) -> t1[1] - t2[1]);
        
        int curTime = 0;
        int jobIndex = 0;
        int total = 0;
        while(count < jobs.length){            
            while(jobIndex < jobs.length && jobs[jobIndex][0] <= curTime){
                queue.offer(new int[]{jobs[jobIndex][0], jobs[jobIndex][1]});
                jobIndex += 1;
            }
            
            if(queue.isEmpty()){
                if(jobIndex < jobs.length){
                    curTime = jobs[jobIndex][0];
                }
            }
            else{
                int[] task = queue.poll();
                curTime += task[1];
                total += curTime - task[0];
                count += 1;
            }
        }
        
        return total / count;
    }
}