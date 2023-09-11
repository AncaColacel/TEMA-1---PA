import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Semnale {

	static final int mod = 1000000007;
	static int sig_type, x, y;

	Semnale() {
	}

	static int type1() {
		int[][][] dp = new int[x + 1][y + 1][2];
		// cazul de baza. se considera ca am un singur semnal
		// (semnalul vid)
		dp[0][0][0] = 1;
		dp[0][0][1] = 0;
		// de asemenea am pus pe 0 fiecare semnal terminat in 1
		// cu 0 biti de 1
		for (int i = 0; i <= x; i++) {
			dp[i][0][1] = 0;
		}
		// si 0 fiecare semnal terminat in 0 cu 0 biti de 0
		for (int j = 1; j <= y; j++) {
			dp[0][j][0] = 0;
		}
		// parcurg numarul de biti de 0 si de 1
		for (int i = 0; i <= x; i++) {
			for (int j = 0; j <= y; j++) {
				if (i > 0) {
					// formez semnal terminat in 0
					// prin adaugarea unui bit de 0 atat
					// la un semnal terminat in 0 cat
					// si la un semnal terminat in 1
					dp[i][j][0] = (dp[i - 1][j][1] + dp[i - 1][j][0]) % mod;
				}
				if (j > 0) {
					// formez semnal terminat in 1
					// doar prin adaugarea unui bit de 1 la
					// un semnal terminat in 0
					dp[i][j][1] = dp[i][j - 1][0] % mod;
				}
			}
		}
		return (dp[x][y][0] + dp[x][y][1]) % mod;
	}


	static int type2() {
		// TODO Compute the number of type 2 signals.
		return 0;

	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("semnale.in"));

			sig_type = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();

			int ans;
			switch (sig_type) {
				case 1:
					ans = Semnale.type1();
					break;
				case 2:
					ans = Semnale.type2();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("semnale.out");
				fw.write(Integer.toString(ans));
				fw.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
