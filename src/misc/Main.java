package misc;

import java.util.*;
import data_structures.MergeSortTree;
public class Main
{
	int last(int arr[], int low, int high, int x, int n)
	{
	    if (high >= low)
	    {
	        int mid = low + (high - low)/2;
	        if (( mid == n-1 || x < arr[mid+1]) && arr[mid] == x)
	            return mid;
	        else if (x < arr[mid])
	            return last(arr, low, (mid -1), x, n);
	        else
	            return last(arr, (mid + 1), high, x, n);
	    }
	    return -1;
	}
	public static void main(String[] args)
	{
		int[] arr= {1,2,2,2,3,3,5,5,10};
		MergeSortTree mst=new MergeSortTree(arr);
		System.out.println(mst.occurrences(0,8,10));
		//System.out.println(first(arr,0,8,2,9));
	}
}
