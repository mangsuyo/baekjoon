import java.util.*;
class Solution {
    // n 노드개수, s 시작지점, a b 집 위치
    // 우리가 구하려고 하는건 택시 합승을 이용하여, A와 B 모두에 도달하는 최소비용의 합
    // A까지 가는 최소비용 구할 수 있음, B까지 가는 최소비용 구할 수 있음.
    // 위의 두가지는 시작점을 기준으로 구해지는것.
    // 내린 이후에는 내린 합승 지점부터 다시 A B 집까지 가는 최소비용 구해야함.
    // 시작지 - 합승 지점 까지의 최소 + 합승 지점 - (A, B) 까지의 최소
    // 근데 누군가의 집까지가 합승 지점일수도있다.
    
    // 완전탐색은 가능함
    // 시작점에서 다익스트라 한번돌리고(for문을 돌려 하나씩 합승지점으로 선택후), 합승지점에서 다시 (A, B)별도 다익스트라 돌려서 합산한 리스트의 최소를 출력하면됨.
    
    
    Map<Integer, List<int[]>> graph;
    int n;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        this.n = n;
        
        graph = new HashMap<>();
        for(int[] fare: fares){
            graph.putIfAbsent(fare[0], new ArrayList<>());
            graph.putIfAbsent(fare[1], new ArrayList<>());
            graph.get(fare[0]).add(new int[]{fare[1], fare[2]});
            graph.get(fare[1]).add(new int[]{fare[0], fare[2]});
        }
        
        
        List<Integer> list = new ArrayList<>();
        
        int[] costs = getMinimumCosts(s);
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < costs.length; i++){
            int cost = costs[i];
            int[] next = getMinimumCosts(i);
            cost = cost + next[a] + next[b];
            if(cost < min){
                min = cost;
            }
        }
        
        
        return min;
    }
    
    int[] getMinimumCosts(int start){
        Queue<int[]> queue = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        queue.offer(new int[]{start, 0});
        
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            if(curCost > costs[curNode]) continue;
            if(graph.containsKey(curNode)){
                for(int[] next: graph.get(curNode)){
                    int nextNode = next[0];
                    int nextCost = curCost + next[1];
                    if(costs[nextNode] > nextCost){
                        queue.offer(new int[]{nextNode, nextCost});
                        costs[nextNode] = nextCost;
                    }
                }
            }
        }
        
        return costs;
    }
}