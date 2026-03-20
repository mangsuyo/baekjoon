import java.util.*;

class Solution {
    
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int index = 0;
        for(String[] place: places){
            answer[index++] = isValid(place);
        }
        
        return answer;
    }
    
    
    int isValid(String[] place){
        char[][] graph = new char[5][5];
        for(int i = 0; i < 5; i++){
            String[] row = place[i].split("");
            for(int j = 0; j < 5; j++){
                graph[i][j] = row[j].charAt(0);
            }
        }
        
        List<Pair> users = new ArrayList<>();
        
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(graph[i][j] == 'P') {
                    users.add(new Pair(i, j));         
                }
            }
        }
        
        for(Pair user: users){
            int answer = bfs(graph, user);
            if(answer == 0) return 0;
        }
        
        return 1;
 
    }
    
    int bfs(char[][] graph, Pair user){
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];        
        user.dist = 0;
        queue.offer(user);    
        visited[user.r][user.c] = true;
        
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextR = dr[i] + cur.r;
                int nextC = dc[i] + cur.c;
                if(0 <= nextR && nextR < 5 && 0 <= nextC && nextC < 5){
                    if(!visited[nextR][nextC] && graph[nextR][nextC] != 'X'){
                        if(graph[nextR][nextC] == 'P' && cur.dist < 2) return 0;
                            queue.offer(new Pair(nextR, nextC, cur.dist + 1));
                            visited[nextR][nextC] = true;
                    }
                }
            }
        }
        
        return 1;
    }
    
    
    
    class Pair{
        int r;
        int c;
        int dist;
        
        public Pair(int row, int col){
            this.r = row;
            this.c = col;
        }
        
        public Pair(int row, int col, int dist){
            this.r = row;
            this.c = col;
            this.dist = dist;
        }
    }
}