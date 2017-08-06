package week_10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 3..
 */
public class BOJ_2564 {
    private static int X, Y;
    private static ArrayList<Location> stores = new ArrayList<>();
    private static Location dg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            stores.add(getLocation(direction, distance));
        }
        st = new StringTokenizer(br.readLine());
        dg = getLocation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        System.out.println(getTotal());
        br.close();
    }

    private static Location getLocation(int direction, int distance) {
        if (direction == 1) {
            return new Location(distance, 0);
        } else if (direction == 2) {
            return new Location(distance, Y);
        } else if (direction == 3) {
            return new Location(0, distance);
        } else {
            return new Location(X, distance);
        }
    }

    private static int getTotal() {
        int total = 0;
        for (Location store : stores) {
            System.out.println(getMinimumDistance(store));
            total += getMinimumDistance(store);
        }
        return total;
    }

    private static int getMinimumDistance(Location store) {
        if (Math.abs(dg.getX() - store.getX()) == X) {          //반대편인 경우 1 (동 서)
            return X + (Math.min(dg.getY() + store.getY(), Y - dg.getY() + Y - store.getY()));
        } else if (Math.abs(dg.getY() - store.getY()) == Y) {   //반대편인 경우 2 (남 북)
            return Y + (Math.min(dg.getX() + store.getX(), X - dg.getX() + X - store.getX()));
        } else {                                                //그 외
            return Math.abs(dg.getX() - store.getX()) + Math.abs(dg.getY() - store.getY());
        }
    }

    static class Location {
        private int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
