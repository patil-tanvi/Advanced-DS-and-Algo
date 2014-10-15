import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TSPMergeSort3 {

	static int MergeSort(int[] A, int[] B, int p, int r, int h1, int h2)
			throws Exception {
		if (p < r) {
			int q = (p + r) / 2;

			h1 = MergeSort(A, B, p, q, h1, h2);
			h2 = MergeSort(A, B, q + 1, r, h1, h2);

			if (h1 != h2) {
//				if (h1 - 1 == h2) {
//					if (h1 % 2 != 0)
//						Merge(A, B, r, r, r);
//					else
//						Merge(B, A, r, r, r);
//					h2 = h2 + 1;
//				}
//				Merge(A ,A ,p ,q ,r );
//				return h2 + 1;
				
			}

			if (h1 % 2 != 0) {
				Merge(B, A, p, q, r);
			} else {
				Merge(A, B, p, q, r);
			}
			return (h1 - 1);
		} 
			return 10;
	}

	static void Merge(int[] src, int[] dest, int p, int q, int r) {
		int i = p; 
		int j = q + 1;
		for (int k = p; k <= r; k++) {
			if ((j > r) || ((i <= q) && (src[i] <= src[j])))
				dest[k] = src[i++];
			else
				dest[k] = src[j++];
		}
		return;
	}

	public static void main(String[] args) throws Exception {
//		int n = Integer.parseInt(args[0]);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] B = new int[n];

		int h1 = 10, h2 = 10;

		for (int i = 0; i < n; i++) {
			A[i] = n - i;
		}
		long start = System.currentTimeMillis();
		MergeSort(A, B, 0, n - 1, h1, h2);
		long last = System.currentTimeMillis();

		System.out.println("A ");
		for (int j = 0; j < A.length ; j++) {
			System.out.print(A[j] + "   ");
			}
		
	
		System.out.println("B");
		for (int j = 0; j < B.length ; j++) {
			System.out.print(B[j] + "   ");
			}
		
	
		boolean sorted = true;

		for (int j = 0; j < A.length - 1; j++) {
			if (A[j] > A[j + 1]) {
				sorted = false;
				break;
			}
		}

		if (!sorted) {
			sorted = true;

			for (int j = 0; j < B.length - 1; j++) {
				if (B[j] > B[j + 1]) {
					sorted = false;
					break;
				}
			}
		}

		if (sorted)
			System.out.println("Success! :-)");
		else
			System.out.println("Fail! :-(");
		System.out.println(last - start);
	}
}
