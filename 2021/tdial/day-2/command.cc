#include "command.h"

#include <algorithm>
#include <cctype>
#include <iostream>
#include <istream>
#include <sstream>
#include <string>

using ::std::back_inserter;
using ::std::cout;
using ::std::endl;
using ::std::getline;
using ::std::istringstream;
using ::std::string;
using ::std::tolower;
using ::std::transform;

namespace {

// Convert string to lower case
std::string Lowercase(string input) {
  transform(input.begin(), input.end(), input.begin(),
            [](unsigned char c) { return tolower(c); });
  return input;
}

}  // anonymous namespace

namespace adv {

// The specific operator of the command (move forward, etc,.)
// enum class Operator { Null, Forward, Down, Up };

// Default constructor
Command::Command() : operator_(Operator::Null), operand_(0) {}

// Construct with known operator, operand.
Command::Command(Operator op, int operand) : operator_(op), operand_(operand) {}

// Read a command from a stream.
// If the command is not valid, operator will be 'Null'.
Command Command::Read(::std::istream& stream) {
  // Read one line at a time. Makes it simpler to recover from a bad command.
  string line;
  getline(stream, line);
  if (line.size() == 0) {
    // No command, return Null object.
    return Command();
  }

  istringstream iss(line);

  string opstring;
  iss >> opstring;
  opstring = Lowercase(opstring);

  int operand = 0;
  iss >> operand;

  Operator opcode(Operator::Null);
  if (opstring == "forward") {
    opcode = Operator::Forward;
  } else if (opstring == "down") {
    opcode = Operator::Down;
  } else if (opstring == "up") {
    opcode = Operator::Up;
  }

  return Command(opcode, operand);
}

// Returns true if command is null.
bool Command::IsNull() const { return (operator_ == Operator::Null); }

// Returns the operator of the command.
Command::Operator Command::GetOperator() const { return operator_; }

// Returns the operand of the command.
int Command::GetOperand() const { return operand_; }

// Stream insertion
::std::ostream& operator<<(::std::ostream& os, const Command& cmd) {
  switch (cmd.GetOperator()) {
    case Command::Operator::Forward:
      os << "forward";
      break;
    case Command::Operator::Down:
      os << "down";
      break;
    case Command::Operator::Up:
      os << "up";
      break;
    default:
      os << "(null)";
      break;
  }
  os << " ";
  os << cmd.GetOperand();
  return os;
}

}  // namespace adv
