import java.util.*;

class Solution {
    
    int n;
    
    Map<Integer, List<int[]>> graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        int answer = Integer.MAX_VALUE;
        graph = new HashMap<>();
        
        for(int[] f: fares){
            graph.putIfAbsent(f[0], new ArrayList<>());
            graph.putIfAbsent(f[1], new ArrayList<>());
            graph.get(f[0]).add(new int[]{f[1], f[2]});
            graph.get(f[1]).add(new int[]{f[0], f[2]});
        }
        
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[s] = 0;
        
        Queue<int[]> queue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        queue.offer(new int[]{s, 0});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            
            if(dists[curNode] < curDist) continue;
            
            if(graph.containsKey(curNode)){
                for(int[] next: graph.get(curNode)){
                    int nextNode = next[0];
                    int nextDist = next[1] + curDist;
                    if(nextDist < dists[nextNode]){
                        queue.offer(new int[]{nextNode, nextDist});
                        dists[nextNode] = nextDist;
                    }
                }
            }
        }
        
        // dists목록이 다 나왔답니다.
        // 그러면 이 dists를 토대로 A, B를 보내면 되는거죠?
        // ㅇㅇ

        for(int i = 1; i <= n; i++){
            if(dists[i] != Integer.MAX_VALUE){
                answer = Math.min(answer, dists[i] + getMinFare(i, a, b));
            }
        }
        
        return answer;
        
        // 스타트에서 모든 노드를 종착지로 다익스트라를 돌려봄.
        // 종착지에서 A랑 B를 또 다르게 다익스트라 해야함?
        // 합승을 안할수도 있음 ㅋㅋ;
        // 그러면 더 명확해진게, 스타트는 고정이고, 모든 노드를 종착지로 돌리면 되겠네.
        // 그리고 종착지에서 A, B따로 계산한거 가지고 있는 리스트에
        // 최솟값 구해주면 끝.
        // 도로가 끊겨있을 수 있음. 그럼 못가는거지 뭐
    }
    
    int getMinFare(int s, int a, int b){
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[s] = 0;
        
        Queue<int[]> queue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        queue.offer(new int[]{s, 0});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            
            if(dists[curNode] < curDist) continue;
            
            if(graph.containsKey(curNode)){
                for(int[] next: graph.get(curNode)){
                    int nextNode = next[0];
                    int nextDist = next[1] + curDist;
                    if(nextDist < dists[nextNode]){
                        queue.offer(new int[]{nextNode, nextDist});
                        dists[nextNode] = nextDist;
                    }
                }
            }
        }
        
        return dists[a] + dists[b];
    }
}