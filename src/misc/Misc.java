package misc;

import java.util.*;

public class Misc
{
	public static int LIS1(int[] a)// O(n2)
	{
		int n=a.length;
		int[] LIS=new int[n];
		Arrays.fill(LIS,1);
		for(int i=0;i<n;i++)
			for(int j=0;j<i;j++)
				if(a[j]<a[i]) LIS[i]=Math.max(LIS[i],1+LIS[j]);
		int res=1;
		for(int i=0;i<n;i++)
			res=Math.max(res,LIS[i]);
		return res;
	}

	private static int binarySearch3(ArrayList<Integer> LIS,int[] a,int key)
	{
		int n=LIS.size();
		int low=0;
		int high=n-1;
		int mid;
		while(low<high)
		{
			mid=(low+high)/2;
			if(a[LIS.get(mid)]>key) high=mid;
			else if(a[LIS.get(mid)]<key) low=mid+1;
			else return mid;
		}
		return low;
	}

	private static int binarySearch2(ArrayList<Integer> LIS,int key)
	{
		int n=LIS.size();
		int low=0;
		int high=n-1;
		int mid;
		while(low<high)
		{
			mid=(low+high)/2;
			if(LIS.get(mid)>key) high=mid;
			else if(LIS.get(mid)<key) low=mid+1;
			else return mid;
		}
		return low;
	}

	public static int LIS2(int[] a)// O(nlogn)
	{
		int n=a.length;
		ArrayList<Integer> LIS=new ArrayList<Integer>(n);
		int idx=0;
		LIS.add(a[0]);
		for(int i=1;i<n;i++)
		{
			if(a[i]>LIS.get(idx))
			{
				LIS.add(a[i]);
				idx++;
			}
			else
			{
				int insertionPt=binarySearch2(LIS,a[i]);
				LIS.set(insertionPt,a[i]);
			}
		}
		return idx+1;
	}

	public static int[] LIS3(int[] a)// O(nlogn)
	{
		int n=a.length;
		ArrayList<Integer> LIS=new ArrayList<Integer>(n);
		int[] parent=new int[n];
		int idx=0;
		LIS.add(0);
		parent[0]=-1;
		for(int i=1;i<n;i++)
		{
			if(a[i]>a[LIS.get(idx)])
			{
				LIS.add(i);
				parent[i]=LIS.get(idx);
				idx++;
			}
			else
			{
				int insertionPt=binarySearch3(LIS,a,a[i]);
				if(insertionPt==0) parent[i]=-1;
				else parent[i]=LIS.get(insertionPt-1);
				LIS.set(insertionPt,i);
			}
		}
		int[] res=new int[idx+1];
		int curr=idx;
		int x=LIS.get(idx);
		while(x!=-1)
		{
			res[curr--]=a[x];
			x=parent[x];
		}
		return res;
	}

	public static int MaxRangeSum(int[] a)// O(n)
	{
		int n=a.length;
		int sum=0;
		int res=0;
		for(int i=0;i<n;i++)
		{
			sum+=a[i];
			res=Math.max(res,sum);
			if(sum<0) sum=0;
		}
		return res;
	}
	public static long[][] pascalTriangle(int n,int mod)
	{
		long[][] res=new long[n+1][n+1];
		for(int i=0;i<n+1;i++)
		{
			res[i][0]=1;
			res[i][i]=1;
		}
		for(int i=2;i<n+1;i++)
			for(int j=1;j<n+1;j++)
				res[i][j]=(res[i-1][j]%mod+res[i-1][j-1]%mod)%mod;
		return res;
	}
}
