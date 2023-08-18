package Stack;

import java.util.HashMap;
import java.util.Map;

/*
LFU Cache

Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:
LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
When the cache reaches its capacity,
it should invalidate and remove the least frequently used key before inserting a new item.
For this problem, when there is a tie (i.e., two or more keys with the same frequency),
the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache.
The key with the smallest use counter is the least frequently used key.
When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
The use counter for a key in the cache is incremented either a get or put operation is called on it.
The functions get and put must each run in O(1) average time complexity.
*/
public class LFUCache {

    /*
     * @param key: node key
     * @param value: node value
     * @param frequency: frequency count of current node
     * (all nodes connected in same double linked list has same frequency)
     * @param previous: previous pointer of current node
     * @param next: next pointer of current node
     * */
    static class DLLNode {
        int key;
        int value;
        int frequency;
        DLLNode previous;
        DLLNode next;

        public DLLNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    /*
     * @param listSize: current size of double linked list
     * @param head: head node of double linked list
     * @param tail: tail node of double linked list
     * */
    static class DoubleLinkedList {
        int listSize;
        DLLNode head;
        DLLNode tail;

        public DoubleLinkedList() {
            this.listSize = 0;
            this.head = new DLLNode(0, 0);
            this.tail = new DLLNode(0, 0);
            head.next = tail;
            tail.previous = head;
        }

        /* add new node into head of list and increase list size by 1 */
        public void addNode(DLLNode newNode) {
            DLLNode nextNode = head.next;
            newNode.next = nextNode;
            newNode.previous = head;
            head.next = newNode;
            nextNode.previous = newNode;
            listSize++;
        }

        /* remove given node and decrease list size by 1*/
        public void removeNode(DLLNode currentNode) {
            DLLNode previousNode = currentNode.previous;
            DLLNode nextNode = currentNode.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            listSize--;
        }
    }


    final int capacity;
    int currentSize;
    int minimumFrequency;
    Map<Integer, DLLNode> cacheMap;
    Map<Integer, DoubleLinkedList> frequencyMap;

    /*
     * @param capacity: total capacity of LFU Cache
     * @param currentSize: current size of LFU cache
     * @param minimumFrequency: frequency of the last linked list (the minimum frequency of entire LFU cache)
     * @param cacheMap: a hash map that has key to Node mapping, which used for storing all nodes by their keys
     * @param frequencyMap: a hash map that has key to linked list mapping, which used for storing all
     * double linked list by their frequencies
     * */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        this.minimumFrequency = 0;
        this.cacheMap = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    /* get node value by key, and then update node frequency as well as relocate that node */
    public int get(int key) {
        DLLNode currentNode = cacheMap.get(key);
        if (currentNode == null) {
            return -1;
        }
        updateNode(currentNode);
        return currentNode.value;
    }

    public void updateNode(DLLNode currentNode) {
        int currentFrequency = currentNode.frequency;
        DoubleLinkedList currentList = frequencyMap.get(currentFrequency);
        currentList.removeNode(currentNode);
        // if current list is the list which has the lowest frequency and current node is the only node in that list
        // we need to remove the entire list and then increase min frequency value by 1
        if (currentFrequency == minimumFrequency && currentList.listSize == 0) {
            minimumFrequency++;
        }
        //increasing frequency as this node is accessed by get method or by put method
        currentNode.frequency++;
        // add current node to another list has current frequency + 1,
        // if we do not have the list with this frequency, initialize it
        DoubleLinkedList newList = frequencyMap.getOrDefault(currentNode.frequency, new DoubleLinkedList());
        newList.addNode(currentNode);
        frequencyMap.put(currentNode.frequency, newList);
    }

    /*
     * add new node into LFU cache, as well as double linked list
     * condition 1: if LFU cache has input key, update node value and node position in list
     * condition 2: if LFU cache does NOT have input key
     *  - sub condition 1: if LFU cache does NOT have enough space, remove the Least Recent Used node
     *  in minimum frequency list, then add new node
     *  - sub condition 2: if LFU cache has enough space, add new node directly
     * */
    public void put(int key, int value) {
        // corner case: check cache capacity initialization
        if (capacity == 0) {
            return;
        }
        if (cacheMap.containsKey(key)) {
            //updating the existing node's value and its frequency
            DLLNode currentNode = cacheMap.get(key);
            currentNode.value = value;
            updateNode(currentNode);
        } else {
            currentSize++;
            if (currentSize > capacity) {
                //get the minimum frequency list
                DoubleLinkedList minimumFrequencyList = frequencyMap.get(minimumFrequency);
                //Case of Tie is handled by LRU Cache mechanism
                //as when size is full, and multiple elements can be present in that list, so we need to remove element, we use LRU mechanism
                //in LRU cache mechanism, the node previous to tail node is least recently used, so this will be removed
                cacheMap.remove(minimumFrequencyList.tail.previous.key);
                //remove the least accessed(least recently used) node from the minimum frequency list
                minimumFrequencyList.removeNode(minimumFrequencyList.tail.previous);
                currentSize--;
            }
            // reset min frequency to 1 because of adding new node
            minimumFrequency = 1;
            DLLNode newNode = new DLLNode(key, value);
            // get the list with frequency 1, and then add new node into the list, as well as into LFU cache
            DoubleLinkedList currentList = frequencyMap.getOrDefault(1, new DoubleLinkedList());
            currentList.addNode(newNode);
            frequencyMap.put(newNode.frequency, currentList);
            cacheMap.put(key, newNode);
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfuCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfuCache.get(1));      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfuCache.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfuCache.get(2));      // return -1 (not found)
        System.out.println(lfuCache.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfuCache.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfuCache.get(1));      // return -1 (not found)
        System.out.println(lfuCache.get(3));      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfuCache.get(4));      // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}