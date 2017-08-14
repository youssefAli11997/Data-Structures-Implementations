// Trie (Letter Tree) implementation
// C-style implementation
#include <bits/stdc++.h>
using namespace std;

typedef struct trie_node trie_node;

// trie node
struct trie_node{
	trie_node * children[26]; // 26 lower case alphabet letters
	bool isLeaf;
};

trie_node * createNode(){
	trie_node * newNode = (trie_node *)  malloc(sizeof (trie_node) ); // allocate some memory for new node
																   	 // and point to it
	if(newNode){ // if that worked fine
		newNode -> isLeaf = false;
		
		for(int i = 0; i < 26; i++)
			newNode -> children[i] = NULL;
	}
	
	return newNode;
}

// if not precent, inserts key into trie
// if the key is present as a prefix, just marks leaf node
void insert(trie_node * root, string key){

	int length = key.size(), index;
	trie_node * crawl = root;
	
	for(int level = 0; level < length; level++){
		index = key[level] - 'a';
		if(crawl -> children[index] == NULL) // if node didn't created yet
			crawl -> children[index] = createNode(); // create it
		crawl = crawl -> children[index];
	}

	// set last node as leaf (end of the key)
	crawl -> isLeaf = true;
}

// returns true if key presents in trie, else false
bool search(trie_node * root, string key){
	int length = key.size(), index;
	trie_node * crawl = root;
	
	for(int level = 0; level < length; level++){
		index = key[level] - 'a';
		if(crawl -> children[index] == NULL)
			return false;
		crawl = crawl -> children[index];
	}
	
	// return (crawl != NULL); // uncomment this if you search for a prefix not a whole key
	return (crawl != NULL) && (crawl -> isLeaf == true);
}

// Driver Program
int main(){
	string keys[] = {"any", "an", "there", "their", "three", "much", "more"};
	string state[] = {"key is not in the trie", "key is in the trie"};
	string searchFor[] = {"the", "they", "their", "t", "more"};
	
	// create a new trie
	trie_node * root = createNode();
	
	// add the keys to the trie
	for(int i = 0; i < sizeof(keys) / sizeof(string); i++)
		insert(root, keys[i]);
	
	// search for some keys
	for(int i = 0; i < sizeof(searchFor) / sizeof(string); i++)
		cout << "'" << searchFor[i] << "'" << " " << state[search(root, searchFor[i])] << "\n";
	
	return 0;
}
