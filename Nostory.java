import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Nostory {
	public static void main(final String[] args) throws IOException {
		var scanner = new MyScanner(new FileReader("nostory.in"));
		var task = scanner.nextInt();
		var n = scanner.nextInt();
		var moves = task == 2 ? scanner.nextInt() : 0;

		var a = new int[n];
		for (var i = 0; i < n; i += 1) {
			a[i] = scanner.nextInt();
		}

		var b = new int[n];
		for (var i = 0; i < n; i += 1) {
			b[i] = scanner.nextInt();
		}

		try (var printer = new PrintStream("nostory.out")) {
			if (task == 1) {
				printer.println(solveTask1(a, b));
			} else {
				printer.println(solveTask2(a, b, moves));
			}
		}
	}

	private static long solveTask1(int[] a, int[] b) {
		long suma_max = 0L;
		// sortare crescatoare a
		Arrays.sort(a);
		// conversie a vectorului cu elemente de tipul primitiv int in elemente de tipul Integer
		Integer[] copie = Arrays.stream(b).boxed().toArray(Integer[]::new);
		// sortez in mod descrescator b
		Arrays.sort(copie, Collections.reverseOrder());
		for (int i = 0; i < a.length; i++) {
			// compar elementul din a cu elementul corespunzator
			// din b dupa sortare
			if (a[i] > copie[i]) {
				// adaug maximul la suma
				suma_max = suma_max + a[i];
			} else {
				suma_max = suma_max + copie[i];
			}
		}
		return suma_max;
	}

	private static long solveTask2(int[] a, int[] b, int moves) {
		long sum_max = 0L;
		ArrayList<Integer> v_max = new ArrayList<>();
		ArrayList<Integer> v_min = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				sum_max += a[i];
				// in v_max pun elementele maxime din fiecare comparatie
				v_max.add(a[i]);
				// in v_min pun elementele minime din fiecare comparatie
				v_min.add(b[i]);
			} else {
				sum_max += b[i];
				v_max.add(b[i]);
				v_min.add(a[i]);
			}
		}
		// sortez vectorul de maxime crescator
		Collections.sort(v_max);
		// sortez vectorul de minime descrescator
		Collections.sort(v_min, Collections.reverseOrder());

		// verificăm dacă pot face schimburi
		for (int i = 0; i < a.length && moves > 0; i++) {
			if (v_max.get(i) < v_min.get(i)) {
				// actualizez suma
				sum_max = sum_max - v_max.get(i) + v_min.get(i);
				moves--;
			}
		}
		return sum_max;
	}


	/**
	 * A class for buffering read operations, inspired from here:
	 * https://pastebin.com/XGUjEyMN.
	 */
	private static class MyScanner {
		private BufferedReader br;
		private StringTokenizer st;

		public MyScanner(Reader reader) {
			br = new BufferedReader(reader);
		}

		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
