package week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by choi on 2017. 7. 15..
 */
public class King_1063 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] start = br.readLine().split(" ");
        int n = Integer.parseInt(start[2]);
        String[] move = new String[n];
        for (int i=0; i<n; i++) {
            move[i] = br.readLine();
        }

        moveKing(move, start[0], start[1]);
        br.close();
    }

    private static void moveKing(String[] move, String king, String stone) {
        int king_x = getX(king.charAt(0));
        int king_y = getY(king.charAt(1));
        int stone_x = getX(stone.charAt(0));
        int stone_y = getY(stone.charAt(1));

        for (int i=0; i<move.length; i++) {
            int[] direction = direct(move[i]);
            king_x += direction[0];
            king_y += direction[1];
            if (king_x <= 0 || king_y <= 0 || king_x >= 9 || king_y >= 9) {
                king_x -= direction[0];
                king_y -= direction[1];
            }
            if (king_x == stone_x && king_y == stone_y) {
                stone_x += direction[0];
                stone_y += direction[1];
                if (stone_x <= 0 || stone_y <= 0 || stone_x >= 9 || stone_y >= 9) {
                    king_x -= direction[0];
                    king_y -= direction[1];
                    stone_x -= direction[0];
                    stone_y -= direction[1];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getChX(king_x)).append(getChY(king_y)).append('\n').append(getChX(stone_x)).append(getChY(stone_y));
        System.out.println(sb.toString());
    }

    private static int getX(char c) {
        return c - 64;
    }

    private static int getY(char c) {
        return 8 - (c - 49);
    }

    private static char getChX(int x) {
        return (char) (x + 64);
    }

    private static int getChY(int y) {
        return 9 - y;
    }

    private static int[] direct(String move) {
        if (move.equals("R")) {
            return new int[]{1, 0};
        } else if (move.equals("L")) {
            return new int[]{-1, 0};
        } else if (move.equals("B")) {
            return new int[]{0, 1};
        } else if (move.equals("T")) {
            return new int[]{0, -1};
        } else if (move.equals("RT")) {
            return new int[]{1, -1};
        } else if (move.equals("LT")) {
            return new int[]{-1, -1};
        } else if (move.equals("RB")) {
            return new int[]{1, 1};
        } else if (move.equals("LB")) {
            return new int[]{-1, 1};
        }
        return new int[]{0, 0};
    }
}
