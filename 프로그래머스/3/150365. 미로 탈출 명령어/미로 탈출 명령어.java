import java.util.*;

class Solution {
    
    List<String> answers = new ArrayList<>();
    int n;
    int m;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        this.n = n;    
        this.m = m;
        
        int dist = Math.abs(x - r) + Math.abs(y - c);
        
        if(dist % 2 == 0){
            if(k % 2 != 0) return "impossible";
        }
        else{
            if(k % 2 == 0) return "impossible";
        }
                
        if(Math.abs(x - r) + Math.abs(y - c) > k){
            return "impossible";
        }
        
        if(!dfs(x - 1, y - 1, r - 1, c - 1, 0, k, "")){
            return "impossible";
        };

        
        return answers.size() == 0 ? "impossible" : answers.get(0);
    }
    
    boolean dfs(int curR, int curC, int endR, int endC, int depth, int k, String path){
        
        if(depth > k) return false;
        
        if(curR < 0 || curR >= n || curC < 0 || curC >= m) return false;
        
        if((Math.abs(curR - endR) + Math.abs(curC - endC)) > k - depth) return false;
        if(curR == endR && curC == endC && depth == k){
            answers.add(path);
            return true;
        }
        
        if(dfs(curR + 1, curC, endR, endC, depth + 1, k, path + "d")){
            return true;
        };
        if(dfs(curR, curC - 1, endR, endC, depth + 1, k, path + "l")){
            return true;
        };        
        if(dfs(curR, curC + 1, endR, endC, depth + 1, k, path + "r")){
            return true;
        };
        if(dfs(curR - 1, curC, endR, endC, depth + 1, k, path + "u")) return true;
        
        return false;
    }
}