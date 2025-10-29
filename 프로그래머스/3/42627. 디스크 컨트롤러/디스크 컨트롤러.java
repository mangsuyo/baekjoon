import java.util.*;

class Solution {
    
    // 언제끝났는지만 알면됨.
    class Job{
        int index;
        int requestTime;
        int costTime;
        
        public Job(int index, int requestTime, int costTime){
            this.index = index;
            this.requestTime = requestTime;
            this.costTime = costTime;
        }
    }
    
    
    public int solution(int[][] jobs) {
        List<Job> list = new ArrayList<>();
        for(int i = 0; i < jobs.length; i++){
            list.add(new Job(i, jobs[i][0], jobs[i][1]));
        }
        
        Queue<Job> queue = new PriorityQueue<>((p1, p2) -> {
            if(p1.costTime == p2.costTime){
                if(p1.requestTime == p2.requestTime){
                    return p1.index - p2.index;
                }
                else{
                    return p1.requestTime - p2.requestTime;
                }
            }
            else{
                return p1.costTime - p2.costTime;
            }
        });
        
            
        for(Job j: list){
            queue.offer(j);
        }
        
        int answer = 0;
        int time = 0;
        while(!queue.isEmpty()){
            Job cur = queue.poll();
            if(time < cur.requestTime) time = cur.requestTime;
            time = time + cur.costTime;
            answer += time - cur.requestTime;
        }
        return answer / jobs.length;
    }
}