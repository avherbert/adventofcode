#include <fstream>
#include <iostream>

using ::std::cerr;
using ::std::cout;
using ::std::endl;
using ::std::getline;
using ::std::ifstream;
using ::std::string;

// Read next valid depth.
bool next_depth(ifstream& stream, int* depth) {
	if (stream.eof()) {
		return false;
	}

	string token;
	getline(stream, token);
	if (token.size() == 0) {
		// getline returning empty string for last line
		return false;
	}

  *depth = atoi(token.c_str());
	return true;
}

int main(int argc, char* argv[]) {
	// Open depth file.
	ifstream file;
	file.open("input.txt");
	
	// Prime by reading first depth value.
	int prev = 0;
	if (!next_depth(file, &prev)) {
		cerr << "failed to read first depth." << endl;
		return 1;
	}

	// Track count of depths largest than the previous measurement.
	int larger_than_previous = 0;

	// Read all valid entries
	int next = 0;
	while (next_depth(file, &next)) {
		if (next > prev) {
			++larger_than_previous;
		}
		prev = next;
	}

	cout << "Answer: " << larger_than_previous << endl;

	return 0;
}
