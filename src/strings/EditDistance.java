package strings;

public class EditDistance
{
	public static int maxScore(String a,String b)//Implementation of the Needleman-Wunsch algorithm, O(n*m)
	{
		char[] A=a.toCharArray();
		char[] B=b.toCharArray();
		int n=A.length;
		int m=B.length;
		int[][] memo=new int[n+1][m+1];
		for(int i=1;i<=n;i++)
			memo[i][0]=-1*i;
		for(int j=1;j<=m;j++)
			memo[0][j]=-1*j;
		for(int i=1;i<=n;i++)
			for(int j=1;j<=m;j++)
			{
				memo[i][j]=memo[i-1][j-1]+(A[i-1]==B[j-1]?2:-1);
				memo[i][j]=Math.max(memo[i][j],memo[i-1][j]-1);
				memo[i][j]=Math.max(memo[i][j],memo[i][j-1]-1);
			}
		return memo[n][m];
	}
}
