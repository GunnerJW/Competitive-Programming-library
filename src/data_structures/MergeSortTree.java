package data_structures;

import java.util.*;

public class MergeSortTree
{
	public int[] arr;
	public ArrayList<Integer>[] tree;
	public int n;

	private int binarySearch(int p,int key)// for query method
	{
		int n=tree[p].size();
		int low=0;
		int high=n-1;
		int ans=-1;
		while(low<=high)
		{
			int mid=(low+high)>>1;
			if(tree[p].get(mid)>key)
			{
				high=mid-1;
			}
			else
			{
				low=mid+1;
				ans=mid;
			}
		}
		return ans==-1?0:ans+1;
	}

	private int first(int p,int low,int high,int x)
	{
		if(high>=low)
		{
			int mid=low+(high-low)/2;
			if((mid==0||x>tree[p].get(mid-1))&&tree[p].get(mid)==x) return mid;
			else if(x>tree[p].get(mid)) return first(p,(mid+1),high,x);
			else return first(p,low,(mid-1),x);
		}
		return -1;
	}

	private int last(int p,int low,int high,int x,int n)
	{
		// System.out.println("("+p+","+low+","+high+","+x+","+n+") called!");
		if(high>=low)
		{
			int mid=low+(high-low)/2;
			if((mid==n-1||x<tree[p].get(mid+1))&&tree[p].get(mid)==x) return mid;
			else if(x<tree[p].get(mid)) return last(p,low,(mid-1),x,n);
			else return last(p,(mid+1),high,x,n);
		}
		return -1;
	}

	private void build(int p,int l,int r)
	{
		if(l==r)
		{
			tree[p].add(arr[l]);
			return;
		}
		int mid=(l+r)/2;
		build(2*p,l,mid);
		build(2*p+1,mid+1,r);
		tree[p]=merge(tree[2*p],tree[2*p+1]);
	}

	private ArrayList<Integer> merge(ArrayList<Integer> a,ArrayList<Integer> b)
	{
		ArrayList<Integer> res=new ArrayList<Integer>(a.size()+b.size());
		int ia=0;
		int ib=0;
		int sa=a.size();
		int sb=b.size();
		while(ia<sa||ib<sb)
		{
			if(ia>=sa) res.add(b.get(ib++));
			else if(ib>=sb) res.add(a.get(ia++));
			else
			{
				if(a.get(ia)<=b.get(ib)) res.add(a.get(ia++));
				else res.add(b.get(ib++));
			}
		}
		return res;
	}

	private int query(int p,int L,int R,int i,int j,int k)
	{
		if(i>R||j<L) return 0;
		if(i<=L&&j>=R) return binarySearch(p,k);
		int mid=(L+R)/2;
		return query(2*p,L,mid,i,j,k)+query(2*p+1,mid+1,R,i,j,k);
	}

	private int occurrences(int p,int L,int R,int i,int j,int k)
	{
		if(L>j||R<i) return 0;
		if(i<=L&&j>=R)
		{
			int first=first(p,0,tree[p].size()-1,k);
			int last=last(p,0,tree[p].size()-1,k,tree[p].size());
			if(first!=-1&&last!=-1) return last-first+1;
			else return 0;
		}
		int mid=(L+R)>>1;
		return occurrences(2*p,L,mid,i,j,k)+occurrences(2*p+1,mid+1,R,i,j,k);
	}

	public MergeSortTree(int[] a)
	{
		n=a.length;
		arr=new int[n];
		arr=Arrays.copyOf(a,n);
		tree=new ArrayList[4*n];
		for(int i=0;i<4*n;i++)
			tree[i]=new ArrayList<Integer>();// anticipate size
		build(1,0,n-1);
	}

	public int query(int i,int j,int k)
	{
		return query(1,0,n-1,i,j,k);
	}

	public int occurrences(int i,int j,int k)
	{
		return occurrences(1,0,n-1,i,j,k);
	}
}
