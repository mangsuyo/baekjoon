class Solution {
    
    int n;
    
    public int solution(String s) { 
        n = s.length();
        int answer = n;
        
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, getPressedStr(i, s));
        }
        return answer;
    }
    
    int getPressedStr(int step, String s){
        StringBuilder sb = new StringBuilder();
        String prev = s.substring(0, step);
        
        int count = 1;
        
        for(int i = step; i < n; i += step){
            String current;
            
            if(i + step <= n){
                current = s.substring(i, i + step);               
            }
            else{
                current = s.substring(i);
            }
            
            if(prev.equals(current)){
                count += 1;
            }
            else{
                if(count > 1){
                    sb.append(count);
                }
                sb.append(prev);
                count = 1;
                prev = current;
            }
        }
        
        if(count > 1){
            sb.append(count);
        }
        sb.append(prev);
        
        return sb.length();
        
    }
}