import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        Queue<int[]> queue = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);
        
        int currentTime = 0;
        int count = 0;
        int n = jobs.length;
        int jobIndex = 0;
        
        while(count < n){
            while(jobIndex < n && jobs[jobIndex][0] <= currentTime){
                queue.offer(new int[]{jobs[jobIndex][0], jobs[jobIndex][1]});
                jobIndex++;
            }

            if(!queue.isEmpty()){
                int[] job = queue.poll();
                currentTime += job[1];
                answer += currentTime - job[0];
                count += 1;
            }
            
            else{
                currentTime = jobs[jobIndex][0];
            }
        }
        
        
        return answer / n;
    }
}