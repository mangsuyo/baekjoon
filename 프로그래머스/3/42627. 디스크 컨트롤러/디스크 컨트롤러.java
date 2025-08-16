import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int curTime = 0;
        int count = 0;
        int jobIndex = 0;
        int n = jobs.length;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        while(count < n){
            while(jobIndex < n && jobs[jobIndex][0] <= curTime){
                queue.offer(new int[]{jobs[jobIndex][0], jobs[jobIndex][1]});
                jobIndex += 1;
            }
            
            if(!queue.isEmpty()){
                int[] task = queue.poll();
                curTime += task[1];
                answer += curTime - task[0];
                count += 1;
            }
            else{
                curTime = jobs[jobIndex][0];
            }
        }
        return answer / n;
    }
}