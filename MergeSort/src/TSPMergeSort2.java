import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TSPMergeSort2 {

	private static int[] B;

	static void MergeSort(int[] A, int p, int r) {
		if (p < r) {
			if (r - p > 11) {
				int q = (p + r) / 2;
				MergeSort(A, p, q);
				MergeSort(A, q + 1, r);
				Merge(A, B, p, q, r);
			} else { // Insertion sort
				for (int i = p, j = i; i < r; j = ++i) {
					int ai = A[i + 1];
					while (ai < A[j]) {
						A[j + 1] = A[j];
						if (j-- == p) {
							break;
						}
					}
					A[j + 1] = ai;
				}
			}
		}
	}

	static void Merge(int[] A, int[] B, int p, int q, int r) {

		for (int i = p; i <= q; i++)
			B[i] = A[i];
		for (int i = q + 1; i <= r; i++)
			B[i] = A[i];
		int i = p;
		int j = q + 1;
		for (int k = p; k <= r; k++) {
			if ((j > r) || ((i <= q) && (B[i] <= B[j])))
				A[k] = B[i++];
			else
				A[k] = B[j++];
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		 int n = Integer.parseInt(args[0]);

		int[] A = new int[n];
		B = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = n - i;
		}
		long start = System.currentTimeMillis();
		MergeSort(A, 0, n - 1);
		long last = System.currentTimeMillis();

		for (int j = 0; j < A.length - 1; j++) {
			if (A[j] > A[j + 1]) {
				System.out.println("Sorting failed :-(");
				return;
			}
		}
		System.out.println("Success!");
		System.out.println(last - start);
	}
}
