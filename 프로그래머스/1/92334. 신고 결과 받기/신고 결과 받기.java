import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> hash = new HashMap<>();
        Map<String, Integer> answer= new HashMap<>();
        for(String str: id_list){
            hash.put(str, new HashSet<>());
            answer.put(str, 0);
        }
        
        for(String str: report){
            String[] strs = str.split(" ");
            String reporter = strs[0];
            String reported = strs[1];
            hash.get(reported).add(reporter);
        }
        
        for(String key: hash.keySet()){
            if(hash.get(key).size() >= k){
                for(String reporter: hash.get(key)){
                    answer.put(reporter, answer.get(reporter) + 1);
                }
            }
        }
        
        int[] result = new int[id_list.length];
        int index = 0;
        for(String id: id_list){
            result[index++] = answer.get(id);
        }
        
        return result;
    }
}