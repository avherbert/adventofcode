#include <fstream>
#include <iostream>

#include "command.h"
#include "submarine.h"

using ::std::cerr;
using ::std::cout;
using ::std::endl;
using ::std::ifstream;

using ::adv::Command;
using ::adv::Submarine;

int main(int argc, char* argv[]) {
  // Open the command file.
  ifstream file;
  file.open("data/input.txt");

  // Declare submarine, which processes command and tracks its own position.
  Submarine sub;

  // Read through the "orders" for the submarine and execute them.
  while (!file.eof()) {
    Command cmd(Command::Read(file));
    sub.ExecuteCommand(cmd);
  }

  // Print the answer to Day 2
  cout << "Answer: " << sub.GetDepth() * sub.GetHorizontalPosition() << endl;

  return 0;
}
