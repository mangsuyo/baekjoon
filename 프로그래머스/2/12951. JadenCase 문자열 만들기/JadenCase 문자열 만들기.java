import java.util.*;

class Solution {
    public String solution(String s) {
StringBuffer sb = new StringBuffer(s);

    int isFirst = 1;



    for(int i = 0; i < sb.length(); i++){
      if(sb.charAt(i) != ' '){
        if(isFirst == 1){
          sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
          isFirst = 0;
        }
        else{
          sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
        }
      }
      else{
        isFirst = 1;
      }
    }

    return sb.toString();
    }
}