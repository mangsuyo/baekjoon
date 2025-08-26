class Solution {
    public String solution(String new_id) {
        
        // 1단계
        String one = new_id.toLowerCase();
        
        // 2단계
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < one.length(); i++){
            char c = one.charAt(i);
            if('a' <= c && c <= 'z'){
                sb.append(c);
            }
            else if('0' <= c && c <= '9'){
                sb.append(c);
            }
            else if(c == '-' || c == '_' || c == '.'){
                sb.append(c);
            }
        }
        
        // 3단계
        String two = sb.toString();
        sb = new StringBuilder();
        
        int flag = 0;
        for(int i = 0; i < two.length(); i++){
            char c = two.charAt(i);
            if(c == '.'){
                if(flag == 0){
                    sb.append(c);
                    flag = 1;
                }                    
            }
            else{
                flag = 0;
                sb.append(c);
            }
        }
        
        // 4단계
        if(sb.length() > 0 && sb.charAt(0) == '.'){
            sb.deleteCharAt(0);
        }
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == '.'){
            sb.deleteCharAt(sb.length() - 1);
        }
        
        // 5단계
        if(sb.length() == 0){
            sb.append("a");
        }
        
        // 6단계
        if(sb.length() >= 16){
            sb = new StringBuilder(sb.substring(0, 15));
            if(sb.charAt(sb.length() - 1) == '.'){
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        // 7단계
        if(sb.length() <= 2){
            while(sb.length() != 3){
                sb.append(sb.charAt(sb.length() - 1));
            }
        }
        
        
        return sb.toString();
    }
}