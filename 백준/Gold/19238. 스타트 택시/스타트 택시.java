import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int fuel;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};


    static class Pair{
        int row;
        int col;
        int count;
        int fuel;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Pair(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        public Pair(int row, int col, int count, int fuel) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.fuel = fuel;
        }

        static boolean inRange(int r, int c){
            return 0 <= r && r < n && 0 <= c && c < n;
        }
    }

    static class Guest{
        Pair start;
        Pair end;

        public Guest(Pair start, Pair end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            Guest guest = (Guest) object;
            return Objects.equals(start, guest.start) && Objects.equals(end, guest.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        n = Integer.parseInt(size[0]);
        m = Integer.parseInt(size[1]);
        fuel = Integer.parseInt(size[2]);

        int[][] graph = new int[n][n];

        for(int i = 0; i < n; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                graph[i][j] = Integer.parseInt(line[j]);
            }
        }

        String[] taxiInfo = br.readLine().split(" ");
        Pair taxi = new Pair(Integer.parseInt(taxiInfo[0]) - 1, Integer.parseInt(taxiInfo[1]) - 1);

        List<Guest> guests = new ArrayList<>();
        for(int i = 0; i < m; i++){
            String[] line = br.readLine().split(" ");
            Pair start = new Pair(Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1);
            Pair end = new Pair(Integer.parseInt(line[2]) - 1, Integer.parseInt(line[3]) - 1);
            guests.add(new Guest(start, end));
        }


        for(int i = 0; i < m; i++) {
            Guest g = findGuest(graph, taxi, guests);
            if(g == null){
                System.out.println(-1);
                return;
            }
            int a = move(graph, taxi, g.start);
            if(a == -1){
                System.out.println(-1);
                return;
            }
            fuel = fuel - a;
            taxi = g.start;
            int b = move(graph, taxi, g.end);
            if(b == -1){
                System.out.println(-1);
                return;
            }
            fuel = fuel - b;
            taxi = g.end;
            fuel = fuel + b * 2;
            guests.remove(g);
        }

        System.out.println(fuel);
    }

    public static int move(int[][] graph, Pair taxi, Pair goal){
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(new Pair(taxi.row, taxi.col, 0, fuel));
        visited[taxi.row][taxi.col] = true;

        if(taxi.row == goal.row && taxi.col == goal.col) return 0;

        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextR = cur.row + dr[i];
                int nextC = cur.col + dc[i];
                if(Pair.inRange(nextR, nextC)){
                    if(!visited[nextR][nextC] && graph[nextR][nextC] != 1){
                        if(cur.fuel >= 1 && nextR == goal.row && nextC == goal.col) return cur.count + 1;
                        queue.offer(new Pair(nextR, nextC, cur.count + 1, cur.fuel - 1));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static Guest findGuest(int[][] graph, Pair taxi, List<Guest> guests){
        Queue<Pair> queue = new ArrayDeque<>();
        List<Guest> candidates = new ArrayList<>();

        boolean[][] visited = new boolean[n][n];
        int minDist = Integer.MAX_VALUE;
        queue.offer(new Pair(taxi.row, taxi.col, 0, fuel));
        visited[taxi.row][taxi.col] = true;


        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            if(cur.count > minDist) break;

            for(int i = 0; i < guests.size(); i++){
                if(cur.fuel >= 0 && guests.get(i).start.row == cur.row && guests.get(i).start.col == cur.col){
                    minDist = cur.count;
                    candidates.add(guests.get(i));
                }
            }
            for(int i = 0; i < 4; i++){
                int nextR = cur.row + dr[i];
                int nextC = cur.col + dc[i];
                if(Pair.inRange(nextR, nextC)){
                    if(!visited[nextR][nextC] && graph[nextR][nextC] != 1){
                        queue.offer(new Pair(nextR, nextC, cur.count + 1, cur.fuel - 1));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }

        if(candidates.isEmpty()) return null;

        Collections.sort(candidates, (a, b) -> {
            if(a.start.row == b.start.row){
                return Integer.compare(a.start.col, b.start.col);
            }
            return Integer.compare(a.start.row, b.start.row);
        });

        return candidates.get(0);
    }
}
