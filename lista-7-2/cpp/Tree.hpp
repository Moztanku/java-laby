#pragma once
#include<string>
#include<memory>

template<class T>
struct TreeNode {
	TreeNode(T _elem) {
		this->elem = _elem;
	};

	T elem;
	TreeNode<T>* left = nullptr;
	TreeNode<T>* right = nullptr;
};

template<class T>
class Tree {
	public:
		inline Tree() {};
		inline bool search(T elem) { return searchNode(elem, this->root); };
		inline void insert(T elem) { insertNode(elem,this->root); };
		inline void remove(T elem) { removeNode(elem, this->root); };
		std::string draw() { return drawNode(this->root); };
	private:
		bool searchNode(T elem, TreeNode<T>* w);
		void insertNode(T elem, TreeNode<T>*& w);
		void removeNode(T elem, TreeNode<T>*& w);
		void concatNode(TreeNode<T>*& w, TreeNode<T>*& l);
		std::string drawNode(TreeNode<T>* w);

		TreeNode<T>* root = nullptr;
};

template<class T>
bool Tree<T>::searchNode(T elem, TreeNode<T>* w) {
	if (w == nullptr)
		return false;
	else if (w->elem == elem)
		return true;
	else if (w->elem > elem)
		return searchNode(elem, w->left);
	else
		return searchNode(elem, w->right);
	return 0;
};

template<class T>
void Tree<T>::insertNode(T elem, TreeNode<T>*& w) {
	if (w == nullptr) {
		w = new TreeNode<T>(elem);
	}
	else if (w->elem > elem)
		insertNode(elem, w->left);
	else if (w->elem < elem)
		insertNode(elem, w->right);
};

template<class T>
void Tree<T>::removeNode(T elem, TreeNode<T>*& w) {
	if (w == nullptr)
		return;
	else if (w->elem == elem) {
		if (w->right == nullptr) {
			TreeNode<T>* tempLeft = w->left;
			delete w;
			w = tempLeft;
		}
		else {
			TreeNode<T>* tempLeft = w->left;
			TreeNode<T>* tempRight = w->right;
			delete w;
			w = tempRight;
			concatNode(w->left, tempLeft);
		}
	}
	else if (w->elem > elem)
		removeNode(elem, w->left);
	else
		removeNode(elem, w->right);
};

template<class T>
void Tree<T>::concatNode(TreeNode<T>*& w, TreeNode<T>*& l) {
	if (w == nullptr)
		w = l;
	else
		concatNode(w->left, l);
};

template<class T>
std::string Tree<T>::drawNode(TreeNode<T>* w) {
	if (w == nullptr)
		return "";
	else
		return "{"+drawNode(w->left)+" "+std::to_string(w->elem)+" "+drawNode(w->right)+"}";
};

std::string Tree<std::string>::drawNode(TreeNode<std::string>* w) {
	if (w == nullptr)
		return "";
	else
		return "{"+drawNode(w->left) + " " + w->elem + " " + drawNode(w->right)+"}";
};