
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	HashOperations.java \
    LinearHashing.java \
    ChainedHashing.java \
    CuckooHashing.java \
	DoubleHashing.java \
	testcaseLinearHashing1.java \
	testcaseLinearHashing2.java \
	testcaseLinearHashing3.java \
	testcaseLinearHashing4.java \
	testcaseLinearHashing5.java \
	testcaseChainedHashing1.java \
	testcaseChainedHashing2.java \
	testcaseChainedHashing3.java \
	testcaseChainedHashing4.java \
	testcaseChainedHashing5.java \
	testcaseCuckooHashing1.java \
	testcaseCuckooHashing2.java \
	testcaseCuckooHashing3.java \
	testcaseCuckooHashing4.java \
	testcaseCuckooHashing5.java \
	testcaseDoubleHashing1.java \
	testcaseDoubleHashing2.java \
	testcaseDoubleHashing3.java \
	testcaseDoubleHashing4.java \
	testcaseDoubleHashing5.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class