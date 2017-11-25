package data_structures;

import java.util.*;

public class SegmentTreeMin
{
	private int[] st;
	private int[] arr;
	private int n;

	public SegmentTreeMin(int[] a)
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

	private void build(int p,int L,int R)
	{
		if(L==R)
		{
			st[p]=L;
			return;
		}
		build(2*p,L,(L+R)/2);
		build(2*p+1,(L+R)/2+1,R);
		int p1=st[2*p],p2=st[2*p+1];
		st[p]=(arr[p1]<=arr[p2])?p1:p2;
	}

	private int query(int p,int L,int R,int i,int j)
	{
		if(i>R||j<L) return -1;
		if(L>=i&&R<=j) return st[p];
		int p1=query(2*p,L,(L+R)/2,i,j);
		int p2=query(2*p+1,(L+R)/2+1,R,i,j);
		if(p1==-1) return p2;
		if(p2==-1) return p1;
		return (arr[p1]<arr[p2])?p1:p2;
	}

	private void update(int p,int L,int R,int i)
	{
		if(L==R) return;
		if(i>=L&&i<=R)
		{
			update(2*p,L,(L+R)/2,i);
			update(2*p+1,(L+R)/2+1,R,i);
			st[p]=(arr[st[2*p]]<=arr[st[2*p+1]])?st[2*p]:st[2*p+1];
		}
		return;
	}
}