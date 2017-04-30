JFLAGS = -g -d bin -s src -h src -cp bin -sourcepath src -Xlint:unchecked
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
		  FreightSystem.java \
		  AStarRouter.java \
		  Node.java \
		  RoutingState.java \
		  Edge.java \
		  Heuristic.java \
		  UnionFind.java

FILES = $(addprefix src/, $(CLASSES))

classes: $(addprefix src/, $(CLASSES:.java=.class))

srcfiles: $(FILES)

default: classes

docs: srcfiles
	javadoc -tag pre:a:"Preconditions:" -tag post:a:"Postconditions:" -tag inv:a:"Invariants:" -d docs $(FILES)

clean: 
	$(RM) bin/*.class

