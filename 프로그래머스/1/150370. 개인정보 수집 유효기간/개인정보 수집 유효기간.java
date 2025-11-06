import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayNumberDate = getNumberDate(today);
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < terms.length; i++){
            String[] cur = terms[i].split(" ");
            map.put(cur[0], Integer.parseInt(cur[1]));
        }
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < privacies.length; i++){
            String[] cur = privacies[i].split(" ");
            int nextDate = getNumberDate(cur[0]) + map.get(cur[1]) * 28;
            if(todayNumberDate >= nextDate){
                list.add(i);
            }
        }
        
        int index = 0;
        int[] answer = new int[list.size()];

        for(Integer num: list){
            answer[index++] = num + 1;
        }
        return answer;
    }
    
    int getNumberDate(String date) {
        String[] cur = date.split("\\.");
        int year = Integer.parseInt(cur[0]);
        int month = Integer.parseInt(cur[1]);
        int day = Integer.parseInt(cur[2]);
        
        int numberDate = 0;
        numberDate += (year - 2000) * 12 * 28;
        numberDate += (month - 1) * 28;
        numberDate += day;
        
        return numberDate;
    }
}