#include "submarine.h"

#include <iostream>
#include <ostream>

#include "command.h"

namespace adv {

// Construct a submarine, at depth (surfaced) and horizontal position 0.
Submarine::Submarine() : depth_(0), position_(0) {}

// Execute a command. Depth and position may be affected.
void Submarine::ExecuteCommand(const Command& cmd) {
  const int operand = cmd.GetOperand();
  switch (cmd.GetOperator()) {
    case Command::Operator::Forward:
      // Advance position.
      position_ += operand;
      break;

    case Command::Operator::Down:
      // Dive
      depth_ += operand;
      break;

    case Command::Operator::Up:
      // Surface
      depth_ -= operand;

      // The sub may never end up higher than sea level (negative depth!)
      if (depth_ < 0) {
        depth_ = 0;
      }
      break;

    default:
      // Do nothing.
      break;
  }
}

// Get current depth.
int Submarine::GetDepth() const { return depth_; }

// Get current horizontal position.
int Submarine::GetHorizontalPosition() const { return position_; }

::std::ostream& operator<<(::std::ostream& os, const Submarine& sub) {
  os << "Depth: " << sub.GetDepth()
     << ", Position: " << sub.GetHorizontalPosition();
  return os;
}

}  // namespace adv
