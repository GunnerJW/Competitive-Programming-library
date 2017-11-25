package sorting;

public class Sort
{
	public static void countSort(int[] a,int max)// O(n+max)
	{
		int[] aux=new int[max+1];
		int n=a.length;
		for(int i=0;i<n;i++)// O(n)
			aux[a[i]]++;
		int[] res=new int[n];
		int i=0;
		for(int j=0;j<=max;j++)// O(max)
			for(int k=0;k<aux[j];k++)
				res[i++]=j;
		for(int j=0;j<n;j++)
			a[j]=res[j];
		return;
	}

	private static void countSortR(int[] a,int digit)
	{
		int n=a.length;
		int[] freq=new int[10];
		int[] res=new int[n];
		for(int i=0;i<n;i++)
			freq[(a[i]/digit)%10]++;
		for(int i=1;i<10;i++)
			freq[i]+=freq[i-1];
		for(int i=n-1;i>=0;i--)
		{
			res[freq[(a[i]/digit)%10]-1]=a[i];
			freq[(a[i]/digit)%10]--;
		}
		for(int i=0;i<n;i++)
			a[i]=res[i];
	}

	public static void radixSort(int[] a,int max)// O((n+b)*logB(max))
	{
		int digit=1;
		while(max>0)
		{
			countSortR(a,digit);
			digit*=10;
			max/=10;
		}
	}

	public static void merge(int[] a,int start,int mid,int end)
	{
		int p=start;
		int q=mid+1;
		int[] arr=new int[end-start+1];
		int k=0;
		for(int i=start;i<=end;i++)
		{
			if(p>mid)
			{
				arr[k++]=a[q++];
			}
			else if(q>end)
			{
				arr[k++]=a[p++];
			}
			else if(a[p]<=a[q])
			{
				arr[k++]=a[p++];
			}
			else
			{
				arr[k++]=a[q++];
			}
		}
		for(p=0;p<k;p++)
		{
			a[start++]=arr[p];
		}
	}

	public static void merge_sort(int[] a,int start,int end)
	{
		if(start<end)
		{
			int mid=(start+end)/2;
			merge_sort(a,start,mid);
			merge_sort(a,mid+1,end);
			merge(a,start,mid,end);
		}
	}
}
