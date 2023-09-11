import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Feribot {


	public static void main(String[] args) {
		Feribot obiect = new Feribot();
		try {
			MyScanner sc = new MyScanner(new FileReader("feribot.in"));
			int nr_elem = sc.nextInt();
			int K = sc.nextInt();
			ArrayList<Long> masini = new ArrayList<>();
			for (int i = 0; i < nr_elem; i++) {
				masini.add(sc.nextLong());
			}
			// limita inferioara a intervalului
			// este maximul din vector
			Long L = Collections.max(masini);
			Long R = 0L;
			// limita superioara este suma
			// elemenentelor din vector
			R = masini.stream().mapToLong(Long::longValue).sum();
			Long ans = obiect.bnSearch(L, R, masini, K);
			try {
				FileWriter fw = new FileWriter("feribot.out");
				fw.write(Long.toString(ans));
				fw.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean verifica(Long C, ArrayList<Long> masini, int K) {
		int nr_feribot = 1;
		Long cost_curent = 0L;
		for (Long masina : masini) {
			// daca greutatea masinii impreuna cu costul curent
			// nu depaseste C-ul adica mijlocul de interval
			// se adauga greutatea la costul curent
			if (masina + cost_curent <= C) {
				cost_curent = cost_curent + masina;
			} else {
				// in caz contrar se ia un nou feribot
				// a carui cost se initializeaza cu masina curenta
				nr_feribot++;
				cost_curent = masina;
			}
		}
		// se compara numarul de feriboturi folosite
		// cu numarul total de feriboturi existente
		if (nr_feribot <= K) {
			return true;
		} else {
			return false;
		}
	}

	public Long bnSearch(Long L, Long R, ArrayList<Long> masini, int K) {
		Long mijl = (L + R) / 2;
		while (L <= R) {
			// aplic functia verifica pentru fiecare mijloc
			// din fiecare interval
			if (verifica(mijl, masini, K)) {
				// daca aceasta intoarce true
				// caut un numar mai mic in partea stanga a intervalului
				return bnSearch(L, mijl - 1, masini, K);
			}
			// caut in dreapta altfel
			return bnSearch(mijl + 1, R, masini, K);
		}
		return L;
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

