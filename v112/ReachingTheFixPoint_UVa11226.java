package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReachingTheFixPoint_UVa11226 {

	static ArrayList<Integer> primes;

	static void sieve(int N)
	{
		primes = new ArrayList<Integer>();
		boolean[] isComposite = new boolean[N];
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				for(int j = i * i; j < N; j += i)
					isComposite[j] = true;
			}
	}

	static int sumPF(int N)
	{
		int ans = 0, idx = 0, p = primes.get(0);

		while(p * p <= N)
		{
			while(N % p == 0) { N /= p; ans += p; }
			p = primes.get(++idx);
		}

		if(N != 1)
			ans += N;
		return ans;
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		sieve(1000);
		int[] lsopf = new int[500001];
		for(int i = 2; i <= 500000; ++i)
			lsopf[i] = lsopf[sumPF(i)] + 1;
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int l = sc.nextInt(), r = sc.nextInt(), max = 0;
			if(l > r) { l ^= r; r ^= l; l ^= r;	}
			
			for(int i = l; i <= r; ++i)
				max = Math.max(max, lsopf[i]);
			out.printf("Case #%d:\n%d\n", t, max);
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}