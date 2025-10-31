// 출발지와 도착지가 같으므로, 출발에서 정상까지만 최소를 유지해주면 될듯?
// 총비용의 최소가 아니라, 간선의 최소

import java.util.*;

class Solution {
    
    Map<Integer, List<int[]>> graph;
    int n;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        graph = new HashMap<>();
        this.n = n;
        
        for(int[] p: paths){
            graph.putIfAbsent(p[0], new ArrayList<>());
            graph.putIfAbsent(p[1], new ArrayList<>());
            graph.get(p[0]).add(new int[]{p[1], p[2]});
            graph.get(p[1]).add(new int[]{p[0], p[2]});
        }
        Arrays.sort(summits);
        
        Queue<int[]> queue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        
        int[] dists = getMinPath(gates, summits);
        
        
        for(int summit: summits){
            queue.offer(new int[]{summit, dists[summit]});
        }
        
        return queue.poll();
    }
    
    int[] getMinPath(int[] gates, int[] summits){
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
                
        Queue<int[]> queue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        
        for(int i = 0; i < gates.length; i++){
            dists[gates[i]] = 0;   
            queue.offer(new int[]{gates[i], 0});
        }
        
        Map<Integer, Boolean> summitMap = new HashMap<>();
        for(int i = 0; i < summits.length; i++){
            summitMap.put(summits[i], true);
        }
        
        Map<Integer, Boolean> gateMap = new HashMap<>();
        for(int i = 0; i < gates.length; i++){
            gateMap.put(gates[i], true);
        }

        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            int flag = 0;
            if(dists[curNode] < curDist) continue;
            if(summitMap.containsKey(curNode)) continue;
            
            
            if(graph.containsKey(curNode)){
                for(int[] next: graph.get(curNode)){
                    int nextNode = next[0];
                    if(gateMap.containsKey(nextNode)) continue;
                    int nextDist = Math.max(next[1], curDist);
                    if(nextDist < dists[nextNode]){
                        queue.offer(new int[]{nextNode, nextDist});
                        dists[nextNode] = nextDist;
                    }
                }
            }
        }
        
        
        return dists;
    }
}