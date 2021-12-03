#ifndef COMMAND_H_INCLUDED_
#define COMMAND_H_INCLUDED_

#include <istream>
#include <ostream>
#include <string>

namespace adv {

// Instance of a submarine command.
class Command {
 public:
  // The specific operator of the command (move forward, etc,.)
  enum class Operator { Null, Forward, Down, Up };

  // Construct Null command.
  Command();

  // Construct with operator, operand.
  Command(Operator op, int operand);

  // Read a command from a stream.
  // If the command is not valid, operator will be 'Null'.
  static Command Read(::std::istream& stream);

  // Returns true if command is null.
  bool IsNull() const;

  // Returns the operator of the command.
  Operator GetOperator() const;

  // Returns the operand of the command.
  int GetOperand() const;

 private:
  Operator operator_;
  int operand_;
};

// Canonical stream insertion, for debugging.
::std::ostream& operator<<(::std::ostream&, const Command&);

}  // namespace adv

#endif  // COMMAND_H_INCLUDED_
