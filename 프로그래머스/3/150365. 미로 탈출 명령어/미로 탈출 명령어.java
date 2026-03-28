import java.util.*;

class Solution {
    char[] d = new char[]{'d', 'l', 'r', 'u'};
    int[] dr = new int[]{1, 0, 0, -1};
    int[] dc = new int[]{0, -1, 1, 0};
    int r;
    int c;
    int n;
    int m;
    StringBuilder sb = new StringBuilder();
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        x--;
        y--;
        r--;
        c--;
        this.r = r;
        this.c = c;
        this.n = n;
        this.m = m;
        
        if(dfs(x, y, 0, k)){
            return sb.toString();
        }else{
            return "impossible";
        }
    }
    
    
    boolean dfs(int x, int y, int step, int k){
        if(x < 0 || x >= n || y < 0 || y >= m) return false;
        if(x == r && y == c && step == k) return true;
        int dist = Math.abs(r - x) + Math.abs(c - y);
        if(dist > k - step) return false;
        if((k - step - dist) % 2 != 0) return false;
        
        for(int i = 0; i < 4; i++){
            int nextR = x + dr[i];
            int nextC = y + dc[i];
            sb.append(d[i]);
            if(dfs(nextR, nextC, step + 1, k)) return true;
            sb.deleteCharAt(sb.length() - 1);
        }
        return false;
    }
}