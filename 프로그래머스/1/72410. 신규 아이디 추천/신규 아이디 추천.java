class Solution {
    public String solution(String new_id) {
        String first = new_id.toLowerCase();
        StringBuilder second = new StringBuilder();
        
        for(char c: first.toCharArray()){
            if(('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.'){
                second.append(c);
            }
        }
        
        StringBuilder third = new StringBuilder();
        boolean isBeforeDot = false;
        for(int i = 0; i < second.length(); i++){
            char c = second.charAt(i);
            if(c == '.'){
                if(!isBeforeDot){
                    third.append(c);
                    isBeforeDot = true;
                }
            }
            else{
                third.append(c);
                isBeforeDot = false;
            }
        }
        
        if(third.length() > 0 && third.charAt(0) == '.') third.deleteCharAt(0);
        if(third.length() > 0 && third.charAt(third.length() - 1) == '.') third.deleteCharAt(third.length() - 1);
        
        if(third.length() == 0) third.append("a");
        
        StringBuilder sixth = new StringBuilder();
        if(third.length() >= 16){
            sixth.append(third.toString().substring(0, 15));
            if(sixth.charAt(sixth.length() - 1) == '.'){
                sixth.deleteCharAt(sixth.length() - 1);
            }
        } else{
            sixth = new StringBuilder(third);
        }
        
        if(sixth.length() > 0){
            while(sixth.length() <= 2){
                sixth.append(sixth.charAt(sixth.length() - 1));
            }
        }
        
        
        return sixth.toString();
    }
}