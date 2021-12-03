#ifndef SUBMARINE_H_INCLUDED_
#define SUBMARINE_H_INCLUDED_

#include <ostream>

#include "command.h"

namespace adv {

class Submarine {
 public:
  // Construct a submarine, at depth (surfaced) and horizontal position 0.
  Submarine();

  // Execute a command. Depth and position may be affected.
  void ExecuteCommand(const Command&);

  // Get current depth.
  int GetDepth() const;

  // Get current horizontal position.
  int GetHorizontalPosition() const;

 private:
  int depth_;
  int position_;
};

// Stream insertion (for debugging)
::std::ostream& operator<<(::std::ostream&, const Submarine&);

}  // namespace adv

#endif  // SUBMARINE_H_INCLUDED_
