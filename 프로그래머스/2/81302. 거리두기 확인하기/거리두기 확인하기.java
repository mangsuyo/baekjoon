import java.util.*;

class Solution {
    // nextR, nextC가 사람인데
    // 거리두기가 2이상이면 false;
    // 근데 사람과 사람이 개먼데, 카운트로?
    
    class Pair{
        int r;
        int c;
        int count;
        
        public Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        public Pair(int r, int c, int count){
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
    
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1};
    
    int n;
    
    public int[] solution(String[][] places) {
        this.n = 5;
        
        int[] answer = new int[n];
        for(int i = 0; i < n; i++){
            boolean result = inspect(i, places[i]);
            if(result){
                answer[i] = 1;
            }
            else{
                answer[i] = 0;
            }
        }
        return answer;
    }
    
    boolean inspect(int r, String[] place){
        List<Pair> person = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(place[i].charAt(j) == 'P'){
                    person.add(new Pair(i, j, 0));
                }
            }
        }
        
        for(int i = 0; i < person.size(); i++){
            for(int j = i + 1; j < person.size(); j++){
                Pair p1 = person.get(i);
                Pair p2 = person.get(j);
                int dist = Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
                if(dist <= 2){
                    boolean result = bfs(place, p1, p2);
                    if(!result) return false;
                }
            }
        }
        
        return true;
    }
    
    boolean bfs(String[] place, Pair p1, Pair p2){
        Queue<Pair> queue = new ArrayDeque<>(List.of(p1));
        boolean[][] visited = new boolean[n][n];
        visited[p1.r][p1.c] = true;
        
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];
                if(0 <= nextR && nextR < n && 0 <= nextC && nextC < n){
                    if(!visited[nextR][nextC] && place[nextR].charAt(nextC) != 'X'){
                        if(nextR == p2.r && nextC == p2.c){
                            if(cur.count <= 1) return false;
                        }
                        else{
                        queue.offer(new Pair(nextR, nextC, cur.count + 1));
                        visited[nextR][nextC] = true;
                        }
                    }
                }
            }
        }

        
        return true;
    }
}