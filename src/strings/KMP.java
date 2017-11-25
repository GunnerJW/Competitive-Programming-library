package strings;

public class KMP
{
	static char[] T;
	static char[] P;
	static int[] b;// length of b is length of P + 1
	static int n;// length of T
	static int m;// length of P

	static void kmpPreprocess()
	{
		int i=0,j=-1;
		b[0]=-1;
		while(i<m)
		{
			while(j>=0&&P[i]!=P[j])
				j=b[j];
			i++;
			j++;
			b[i]=j;
		}
	}

	static void kmpSearch()
	{
		int i=0,j=0;
		while(i<n)
		{
			while(j>=0&&T[i]!=P[j])
				j=b[j];
			i++;
			j++;
			if(j==m)
			{
				System.out.println("P is found at index "+(i-j)+" in T");
				j=b[j];
			}
		}
	}
}
