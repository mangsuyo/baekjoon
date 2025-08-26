import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termHash = new HashMap<>();
        Map<String, String> privacyMap = new HashMap<>();
        
        for(String str: terms){
            String[] info = str.split(" ");
            termHash.put(info[0], Integer.parseInt(info[1]));
        }
        List<Integer> answers = new ArrayList<>();
        int index = 0;
        for(String privacy: privacies){
            String[] info = privacy.split(" ");
            int finalDate = getFinalDate(info[0], termHash.get(info[1]));
            index++;
            if(!isValid(getDateToInteger(today), finalDate)){
                answers.add(index);
            }
        }
        
        int[] result = new int[answers.size()];
        int k = 0;
        for(int i = 0; i < answers.size(); i++){
            result[k++] = answers.get(i);
        }
        
        return result;
    }
    
    
    boolean isValid(int today, int date){
        if(today >= date) return false;
        return true;
    }
               
    public int getFinalDate(String prevDate, Integer month){
        int date = getDateToInteger(prevDate);
        return date + (month * 28);
    }
    
    public int getDateToInteger(String date){
        String[] dateYMD = date.split("\\.");
        String year = dateYMD[0];
        String month = dateYMD[1];
        String day = dateYMD[2];
        return Integer.parseInt(year) * 12 * 28 + Integer.parseInt(month) * 28 + Integer.parseInt(day);
    }
}