import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 1단계
        String stepOne = new_id.toLowerCase();
        
        // 2단계
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < stepOne.length(); i++){
            char c = stepOne.charAt(i);
            if('a' <= c && c <= 'z'){
                temp.append(c);
            }
            else if('0' <= c && c <= '9'){
                temp.append(c);
            }
            else if(c == '-' || c == '_' || c == '.'){
                temp.append(c);
            }
        }
        
        String stepTwo = temp.toString();
        temp = new StringBuilder();
        boolean flag = false;
        // 3단계
        for(int i = 0; i < stepTwo.length(); i++){
            char c = stepTwo.charAt(i);
            if(c == '.'){
                if(!flag){
                    temp.append(c);
                    flag = true;
                }
            }else{
                flag = false;
                temp.append(c);
            }
        }
        
        String stepThree = temp.toString();
                
        // 4단계
        if(temp.length() > 0){
            if(temp.charAt(0) == '.'){
                temp.deleteCharAt(0);
            }
        }
        
        
            if(temp.length() > 0 && temp.charAt(temp.length() - 1) == '.'){
                temp.deleteCharAt(temp.length() - 1);
            }
        // 5단계
        
        if(temp.length() == 0){
            temp.append("a");
        }
        
        // 6단계
        String stepFive = temp.toString();
        
        if(stepFive.length() >= 16){
            temp = new StringBuilder(stepFive.substring(0, 15));
            if(temp.charAt(temp.length() - 1) == '.'){
                temp.deleteCharAt(temp.length() - 1);
            }
        }
        
        // 7단계
        if(temp.length() <= 2){
            while(temp.length() != 3){
                temp.append(temp.charAt(temp.length() -1));
            }
        }
        
            
        return temp.toString();
    }
}