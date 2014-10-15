import java.io.*;
import java.util.concurrent.TimeUnit;

public class TSPMergeSort1 {

	static void MergeSort(int[] A, int p, int r) {
		if (p < r) {
			if (r - p > 11) {
				int q = (p + r) / 2;
				MergeSort(A, p, q);
				MergeSort(A, q + 1, r);
				Merge(A, p, q, r);
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

	static void Merge(int[] A, int p, int q, int r) {
		int ls = q - p + 1;
		int rs = r - q;
		int[] L = new int[ls];
		int[] R = new int[rs];
		for (int i = p; i <= q; i++)
			L[i - p] = A[i];
		for (int i = q + 1; i <= r; i++)
			R[i - (q + 1)] = A[i];
		int i = 0;
		int j = 0;
		for (int k = p; k <= r; k++) {
			if ((j >= rs) || ((i < ls) && (L[i] <= R[j])))
				A[k] = L[i++];
			else
				A[k] = R[j++];
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(args[0]);
		int[] A = new int[n];
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