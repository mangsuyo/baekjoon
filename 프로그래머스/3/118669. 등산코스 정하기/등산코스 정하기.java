import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};        
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Arrays.sort(summits);
        
        Map<Integer, Boolean> summitMap = new HashMap<>();
        
        for(int summit: summits){
            summitMap.put(summit, true);
        }
        
        for(int[] path: paths){
            graph.putIfAbsent(path[0], new ArrayList<>());
            graph.putIfAbsent(path[1], new ArrayList<>());
            graph.get(path[0]).add(new int[]{path[1], path[2]});
            graph.get(path[1]).add(new int[]{path[0], path[2]});
        }
        
        Queue<int[]> queue = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        
        
        Map<Integer, Boolean> gateMap = new HashMap<>();
        
        for(int gate: gates){
            queue.offer(new int[]{gate, 0});
            dists[gate] = 0;
            gateMap.put(gate, true);
        }
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curIntensity = cur[1];
            if(summitMap.containsKey(curNode)) continue;
            if(curIntensity > dists[curNode]) continue;
            if(graph.containsKey(curNode)){
                for(int[] next: graph.get(curNode)){
                    int nextNode = next[0];
                    if(gateMap.containsKey(nextNode)) continue;
                    int nextIntensity = Math.max(next[1], curIntensity);
                    if(dists[nextNode] > nextIntensity){
                        queue.offer(new int[]{nextNode, nextIntensity});
                        dists[nextNode] = nextIntensity;
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minSummit = 0;
        
        for(int summit: summits){
            if(dists[summit] < min){
                min = dists[summit];
                minSummit = summit;
            }
        }
        
        return new int[]{minSummit, dists[minSummit]};
    }
}