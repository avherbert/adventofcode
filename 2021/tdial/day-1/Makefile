CXX=g++
CXXFLAGS=-Wall -Werror -std=c++17

.PHONY:
all: main

.PHONY:
clean:
	-rm -f main *.o

main: main.o
	$(CXX) $(CXXFLAGS) -o main $^

.cc.o:
	$(CXX) $(CXXFLAGS) -c $<


