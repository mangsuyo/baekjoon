import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        int now = getDateNumber(today);
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < terms.length; i++){
            String[] cur = terms[i].split(" ");
            map.put(cur[0], Integer.parseInt(cur[1]));
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++){
            String[] cur = privacies[i].split(" ");
            int extraMonth = map.get(cur[1]);
            int after = getDateNumber(cur[0]) + 28 * extraMonth;
            if(now >= after){
                list.add(i + 1);
            }
        }
        int[] answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    
    int getDateNumber(String date){
        String[] cur = date.split("\\.");
        int year = Integer.parseInt(cur[0]) - 2000;
        int month = Integer.parseInt(cur[1]) - 1;
        int day = Integer.parseInt(cur[2]) - 1;
        return year * 12 * 28 + month * 28 + day;
    }
}