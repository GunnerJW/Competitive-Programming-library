package data_structures;

public class FenwickTree
{
	public int[] arr;

	public FenwickTree(int n)
	{
		arr=new int[n+1];
	}

	private static int LSOne(int i)
	{
		return (i&(-i));
	}

	public int query(int i)// returns sum of [1,i]
	{
		int sum=0;
		for(;i>0;i-=LSOne(i))
			sum+=arr[i];
		return sum;
	}

	public int query(int a,int b)
	{
		return this.query(b)-((a==1)?0:this.query(a-1));
	}

	public void adjust(int k,int offset)
	{
		for(;k<arr.length;k+=LSOne(k))
			arr[k]+=offset;
	}
}
