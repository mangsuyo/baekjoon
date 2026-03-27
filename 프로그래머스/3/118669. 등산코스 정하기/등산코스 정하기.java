import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for(int[] path: paths){
            graph.putIfAbsent(path[0], new ArrayList<>());
            graph.putIfAbsent(path[1], new ArrayList<>());
            graph.get(path[0]).add(new int[]{path[1], path[2]});
            graph.get(path[1]).add(new int[]{path[0], path[2]});
        }
        
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        
        for(int i = 0; i < gates.length; i++){
            queue.offer(new int[]{gates[i], 0});
            dists[gates[i]] = 0;
        }
        
        
        Set<Integer> gateSet = new HashSet<>();
        for(int gate: gates){
            gateSet.add(gate);
        }
        
        Set<Integer> summitSet = new HashSet<>();
        for(int summit: summits){
            summitSet.add(summit);
        }
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(summitSet.contains(cur[0])) continue;
            if(cur[1] > dists[cur[0]]) continue;
            if(!graph.containsKey(cur[0])) continue;
            
            for(int[] next: graph.get(cur[0])){
                int nextNode = next[0];
                if(gateSet.contains(nextNode)) continue;
                int nextDist = Math.max(next[1], cur[1]);
                if(nextDist < dists[nextNode]){
                    queue.offer(new int[]{nextNode, nextDist});
                    dists[nextNode] = nextDist;
                }
            }                
        }
        
        int minDist = Integer.MAX_VALUE;
        int summitNumber = 0;
        
        Arrays.sort(summits);
        for(int i = 0; i < summits.length; i++){
            if(minDist > dists[summits[i]]){
                minDist = dists[summits[i]];
                summitNumber = summits[i];
            }
        }
        return new int[]{summitNumber, minDist};
    }
}