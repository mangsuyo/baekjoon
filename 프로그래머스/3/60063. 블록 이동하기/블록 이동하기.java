import java.util.*;

class Solution {
    
    class Pair{
        int r;
        int c;
        
        public Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    class Robot{
        Pair p1;
        Pair p2;
        int count;
        
        public Robot(Pair p1, Pair p2, int count){
            if(p1.c == p2.c){
                if(p1.r < p2.r){
                    this.p1 = p1;
                    this.p2 = p2;
                    this.count = count;
                }
                else{
                    this.p1 = p2;
                    this.p2 = p1;
                    this.count = count;
                }
            }
            else{
                if(p1.c < p2.c){
                    this.p1 = p1;
                    this.p2 = p2;
                    this.count = count;
                }
                else{
                    this.p1 = p2;
                    this.p2 = p1;
                    this.count = count;
                }
            }
        }
    }
    
    int n;
    
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1};
    boolean[][][][] visited;
    int[][] board;
    Queue<Robot> queue;
    
    public int solution(int[][] board) {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 1);
        Robot robot = new Robot(p1, p2, 0);
        this.n = board.length;
        visited = new boolean[n][n][n][n];
        visited[0][0][0][1] = true;
        
        this.board = board;
        
        this.queue = new ArrayDeque<>();
        queue.offer(robot);
        
        while(!queue.isEmpty()){
            Robot cur = queue.poll();
            int r1 = cur.p1.r;
            int c1 = cur.p1.c;
        
            int r2 = cur.p2.r;
            int c2 = cur.p2.c;
            
            if(r1 == n - 1 && c1 == n - 1 || r2 == n - 1 && c2 == n - 1) return cur.count;
            
            for(int i = 0; i < 4; i++){
                int nextR1 = r1 + dr[i];
                int nextC1 = c1 + dc[i];
                int nextR2 = r2 + dr[i];
                int nextC2 = c2 + dc[i];
                if(isValid(nextR1, nextC1, nextR2, nextC2)){
                    addNewState(nextR1, nextC1, nextR2, nextC2, cur.count);
                }
            }
            // 회전
            rotate(r1, c1, r2, c2, cur.count);
        }
        
        return -1;

    }
    
    void rotate(int r1, int c1, int r2, int c2, int count){
        if(c1 == c2){
            if(isValid(r1 + 1, c1 - 1, r2, c2)){
                if(board[r1][c1 - 1] == 0){
                    addNewState(r1 + 1, c1 - 1, r2, c2, count);
                }
            }
            if(isValid(r1 + 1, c1 + 1, r2, c2)){
                if(board[r1][c1 + 1] == 0){
                    addNewState(r1 + 1, c1 + 1, r2, c2, count);
                }
            }
            if(isValid(r1, c1, r2 - 1, c2 - 1)){
                if(board[r2][c2 - 1] == 0){
                    addNewState(r1, c1, r2 - 1, c2 - 1, count);
                }
            }
            if(isValid(r1, c1, r2 - 1, c2 + 1)){
                if(board[r2][c2 + 1] == 0){
                    addNewState(r1, c1, r2 - 1, c2 + 1, count);                    
                }
            }            
        }
        else{
            if(isValid(r1 - 1, c1 + 1, r2, c2)){
                if(board[r1 - 1][c1] == 0){
                    addNewState(r1 - 1, c1 + 1, r2, c2, count);                    
                }
            }             
            if(isValid(r1 + 1, c1 + 1, r2, c2)){
                if(board[r1 + 1][c1] == 0){
                    addNewState(r1 + 1, c1 + 1, r2, c2, count);                    
                }
            }     
            if(isValid(r1, c1, r2 - 1, c2 - 1)){
                if(board[r2 - 1][c2] == 0){
                    addNewState(r1, c1, r2 - 1, c2 - 1, count);                    
                }
            } 
            if(isValid(r1, c1, r2 + 1, c2 - 1)){
                if(board[r2 + 1][c2] == 0){
                    addNewState(r1, c1, r2 + 1, c2 - 1, count);                    
                }
            }            
        }
    }
    
    void addNewState(int r1, int c1, int r2, int c2, int count){
        Robot robot = new Robot(new Pair(r1, c1), new Pair(r2, c2), count + 1);
        if(!visited[robot.p1.r][robot.p1.c][robot.p2.r][robot.p2.c]){
            queue.offer(robot);
            visited[robot.p1.r][robot.p1.c][robot.p2.r][robot.p2.c] = true;
        }
        
    }
    
    boolean isValid(int r1, int c1, int r2, int c2){
        if(r1 < 0 || r1 >= n || c1 < 0 || c1 >= n) return false;
        if(r2 < 0 || r2 >= n || c2 < 0 || c2 >= n) return false;
        if(board[r1][c1] == 1 || board[r2][c2] == 1) return false;
        return true;
    }
}