CXX=g++
CXXFLAGS=-Wall -Werror -std=c++17

.PHONY:
all: main

.PHONY:
clean:
	-rm -f main *.o

.PHONY:
format:
	clang-format -style=Google -i *.h *.cc

main: main.o command.o submarine.o
	$(CXX) $(CXXFLAGS) -o main $^

.cc.o:
	$(CXX) $(CXXFLAGS) -c $<


