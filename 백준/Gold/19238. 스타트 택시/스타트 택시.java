import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    static char[][] graph;

    static class User{
        int startR;
        int startC;
        int endR;
        int endC;

        public User(int startR, int startC, int endR, int endC) {
            this.startR = startR;
            this.startC = startC;
            this.endR = endR;
            this.endC = endC;
        }
    }

    static class Pair{
        int r;
        int c;
        int count;

        public Pair(int r, int c){
            this.r = r;
            this.c = c;
        }

        public Pair(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");

        n = Integer.parseInt(size[0]);
        m = Integer.parseInt(size[1]);
        int fuel = Integer.parseInt(size[2]);

        graph = new char[n][n];
        for(int i = 0; i < n; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                graph[i][j] = line[j].charAt(0);
            }
        }
        size = br.readLine().split(" ");
        int startR = Integer.parseInt(size[0]) - 1;
        int startC = Integer.parseInt(size[1]) - 1;
        List<User> users = new ArrayList<>();

        for(int i = 0; i < m; i++){
            String[] line = br.readLine().split(" ");
            users.add(new User(Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1, Integer.parseInt(line[2]) - 1, Integer.parseInt(line[3]) - 1));
        }

        for(int i = 0; i < m; i++){
            int minDist = Integer.MAX_VALUE;
            User nextUser = null;
            for(User u: users){
                int dist = minDistance(startR, startC, u.startR, u.startC);
                if(dist == -1){
                    System.out.println(-1);
                    return;
                }
                if(dist < minDist){
                    minDist = dist;
                    nextUser = u;
                }
                else if(dist == minDist){
                    if(nextUser.startR > u.startR){
                        nextUser = u;
                    }
                    else if(nextUser.startR == u.startR){
                        if(nextUser.startC > u.startC){
                            nextUser = u;
                        }
                    }
                }
            }
            fuel -= minDist;
            if(fuel <= 0){
                System.out.println(-1);
                return;
            }
            startR = nextUser.startR;
            startC = nextUser.startC;
            int dist = minDistance(startR, startC, nextUser.endR, nextUser.endC);
            if(dist == -1){
                System.out.println(-1);
                return;
            }
            fuel -= dist;
            if(fuel < 0){
                System.out.println(-1);
                return;
            }
            startR = nextUser.endR;
            startC = nextUser.endC;
            fuel += dist * 2;
            users.remove(nextUser);
        }

        System.out.println(fuel);
    }


    static int minDistance(int startR, int startC, int endR, int endC){
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(new Pair(startR, startC, 0));
        visited[startR][startC] = true;

        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            if(cur.r == endR && cur.c == endC){
                return cur.count;
            }
            for(int i = 0; i < 4; i++){
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];
                if(0 <= nextR && nextR < n && 0 <= nextC && nextC < n){
                    if(!visited[nextR][nextC] && graph[nextR][nextC] == '0'){
                        queue.offer(new Pair(nextR, nextC, cur.count + 1));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }

        return -1;
    }
}
