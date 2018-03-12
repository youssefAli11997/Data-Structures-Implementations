/*
  Author : Youssef Ali
  Description : - A simple segment tree implementation without classes.
                - The three basic functions of segment trees : build, update and query.
                - A Helper function to print the elements.
		- A main function to run the program.
                - Assuming that The queries are to find sum of ranges.
*/
#include <bits/stdc++.h>
using namespace std;
const int MAX = 100;

int seg[4 * MAX + 5], arr[MAX], n, q;

// segment tree functions

/*
	[start, end] : is the range which a given node represents.
	idx : the index of the element in the array.
	node : the index of the element in the segment tree.
	val : the value by which a given element will be incremented.
	[left, right] : is the query range.
*/

void build(int node, int start, int end){ // O(N), N is number of segment tree nodes
	if(start == end)
		seg[node] = arr[start];
	else{
		int mid = start + (end - start) / 2;
		build(2 * node, start, mid); // start child
		build(2 * node + 1, mid + 1, end); // end child
		seg[node] = seg[2 * node] + seg[2 * node + 1]; // if it was asked to return range-sums
	}
}

void update(int node, int start, int end, int idx, int val){ // O(log N)
	if(start == end){
		arr[idx] += val; // if the update was to increase the value of the element
		seg[node] += val;
	}
	else{
		int mid = start + (end - start) / 2;
		if(idx >= start && idx <= mid) // is in the start range
			update(2 * node, start, mid, idx, val);
		else // is in the end range
			update(2 * node + 1, mid + 1, end, idx, val);
		seg[node] = seg[2 * node] + seg[2 * node + 1]; // assuming it was asked to return range-sums
	}
}

int query(int node, int start, int end, int left, int right){ // O(log N)
	if(end < left || start > right) // out
		return 0;
	if(start >= left && end <= right) // in
		return seg[node];
	else{ // partially in, partially out
		int mid = start + (end - start) / 2;
		int p1 = query(2 * node, start, mid, left, right); // left child
		int p2 = query(2 * node + 1, mid + 1, end, left, right); // right child
		return p1 + p2; // assuming it was asked to return range-sums
	}
}

void printTheArray(){
	cout<<"The elements are:\n";
	for(int i=0; i<n; i++)
		cout<<arr[i]<<" ";
	cout<<endl;
}

int main(){
	cout<<"Welcome to my sum-range-query segment tree\n";
	cout<<"How many elements would you like to enter ?\n";
	cin>>n;
	cout<<"Enter the "<< n <<" elements\n";
	
	for(int i=0; i<n; i++) cin>>arr[i];
	build(1, 0, n - 1);
	
	cout<<"How many queries do you want ?\n";
	cin>>q;
	cout<<"Enter the "<< q <<" queries of the array.\n";
	cout<<"Each query sould be one of three types :\n\n";
	cout<<"--- update index value\n";
	cout<<"--- sum left right\n";
	cout<<"--- print\n";
	cout<<"\nNOTE : ALL IS 0-BASED\n";
	
	while(q--){
		string command; cin>>command;
		if(command == "update"){
			int index, value;
			cin>> index >> value;
			if(index >= n){
				cout<<"Invalid! Index should be in the range 0 : "<< n - 1 <<"\n";
				q++;
				continue;
			}
			update(1, 0, n - 1, index, value);
			cout<<"Updated successfully, element #"<< index <<" = "<< arr[index] <<"\n";
		}
		else if(command == "sum"){
			int left, right;
			cin>> left >> right;
			if(left < 0 || right >= n || left > right){
				cout<<"Invalid range!\n";
				q++;
				continue;
			}
			int sum = query(1, 0, n - 1, left, right);
			cout<<"The sum of range from "<<left<<" to "<<right<<" : \n";
			cout<<sum<<endl;
		}
		else if(command == "print"){
			printTheArray();
		}
		else{ // invalid command
			cout<<"Invalid Command, just type : \"update\" then 2 integers , \"sum\" then 2 integers or simply \"print\"\n";
			q++; // to ignore this invalid query
		}
	}
}
