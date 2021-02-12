package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class LinkedList2Test {

    Node node1;
    Node node2;
    Node node3;
    Node node4;
    Node node5;

    Node node6;
    Node node7;
    Node node8;
    Node node9;
    Node node10;
    Node node11;

    LinkedList2 linkedList2Growing;
    LinkedList2 linkedList2Empty;
    LinkedList2 linkedList2Repeat;

    @BeforeEach
    public void setUp() {
        node1 = new Node(1);
        node2 = new Node(2);
        node3 = new Node(3);
        node4 = new Node(4);
        node5 = new Node(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        node5.setPrev(node4);
        node4.setPrev(node3);
        node3.setPrev(node2);
        node2.setPrev(node1);

        linkedList2Growing = new LinkedList2();
        linkedList2Growing.setHead(node1);
        linkedList2Growing.setTail(node5);

        linkedList2Empty = new LinkedList2();

        node6 = new Node(6);
        node7 = new Node(7);
        node8 = new Node(8);
        node9 = new Node(6);
        node10 = new Node(6);
        node11 = new Node(8);

        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node10);
        node10.setNext(node11);

        node7.setPrev(node6);
        node8.setPrev(node7);
        node9.setPrev(node8);
        node10.setPrev(node9);
        node11.setPrev(node10);

        linkedList2Repeat = new LinkedList2();
        linkedList2Repeat.setHead(node6);
        linkedList2Repeat.setTail(node11);
    }

    @AfterEach
    public void tearDown() {
        linkedList2Empty = null;
        linkedList2Growing = null;
        linkedList2Repeat = null;
    }

    @Test
    public void addInTailNullElements() {
        Node node = new Node(1);
        Node node101 = new Node(1);

        LinkedList2 linkedListExpected = new LinkedList2();
        linkedListExpected.setHead(node);
        linkedListExpected.setTail(node);

        linkedList2Empty.addInTail(node101);
        assertEquals(linkedListExpected, linkedList2Empty);

        assertThat(linkedList2Empty, is(linkedListExpected));
    }

    @Test
    public void addInTail1Node() {
        Node node12 = new Node(12);

        LinkedList2 linkedList2Actual = new LinkedList2();
        linkedList2Actual.setHead(node11);
        linkedList2Actual.setTail(node11);
        linkedList2Actual.addInTail(node12);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node11);
        linkedList2Expected.setTail(node12);
        node11.setNext(node12);

        assertEquals(linkedList2Expected, linkedList2Actual);

        assertThat(linkedList2Actual, is(linkedList2Expected));
    }

    @Test
    public void findNodeExist() {
        assertEquals(node3, linkedList2Growing.find(3));
        assertEquals(node6, linkedList2Repeat.find(6));

        assertThat(linkedList2Growing.find(3), is(node3));
        assertThat(linkedList2Growing.find(6), is(not(node6)));
        assertThat(linkedList2Repeat.find(6), is(node6));

    }

    @Test
    public void findNodeNotExist() {
        assertNull(linkedList2Growing.find(100));

        assertThat(linkedList2Growing.find(100), nullValue());
    }

    @Test
    public void findAllValue6ShouldNode6Node9Node10() {
/*        List<Node> nodeListExpected = new ArrayList<>() {{
            add(node6);
            add(node9);
            add(node10);
        }};
        */
        List<Node> nodeListExpected = List.of(node6, node9, node10);

        assertEquals(nodeListExpected, linkedList2Repeat.findAll(6));

        assertThat(linkedList2Repeat.findAll(6), is(nodeListExpected));
    }

    @Test
    public void findAllNotExist() {
        assertEquals(new ArrayList<Node>(), linkedList2Repeat.findAll(100));

        assertThat(linkedList2Repeat.findAll(100), emptyCollectionOf(Node.class));
        assertThat(linkedList2Repeat.findAll(100), empty());
    }

    @Test
    public void removeNodeNotExist() {
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node101.setNext(node102);
        node102.setNext(node103);
        node103.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node103);
        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 LinkedList2Expected = new LinkedList2();
        LinkedList2Expected.setHead(node101);
        LinkedList2Expected.setTail(node105);

        assertFalse(linkedList2Repeat.remove(100));
        assertEquals(LinkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Repeat.remove(100), is(false));
        assertThat(linkedList2Growing, is(equalTo(LinkedList2Expected)));
    }

    @Test
    public void removeNodeHead() {

        Node node102 = new Node(2);
        Node node103 = new Node(3);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node102.setNext(node103);
        node103.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node103);
        node103.setPrev(node102);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node102);
        linkedList2Expected.setTail(node105);

        assertTrue(linkedList2Growing.remove(1));
        assertEquals(linkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void removeNodeTail() {
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);
        Node node104 = new Node(4);

        node101.setNext(node102);
        node102.setNext(node103);
        node103.setNext(node104);

        node104.setPrev(node103);
        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node104);

        assertTrue(linkedList2Growing.remove(5));
        assertEquals(linkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void removeNodeMiddle() {
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node101.setNext(node102);
        node102.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node105);

        assertTrue(linkedList2Growing.remove(3));
        assertEquals(linkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void removeAllElementNotExist() {
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node101.setNext(node102);
        node102.setNext(node103);
        node103.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node103);
        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node105);

        linkedList2Growing.removeAll(100);
        assertEquals(linkedList2Expected, linkedList2Growing);
        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void removeAll_RemoveOnlyHead() {
        Node node102 = new Node(2);
        Node node103 = new Node(3);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node102.setNext(node103);
        node103.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node103);
        node103.setPrev(node102);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node102);
        linkedList2Expected.setTail(node105);

        linkedList2Growing.removeAll(1);

        assertEquals(linkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void removeAll_RemoveOnlyMiddle1Element() {
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node101.setNext(node102);
        node102.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node105);

        linkedList2Growing.removeAll(3);

        assertEquals(linkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void removeAll_RemoveOnlyMiddle13Element() {
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node104 = new Node(4);
        Node node105 = new Node(5);

        node101.setNext(node102);
        node102.setNext(node104);
        node104.setNext(node105);

        node105.setPrev(node104);
        node104.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node105);

        Node node33 = new Node(3);
        Node node333 = new Node(3);

        node3.setNext(node33);
        node33.setNext(node333);
        node333.setNext(node4);

        node4.setPrev(node333);
        node333.setPrev(node33);
        node33.setPrev(node3);

        linkedList2Growing.removeAll(3);

        assertEquals(linkedList2Expected, linkedList2Growing);
    }

    @Test
    public void removeAll_RemoveOnlyTail() {

        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);
        Node node104 = new Node(4);

        node101.setNext(node102);
        node102.setNext(node103);
        node103.setNext(node104);

        node104.setPrev(node103);
        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node104);

        linkedList2Growing.removeAll(5);

        assertEquals(linkedList2Expected, linkedList2Growing);
    }

    @Test
    public void removeAll_1Element_NodeNotExist() {
        Node node = new Node(1);
        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node);
        linkedList2Expected.setTail(node);

        Node node_1 = new Node(1);
        LinkedList2 linkedList21Node = new LinkedList2();
        linkedList21Node.setHead(node_1);
        linkedList21Node.setTail(node_1);

        linkedList21Node.removeAll(100);

        assertEquals(linkedList2Expected, linkedList21Node);
    }

    //Must find in LinkedList2 1 Element

    @Test
    public void removeAll_1ElementRepeat() {
        Node node101 = new Node(1);
        Node node102 = new Node(1);
        Node node103 = new Node(1);

        node101.setNext(node102);
        node102.setNext(node103);

        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Actual = new LinkedList2();
        linkedList2Actual.setHead(node101);
        linkedList2Actual.setTail(node103);

        linkedList2Actual.removeAll(1);

        assertEquals(linkedList2Actual, linkedList2Empty);
    }

    @Test
    public void removeAll_1Element() {
        Node node = new Node(1);
        LinkedList2 linkedList2Actual = new LinkedList2();
        linkedList2Actual.setHead(node);
        linkedList2Actual.setTail(node);

        linkedList2Actual.removeAll(1);

        assertEquals(linkedList2Empty, linkedList2Actual);
    }


    @Test
    public void removeAll_Repeat_removeMiddle3Element() {
        Node node107 = new Node(7);
        Node node108 = new Node(8);
        Node node111 = new Node(8);

        node107.setNext(node108);
        node108.setNext(node111);

        node111.setPrev(node108);
        node108.setPrev(node107);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node107);
        linkedList2Expected.setTail(node111);

        linkedList2Repeat.removeAll(6);

        assertEquals(linkedList2Expected, linkedList2Repeat);
    }

    //Repeat
    @Test
    public void removeAll_Repeat_removeMiddleAndTail2Element() {
        Node node106 = new Node(6);
        Node node107 = new Node(7);
        Node node109 = new Node(6);
        Node node110 = new Node(6);

        node106.setNext(node107);
        node107.setNext(node109);
        node109.setNext(node110);

        node110.setPrev(node109);
        node109.setPrev(node107);
        node107.setPrev(node106);

        LinkedList2 linkedList2TestExpected = new LinkedList2();
        linkedList2TestExpected.head = node106;
        linkedList2TestExpected.tail = node110;

        linkedList2Repeat.removeAll(8);
        assertEquals(linkedList2TestExpected, linkedList2Repeat);

        assertThat(linkedList2Repeat, is(linkedList2TestExpected));
    }

    @Test
    public void removeAll() {
        Node node101 = new Node(1);
        Node node102 = new Node(1);
        Node node103 = new Node(1);

        node101.setNext(node102);
        node102.setNext(node103);

        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Actual = new LinkedList2();
        linkedList2Actual.setHead(node101);
        linkedList2Actual.setTail(node103);

        linkedList2Actual.removeAll(1);
        assertEquals(linkedList2Empty, linkedList2Actual);

        assertThat(linkedList2Actual, is(linkedList2Empty));
    }

    @Test
    public void clearTailHeadShouldBeNull() {
        linkedList2Repeat.clear();
        assertNull(linkedList2Repeat.getHead());
        assertNull(linkedList2Repeat.getTail());

        assertThat(linkedList2Repeat.getHead(), nullValue());
        assertThat(linkedList2Repeat.getTail(), nullValue());
    }

    @Test
    public void count5Elements() {
        assertEquals(5, linkedList2Growing.count());
        assertEquals(6, linkedList2Repeat.count());

        assertThat(linkedList2Growing.count(), is(5));
        assertThat(linkedList2Repeat.count(), is(6));
    }

    @Test
    public void countNullElements() {
        assertEquals(0, linkedList2Empty.count());
        assertThat(linkedList2Empty.count(), is(0));
    }

    @Test
    public void insertAfterNull() {
        Node node0 = new Node(100);
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);

        node0.setNext(node101);
        node101.setNext(node102);
        node102.setNext(node103);

        node103.setPrev(node102);
        node102.setPrev(node101);
        node101.setPrev(node0);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node0);
        linkedList2Expected.setTail(node103);

        node3.setNext(null);
        linkedList2Growing.setTail(node3);
        Node nodeInsert = new Node(100);

        linkedList2Growing.insertAfter(null, nodeInsert);
//        assertEquals(linkedList2Expected, linkedList2Growing);

        assertThat(linkedList2Growing, is(linkedList2Expected));
    }

    @Test
    public void insertAfter1Element() {
        Node node0 = new Node(100);
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);

        node101.setNext(node0);
        node0.setNext(node102);
        node102.setNext(node103);

        node103.setPrev(node102);
        node102.setPrev(node0);
        node0.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node103);

        node3.setNext(null);
        linkedList2Growing.setTail(node3);
        Node nodeInsert = new Node(100);

        linkedList2Growing.insertAfter(node1, nodeInsert);
        assertEquals(linkedList2Expected, linkedList2Growing);
    }


    @Test
    public void insertAfter1ElemTail() {
        Node node0 = new Node(100);
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);

        node101.setNext(node102);
        node102.setNext(node103);
        node103.setNext(node0);

        node0.setPrev(node103);
        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 LinkedList2Test = new LinkedList2();
        LinkedList2Test.setHead(node101);
        LinkedList2Test.setTail(node0);

        node3.setNext(null);
        linkedList2Growing.setTail(node3);
        Node nodeInsert = new Node(100);

        linkedList2Growing.insertAfter(node3, nodeInsert);
        assertEquals(LinkedList2Test, linkedList2Growing);
    }


    @Test
    public void insertAfter1ElementTail() {
        Node node0 = new Node(100);
        Node node101 = new Node(1);

        node101.setNext(node0);
        node0.setNext(null);

        node0.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node0);

        node1.next = null;
        linkedList2Growing.tail = node1;
        Node nodeInsert = new Node(100);

        linkedList2Growing.insertAfter(node1, nodeInsert);
        assertEquals(linkedList2Expected, linkedList2Growing);
    }

    @Test
    public void insertAfterMiddle() {
        Node node0 = new Node(100);
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);

        node101.setNext(node102);
        node102.setNext(node0);
        node0.setNext(node103);

        node103.setPrev(node0);
        node0.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node103);

        node3.next = null;
        linkedList2Growing.tail = node3;
        Node nodeInsert = new Node(100);

        linkedList2Growing.insertAfter(node2, nodeInsert);
        assertEquals(linkedList2Expected, linkedList2Growing);
    }

    @Test
    public void insertAfterEmptyList() {
        LinkedList2 LinkedList2Expected = new LinkedList2();
        node1.next = null;
        LinkedList2Expected.head = node1;
        LinkedList2Expected.tail = node1;

        Node node101 = new Node(1);

        linkedList2Empty.insertAfter(null, node101);
        assertEquals(LinkedList2Expected, linkedList2Empty);
    }

    @Test
    public void insertAfterTail() {
        Node node0 = new Node(100);
        Node node101 = new Node(1);
        Node node102 = new Node(2);
        Node node103 = new Node(3);

        node101.setNext(node102);
        node102.setNext(node103);
        node103.setNext(node0);

        node0.setPrev(node103);
        node103.setPrev(node102);
        node102.setPrev(node101);

        LinkedList2 linkedList2Expected = new LinkedList2();
        linkedList2Expected.setHead(node101);
        linkedList2Expected.setTail(node0);

        node3.next = null;
        linkedList2Growing.tail = node3;
        Node nodeInsert = new Node(100);

        linkedList2Growing.insertAfter(node3, nodeInsert);
        assertEquals(linkedList2Expected, linkedList2Growing);
    }

    @Test
    public void equalsNode() {
        Node node101 = new Node(1);
        // check next = null
        node101.value = 1;
        node101.next = null;

        node1.next = null;
        assertEquals(node1, node101);

        // check next = Node
        Node node102 = new Node(2);

        node1.next = node2;
        node101.next = node102;

        assertEquals(node1, node101);

        node102.setPrev(node101);

        assertThat(node101, is(equalTo(node1)));
        assertThat(node102, is(not(node2)));

        // check Node = null
        node1 = null;
        node101 = null;
        assertEquals(node1, node101);
    }

    @Test
    public void equalsLinkedList2() {
        assertFalse(linkedList2Growing.equals(linkedList2Empty));
        assertFalse(linkedList2Growing.equals(linkedList2Repeat));
        assertTrue(linkedList2Empty.equals(new LinkedList2()));
        assertTrue(linkedList2Growing.equals(linkedList2Growing));

        assertThat(linkedList2Growing, is(not(linkedList2Empty)));
        assertThat(linkedList2Growing, is(not(linkedList2Repeat)));
        assertThat(linkedList2Growing, is(equalTo(linkedList2Growing)));
        assertThat(linkedList2Empty, is(new LinkedList2()));
    }

    @Test
    public void equalsLinkedList2_SameLength() {
        node6.setValue(1);
        node10.setNext(null);
        linkedList2Repeat.setTail(node10);
        assertFalse(linkedList2Growing.equals(linkedList2Repeat));
        assertNotEquals(linkedList2Growing, linkedList2Repeat);
        assertThat(linkedList2Growing, is(not(linkedList2Repeat)));
    }
}