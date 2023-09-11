import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class Sushi {

	static int n, m, x;
	static int[] prices;
	static int[][] grades;

	Sushi() {
	}

	static int task1() {
		// TODO solve task 1
		// suma de bani disponibila
		int bani = n * x;
		int[][] dp = new int[m + 1][bani + 1];
		// cazul de baza (nu cumpar niciun tip de sushi)
		for (int j = 0; j <= bani; ++j) {
			dp[0][j] = 0;
		}
		// cazul general
		for (int i = 1; i <= m; ++i) {
			for (int j = 0; j <= bani; ++j) {
				// nu cumpar acel tip de sushi
				dp[i][j] = dp[i - 1][j];
				// cumpar acel tip de sushi
				int suma_note = 0;
				for (int k = 0; k <= n - 1; ++k) {
					suma_note = suma_note + grades[k][i - 1];
				}
				// verific sa am bani pt el
				if (j - prices[i - 1] >= 0) {
					int sol_aux = dp[i - 1][j - prices[i - 1]] + suma_note;
					// verific daca acel tip de sushi imi aduce
					// grade mai bun fata de ce am pana acum
					dp[i][j] = Math.max(dp[i][j], sol_aux);
				}
			}

		}
		return dp[m][bani];
	}


	static int task2() {
		// TODO solve task 2
		int bani = n * x;
		int[][] dp = new int[m + 1][bani + 1];
		// cazul de baza (nu cumpar niciun tip de sushi)
		for (int j = 0; j <= bani; ++j) {
			dp[0][j] = 0;
		}
		// cazul general
		for (int i = 1; i <= m; ++i) {
			for (int j = 0; j <= bani; ++j) {
				// nu cumpar acel tip de sushi
				dp[i][j] = dp[i - 1][j];
				// cumpar acel tip de sushi o singura data
				int suma_note_1 = 0;
				for (int k = 0; k <= n - 1; ++k) {
					suma_note_1 = suma_note_1 + grades[k][i - 1];
				}
				// verific sa am bani pt el
				if (j - prices[i - 1] >= 0) {
					int sol_aux_1 = dp[i - 1][j - prices[i - 1]] + suma_note_1;
					dp[i][j] = Math.max(dp[i][j], sol_aux_1);
				}
				// incerc sa mai cumpar acel tip de sushi inca o data
				int suma_note_2 = 0;
				for (int k = 0; k <= n - 1; ++k) {
					suma_note_2 = suma_note_2 + 2 * grades[k][i - 1];
				}
				// verific sa vad daca am bani pentru ambele platouri de acelasi tip
				if (j - 2 * prices[i - 1] >= 0) {
					int sol_aux_2 = dp[i - 1][j - 2 * prices[i - 1]] + suma_note_2;
					dp[i][j] = Math.max(dp[i][j], sol_aux_2);
				}
			}
		}

		return dp[m][bani];
	}

	static int task3() {
		// TODO solve task 3
		int bani = n * x;
		int[][][] dp = new int[m + 1][bani + 1][n + 1];
		// cazul de baza (nu cumpar niciun tip de sushi)
		for (int j = 0; j <= bani; ++j) {
			dp[0][j][0] = 0;
		}
		// cazul general
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= bani; ++j) {
				for (int k = 1; k <= n; ++k) {
					// nu cumpar acel tip de sushi
					dp[i][j][k] = dp[i - 1][j][k];
					// cumpar acel tip de sushi o singura data
					int suma_note_1 = 0;
					for (int z = 0; z <= n - 1; ++z) {
						suma_note_1 = suma_note_1 + grades[z][i - 1];
					}
					// verific sa am bani pt el si
					// k sa fie pozitiv
					if (j - prices[i - 1] >= 0 && k > 0) {
						int sol_aux_1 = dp[i - 1][j - prices[i - 1]][k - 1] + suma_note_1;
						dp[i][j][k] = Math.max(dp[i][j][k], sol_aux_1);
					}
					// incerc sa mai cumpar acel tip de sushi inca o data
					int suma_note_2 = 0;
					for (int w = 0; w <= n - 1; ++w) {
						suma_note_2 = suma_note_2 + 2 * grades[w][i - 1];
					}
					// verific sa vad daca am bani pentru ambele platouri de acelasi tip
					// si k sa fie mai mare decat 1
					if (j - 2 * prices[i - 1] >= 0 && k > 1) {
						int sol_aux_2 = dp[i - 1][j - 2 * prices[i - 1]][k - 2] + suma_note_2;
						dp[i][j][k] = Math.max(dp[i][j][k], sol_aux_2);
					}
				}
			}
		}

		return dp[m][bani][n];
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("sushi.in"));

			final int task = sc.nextInt(); // task number

			n = sc.nextInt(); // number of friends
			m = sc.nextInt(); // number of sushi types
			x = sc.nextInt(); // how much each of you is willing to spend

			prices = new int[m]; // prices of each sushi type
			grades = new int[n][m]; // the grades you and your friends gave to each sushi type

			// price of each sushi
			for (int i = 0; i < m; ++i) {
				prices[i] = sc.nextInt();
			}

			// each friends rankings of sushi types
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					grades[i][j] = sc.nextInt();
				}
			}

			int ans;
			switch (task) {
				case 1:
					ans = Sushi.task1();
					break;
				case 2:
					ans = Sushi.task2();
					break;
				case 3:
					ans = Sushi.task3();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("sushi.out");
				fw.write(Integer.toString(ans) + '\n');
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
