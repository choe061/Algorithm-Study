package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by choi on 2017. 8. 31..
 */
public class DSLR_9019 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            Dslr dslr = new Dslr(A, B);
            System.out.println(dslr.getCommands());
        }
        br.close();
    }

    static class Dslr {
        private int A, B;
        private boolean[] visited;

        public Dslr(int a, int b) {
            A = a;
            B = b;
            visited = new boolean[10001];
        }

        public String getCommands() {
            Queue<Register> registers = new LinkedList<>();
            registers.offer(new Register(A, ""));
            visited[A] = true;
            while (!registers.isEmpty()) {
                Register register = registers.poll();
                if (register.getNum() == B) {
                    return register.getCommand();
                }

                if (!visited[commandD(register.getNum())]) {
                    int num = commandD(register.getNum());
                    visited[num] = true;
                    StringBuilder commands = new StringBuilder(register.getCommand());
                    registers.offer(new Register(num, commands.append("D").toString()));
                }

                if (!visited[commandS(register.getNum())]) {
                    int num = commandS(register.getNum());
                    visited[num] = true;
                    StringBuilder commands = new StringBuilder(register.getCommand());
                    registers.offer(new Register(num, commands.append("S").toString()));
                }

                if (!visited[commandL(register.getNum())]) {
                    int num = commandL(register.getNum());
                    visited[num] = true;
                    StringBuilder commands = new StringBuilder(register.getCommand());
                    registers.offer(new Register(num, commands.append("L").toString()));
                }

                if (!visited[commandR(register.getNum())]) {
                    int num = commandR(register.getNum());
                    visited[num] = true;
                    StringBuilder commands = new StringBuilder(register.getCommand());
                    registers.offer(new Register(num, commands.append("R").toString()));
                }
            }
            return null;
        }

        private int commandD(int n) {
            return (n*2) % 10000;
        }

        private int commandS(int n) {
            return (n == 0) ? 9999 : n-1;
        }

        private int commandL(int n) {
            return (n % 1000) * 10 + n / 1000;
        }

        private int commandR(int n) {
            return (n % 10) * 1000 + (n / 10);
        }

        class Register {
            private int num;
            private String command;

            public Register(int num, String command) {
                this.num = num;
                this.command = command;
            }

            public int getNum() {
                return num;
            }

            public String getCommand() {
                return command;
            }
        }
    }
}
