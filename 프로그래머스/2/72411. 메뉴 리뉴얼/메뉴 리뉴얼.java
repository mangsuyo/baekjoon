import java.util.*;

class Solution {
    Map<String, Integer> map;
    
    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        
        for(int i = 0; i < orders.length; i++){
            String order = orders[i];
            int orderSize = order.length();
            for(int j = 0; j < course.length; j++){
                if(orderSize >= course[j]){
                    char[] arr = order.toCharArray();
                    Arrays.sort(arr);
                    order = new String(arr);
                    checkOrders(order, 0, course[j], new StringBuilder());
                }
            }
        }
        
        List<String> answer = new ArrayList<>();
        for(int i = 0; i < course.length; i++){
            int courseSize = course[i];
            int maxCount = 2;
            for(String key: map.keySet()){
                if(key.length() == courseSize){
                    if(map.get(key) >= 2 && map.get(key) >= maxCount){
                        maxCount = map.get(key);
                    }
                }
            }
            
            for(String key: map.keySet()){
                if(key.length() == courseSize){
                    if(map.get(key) == maxCount){
                        answer.add(key);
                    }
                }
            }
            
        }
        
        String[] answers = answer.toArray(new String[0]);
        Arrays.sort(answers);
        return answers;
    }
    
    void checkOrders(String order, int start, int orderSize, StringBuilder sb){
        if(sb.length() == orderSize){
            map.putIfAbsent(sb.toString(), 0);
            map.put(sb.toString(), map.get(sb.toString()) + 1);
            return;
        }
        
        for(int i = start; i < order.length(); i++){
            sb.append(order.charAt(i));
            checkOrders(order, i + 1, orderSize, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}