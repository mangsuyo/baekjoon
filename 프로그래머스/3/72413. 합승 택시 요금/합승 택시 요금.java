import java.util.*;

class Solution {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        for(int[] fare: fares){
            graph.putIfAbsent(fare[0], new ArrayList<>());
            graph.putIfAbsent(fare[1], new ArrayList<>());
            graph.get(fare[0]).add(new int[]{fare[1], fare[2]});
            graph.get(fare[1]).add(new int[]{fare[0], fare[2]});
        }
        
        int answer = 0;
        int[] start_costs = getMinCost(n, s);
        answer = start_costs[a] + start_costs[b];
        int[] a_costs = getMinCost(n, a);
        int[] b_costs = getMinCost(n, b);
    
        for(int i = 1; i <= n; i++){
            int cost = start_costs[i];
            int a_cost = a_costs[i];
            int b_cost = b_costs[i];
            answer = Math.min(answer, cost + a_cost + b_cost);            
        }
        
        return answer;
    }
    
    int[] getMinCost(int n, int start){
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.offer(new int[]{start, 0});
        
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[start] = 0;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[1] > dists[cur[0]]) continue;
            if(!graph.containsKey(cur[0])) continue;
            
            for(int[] next: graph.get(cur[0])){
                int nextNode = next[0];
                int nextDist = cur[1] + next[1];
                if(nextDist < dists[nextNode]){
                    queue.offer(new int[]{nextNode, nextDist});
                    dists[nextNode] = nextDist;
                }
            }
        }
        
        return dists;
    }
}