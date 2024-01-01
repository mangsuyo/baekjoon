import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        Set<String> subStrings = new HashSet<>();

        for (int i = 0; i < S.length(); i++) {
            char startCh = S.charAt(i);
            for (int j = i + 1; j <= S.length(); j++) {
                String addResult = S.substring(i, j);
                subStrings.add(addResult);
            }
        }
        System.out.println(subStrings.size());
    }
}