import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        Queue<int[]> queue = new PriorityQueue<>((t1, t2) -> t1[1] - t2[1]);
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        
        int curT = 0;
        int jobIndex = 0;
        int count = 0;
        int answer = 0;
        int n = jobs.length;
        
        while(count < n){
            while(jobIndex < n && jobs[jobIndex][0] <= curT){
                queue.offer(new int[]{jobs[jobIndex][0], jobs[jobIndex][1]});
                jobIndex += 1;
            }
            
            if(!queue.isEmpty()){
                int[] cur = queue.poll();
                curT += cur[1];
                answer += curT - cur[0];
                count += 1;
            }
            else{
                curT = jobs[jobIndex][0];
            }
        }
        return answer / n;
    }
}