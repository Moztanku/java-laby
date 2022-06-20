#include<iostream>
#include"Tree.hpp"

int main(int argc, char** argv) {
	Tree<int> T;
	Tree<std::string> s;
	T.insert(5);
	T.insert(2);
	T.insert(1);
	T.insert(6);
	T.insert(3);
	T.remove(5);
	s.insert("aaaa");
	s.insert("f");
	s.insert("b");
	s.insert("b");
	s.insert("e");
	s.insert("a");
	s.remove("aaaa");
	std::cout << T.draw() << std::endl;
	std::cout << s.draw() << std::endl;
	
	return 0;
};