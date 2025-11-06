import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        String first = new_id.toLowerCase();
        StringBuilder second = new StringBuilder();
        for(char c: first.toCharArray()){
            if('a' <= c && c <= 'z' || '0' <= c && c <= '9' || c == '-' || c == '_' || c == '.'){
                second.append(c);
            }
        }
        StringBuilder third = new StringBuilder();
        boolean isBeforeDot = false;
        for(int i = 0; i < second.length(); i++){
            if(second.charAt(i) == '.'){
                if(isBeforeDot){continue;}
                else{
                    isBeforeDot = true;
                }
            }
            else{isBeforeDot = false;}
            third.append(second.charAt(i));
        }
        
        
        if(third.length() > 0 && third.charAt(0) == '.'){
            third.deleteCharAt(0);
        }
        if(third.length() > 0 && third.charAt(third.length() - 1) == '.'){
            third.deleteCharAt(third.length() - 1);
        }
        
        if(third.length() == 0) third.append("a");
        
        if(third.length() >= 16){
            third = new StringBuilder(third.substring(0, 15));
            if(third.charAt(14) == '.') third.deleteCharAt(14);
        }
        
        while(third.length() < 3){
            third.append(third.charAt(third.length() - 1));
        }
        return third.toString();
    }
}