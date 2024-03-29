JFLAGS = -g -d bin -s src -h src -cp bin -sourcepath src -Xlint:unchecked
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
		  State.java \
		  FreightSystem.java \
		  AStarRouter.java \
		  Node.java \
		  Edge.java \
		  Heuristic.java \
		  JobsHeuristic.java \
		  OpenList.java

FILES = $(addprefix src/, $(CLASSES))

classes: $(addprefix src/, $(CLASSES:.java=.class))

srcfiles: $(FILES)

default: classes

docs: srcfiles
	javadoc -tag pre:a:"Preconditions:" -tag post:a:"Postconditions:" -tag inv:a:"Invariants:" -d docs $(FILES)

clean: 
	$(RM) bin/*.class

