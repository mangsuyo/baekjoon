import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] cards = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        String[] findCards = br.readLine().split(" ");

        List<String> result = new ArrayList<>();
        HashMap<String, Integer> hash = new HashMap<>();

        for (String s : cards) {
            hash.put(s, hash.getOrDefault(s, 0) + 1);
        }

        for (String s : findCards) {
            result.add(String.valueOf(hash.getOrDefault(s, 0)));
        }

        System.out.println(String.join(" ", result));



    }
}