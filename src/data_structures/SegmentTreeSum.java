package data_structures;

import java.util.*;

public class SegmentTreeSum
{
	public int[] st;
	private int[] arr;
	private int n;

	private void build(int p,int L,int R)
	{
		if(L==R)
		{
			st[p]=arr[L];
			return;
		}
		build(2*p,L,(L+R)/2);
		build(2*p+1,(L+R)/2+1,R);
		st[p]=st[2*p]+st[2*p+1];
	}

	private int query(int p,int L,int R,int i,int j)
	{
		if(i>R||j<L) return 0;
		if(L>=i&&R<=j) return st[p];
		int p1=query(2*p,L,(L+R)/2,i,j);
		int p2=query(2*p+1,(L+R)/2+1,R,i,j);
		return p1+p2;
	}

	private void update(int p,int L,int R,int i)
	{
		if(L==R)
		{
			if(L==i) st[p]=arr[i];
			return;
		}
		if(i>=L&&i<=R)
		{
			update(2*p,L,(L+R)/2,i);
			update(2*p+1,(L+R)/2+1,R,i);
			st[p]=st[2*p]+st[2*p+1];
		}
		return;
	}

	public SegmentTreeSum(int[] a)
	{
		this.n=a.length;
		this.arr=Arrays.copyOf(a,n);
		this.st=new int[4*n];
		build(1,0,n-1);
	}

	public int query(int i,int j)
	{
		return query(1,0,n-1,i,j);
	}

	public void update(int i,int value)
	{
		arr[i]=value;
		update(1,0,n-1,i);
	}
}
