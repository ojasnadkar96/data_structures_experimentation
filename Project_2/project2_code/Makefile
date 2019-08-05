
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	TreeOperations.java \
    BinarySearchTree.java \
    AVLTree.java \
    SplayTree.java \
	TreeHeap.java \
	testcaseBinarySearchTree1.java \
	testcaseBinarySearchTree2.java \
	testcaseBinarySearchTree3.java \
	testcaseBinarySearchTree4.java \
	testcaseBinarySearchTree5.java \
	testcaseAVLTree1.java \
	testcaseAVLTree2.java \
	testcaseAVLTree3.java \
	testcaseAVLTree4.java \
	testcaseAVLTree5.java \
	testcaseSplayTree1.java \
	testcaseSplayTree2.java \
	testcaseSplayTree3.java \
	testcaseSplayTree4.java \
	testcaseSplayTree5.java \
	testcaseTreeHeap1.java \
	testcaseTreeHeap2.java \
	testcaseTreeHeap3.java \
	testcaseTreeHeap4.java \
	testcaseTreeHeap5.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class