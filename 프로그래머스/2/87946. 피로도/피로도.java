import java.util.*;

class Solution {
    
    int n;
    int m;
    
    int answer = 0;
    
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        this.n = dungeons.length;
        this.m = dungeons[0].length;
        
        visited = new boolean[n];
        backtrack(dungeons, k, 0);
        return answer;
    }
    
    void backtrack(int[][] dungeons, int k, int count){
        if(answer < count) answer = count;
        
        for(int i = 0; i < n; i++){
            int[] dungeon = dungeons[i];
            int required = dungeon[0];
            int cost = dungeon[1];
            if(k >= required && !visited[i]){
                visited[i] = true;
                backtrack(dungeons, k - cost, count + 1);
                visited[i] = false;
            }
            
        }
    }
}