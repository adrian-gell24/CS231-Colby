/**
 * Author: Adrian Gellert
 * Date: December 12, 2022
 * File: HashSet.java
 * Section: Lecture A, Lab D
 * Project: Path Around US
 * Course: CS231 
*/

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Deque<T>, Cloneable {

    private static class HashNode<T> implements Iterable<T> {
        HashNode<T> next, after, before, prev;
        T data;

        public HashNode(T data) {
            this.data = data;
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                HashNode<T> cur = HashNode.this;

                public T next() {
                    T toReturn = cur.data;
                    cur = cur.next;
                    return toReturn;
                }

                public boolean hasNext() {
                    return cur != null;
                }
            };
        }
    }

    private HashNode<T>[] set;
    /**
     * The number of items in this HashSet
     */
    protected int size;
    private HashNode<T> head, tail;

    /**
     * Creates an empty HashSet.
     */
    public HashSet() {
        set = (HashNode<T>[]) new HashNode[16];
        size = 0;
        head = tail = null;
    }

    /**
     * Returns the number of items in this HashSet.
     * 
     * @return the number of items in this HashSet.
     */
    public int size() {
        return size;
    }

    private int hash(Object item) {
        return Math.abs(item.hashCode() % set.length);
    }

    private void resize(int newSize) {
        HashNode<T> cur = head;
        head = null;
        tail = null;
        size = 0;
        set = (HashNode<T>[]) new HashNode[newSize];
        while (cur != null) {
            add(cur.data);
            cur = cur.after;
        }
    }

    /**
     * Returns an iterator over this HashSet in the proper sequence.
     * 
     * @return an iterator over this HashSet in the proper sequence.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            HashNode<T> before;
            HashNode<T> cur = HashSet.this.head;

            public T next() {
                before = cur;
                cur = cur.after;
                return before.data;
            }

            public boolean hasNext() {
                return cur != null;
            }

            public void remove() {
                if (before == null)
                    return;
                removeNode(before);
            }
        };
    }

    /**
     * Returns the item in this HashSet that is equal to the specified item, or
     * {@code null} if no such item exists
     * 
     * <p>
     * Note that these need not be explicitly the same item.
     * 
     * @param item the item to be found in the HashSet
     * @return the item in this HashSet that is equal to the specified item, or
     *         {@code null} if no such item exists
     */
    public T get(T item) {
        if (set[hash(item)] == null)
            return null;
        for (T t : set[hash(item)]) {
            if (t.equals(item))
                return t;
        }
        return null;
    }

    /**
     * Retrieves, but does not remove, the first element of this HashSet.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T getFirst() {
        return size > 0 ? head.data : null;
    }

    /**
     * Retrieves, but does not remove, the last element of this HashSet.
     * 
     * @return the last element of this HashSet.
     */
    public T getLast() {
        return size > 0 ? tail.data : null;
    }

    /**
     * Returns {@code true} if this HashSet contains the specified element.
     * 
     * <p>
     * More formally, returns {@code true} if and only if this HashSet contains an
     * element {@code e} such that {@code Objects.equals(o, e)}.
     * 
     * @param o element whose presence in this HashSet is to be tested
     * 
     * @return {@code true} if this HashSet contains the specified element.
     */
    public boolean contains(Object o) {
        if (set[hash(o)] == null)
            return false;
        for (T item : set[hash(o)])
            if (item.equals(o))
                return true;
        return false;
    }

    /**
     * Returns {@code true} if this collection contains all of the elements in the
     * specified collection.
     * 
     * @param items collection to be checked for containment in this collection
     * 
     * @return {@code true} if this collection contains all of the elements in the
     *         specified collection.
     */
    public boolean containsAll(Collection<?> items) {
        for (Object item : items)
            if (!contains(item))
                return false;
        return true;
    }

    private void removeNode(HashNode<T> node) {
        if (node.prev != null)
            node.prev.next = node.next;
        else
            set[hash(node.data)] = node.next;

        if (node.next != null)
            node.next.prev = node.prev;

        if (node.before != null)
            node.before.after = node.after;
        else
            head = node.after;

        if (node.after != null)
            node.after.before = node.before;
        else
            tail = node.before;

        if (head == null || tail == null)
            head = tail = null;
    }

    private void decrementSize(int x) {
        size -= x;
        if (size * 4 < set.length && set.length / 2 >= 16)
            resize(set.length / 2);
    }

    /**
     * Retrieves and removes the first element of this HashSet.
     * 
     * @return the first element of this HashSet.
     */
    public T removeFirst() {
        T toReturn = head.data;
        removeNode(head);
        decrementSize(1);
        return toReturn;
    }

    /**
     * Retrieves and removes the last element of this HashSet.
     * 
     * @return the last element of this HashSet.
     */
    public T removeLast() {
        T toReturn = tail.data;
        removeNode(tail);
        decrementSize(1);
        return toReturn;
    }

    /**
     * Inserts the specified element into the queue represented by this HashSet if
     * it is possible to do so (ie, if this item isn't already contained in the
     * set), returning true upon success.
     * 
     * @param item the item to add
     * 
     * @return {@code true} if item was successfully added to the set.
     */
    public boolean add(T item) {
        int oldSize = size;
        addLast(item);
        return size > oldSize;
    }

    /**
     * Returns a string representation of the object.
     * 
     * @return a string representation of the object.
     */
    public String toString() {
        if (size == 0)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[" + head.data);
        HashNode<T> cur = head.after;
        while (cur != null) {
            sb.append(", " + cur.data);
            cur = cur.after;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns {@code true} if this collection contains no elements.
     * 
     * @return {@code true} if this collection contains no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns an array containing all of the elements in this collection in the
     * correct order. The returned array's runtime component type is Object.
     * 
     * @return an array containing all of the elements in this collection in the
     *         correct order.
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    /**
     * Returns an array containing all of the elements in this collection in the
     * correct order; the runtime type of the returned array is that of the
     * specified array. If the collection fits in the specified array, it is
     * returned therein. Otherwise, a new array is allocated with the runtime type
     * of the specified array and the size of this collection.
     * 
     * @param a the array into which the elements of this collection are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * 
     * @return an array containing all of the elements in this collection in the
     *         correct order.
     */
    @Override
    public <A> A[] toArray(A[] a) {
        if (a.length >= size) {
            int index = 0;
            for (T item : this) {
                a[index] = (A) item;
                index++;
            }
            if (index < a.length)
                a[index] = null;
            return a;
        }
        return toArray((A[]) new Object[size]);
    }

    /**
     * Removes the specified element from this HashSet. If the HashSet does not
     * contain the element, it is unchanged. More formally, removes the element
     * {@code e} such that {@code Objects.equals(o, e)} (if such an element exists).
     * Returns {@code true} if this HashSet contained the specified element (or
     * equivalently, if this HashSet changed as a result of the call).
     * 
     * @param o element to be removed from this HashSet, if present
     * 
     * @return {@code true} if this HashSet contained the specified element.
     */
    @Override
    public boolean remove(Object o) {
        for (HashNode<T> node = set[hash(o)]; node != null; node = node.next) {
            if (node.data.equals(o)) {
                removeNode(node);
                decrementSize(1);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds all of the elements in the specified collection at the end of this
     * HashSet, as if by calling {@link addLast} on each one, in the order that they
     * are returned by the collection's iterator.
     * 
     * @param c the elements to be inserted into this HashSet
     * 
     * @return {@code true} if this HashSet changed as a result of the call.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = false;
        for (T item : c) {
            added = added || add(item);
        }
        return added;
    }

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection. After this call returns, this collection will contain
     * no elements in common with the specified collection.
     * 
     * @param c collection containing elements to be removed from this collection
     * 
     * @return {@code true} if this collection changed as a result of the call.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        int removed = 0;
        for (Object item : c) {
            if (remove(item))
                removed++;
        }
        decrementSize(removed);
        return removed > 0;
    }

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection. In other words, removes from this collection all of its
     * elements that are not contained in the specified collection.
     * 
     * @param c collection containing elements to be retained in this collection
     * 
     * @return {@code true} if this collection changed as a result of the call.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        int removed = 0;
        for (HashNode<T> cur = head; cur != null; cur = cur.after) {
            if (!c.contains(cur.data)) {
                removeNode(cur);
                removed++;
            }
        }
        decrementSize(removed);
        return removed > 0;
    }

    /**
     * Removes all of the elements from this collection. The collection will be
     * empty after this method returns.
     */
    @Override
    public void clear() {
        set = (HashNode<T>[]) new HashNode[16];
        size = 0;
        head = tail = null;
    }

    /**
     * Inserts the specified element at the front of this HashSet if it is possible
     * to do so (ie, if the item is not already contained in this HashSet).
     */
    @Override
    public void addFirst(T item) {
        if (item == null || get(item) != null)
            return;
        HashNode<T> newNode = new HashNode<T>(item);

        newNode.after = head;
        if (head != null)
            head.before = newNode;
        head = newNode;
        if (tail == null)
            tail = head;

        newNode.next = set[hash(item)];
        set[hash(item)] = newNode;
        size++;
        if (size * 2 > set.length)
            resize(set.length * 2);
    }

    /**
     * Inserts the specified element at the end of this HashSet if it is possible to
     * do so (ie, if the item is not already contained in this HashSet).
     */
    @Override
    public void addLast(T item) {
        if (item == null || get(item) != null)
            return;
        HashNode<T> newNode = new HashNode<T>(item);

        newNode.before = tail;
        if (tail != null)
            tail.after = newNode;
        tail = newNode;
        if (head == null)
            head = tail;

        newNode.next = set[hash(item)];
        set[hash(item)] = newNode;

        size++;
        if (size * 2 > set.length)
            resize(set.length * 2);
    }

    /**
     * Inserts the specified element at the front of this HashSet if it is possible
     * to do so (ie, if the item is not already contained in this HashSet).
     * 
     * @param item the element to add
     * 
     * @return {@code true} if the element was added to this HashSet, else
     *         {@code false}.
     */
    @Override
    public boolean offerFirst(T item) {
        int oldSize = size;
        addFirst(item);
        return size > oldSize;
    }

    /**
     * Equivalent to {@link #add(Object)}.
     * 
     * @param item the element to add
     * 
     * @return {@code true} if the element was added to this HashSet, else {@code false}.
     */
    @Override
    public boolean offerLast(T item) {
        return add(item);
    }

    /**
     * Equivalent to {@link removeFirst}.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T pollFirst() {
        return removeFirst();
    }

    /**
     * Equivalent to {@link removeLast}.
     * 
     * @return the last element of this HashSet.
     */
    @Override
    public T pollLast() {
        return removeLast();
    }

    /**
     * Equivalent to {@link getFirst}.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T peekFirst() {
        return getFirst();
    }

    /**
     * Equivalent to {@link getLast}.
     * 
     * @return the last element of this HashSet.
     */
    @Override
    public T peekLast() {
        return getLast();
    }

    /**
     * Equivalent to {@link #remove(Object)}
     * 
     * @param o element to be removed from this HashSet, if present
     * 
     * @return {@code true} if this HashSet contained the specified element.
     */
    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * Equivalent to {@link #remove(Object)}
     * 
     * @param o element to be removed from this HashSet, if present
     * 
     * @return {@code true} if this HashSet contained the specified element.
     */
    @Override
    public boolean removeLastOccurrence(Object o) {
        return remove(o);
    }

    /**
     * Equivalent to {@link #add(Object)}.
     * 
     * @param item the element to add
     * 
     * @return {@code true} if the element was added to this HashSet, else {@code false}.
     */
    @Override
    public boolean offer(T item) {
        return add(item);
    }

    /**
     * Equivalent to {@link removeFirst}.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T remove() {
        return removeFirst();
    }

    /**
     * Equivalent to {@link removeFirst}.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T poll() {
        return removeFirst();
    }

    /**
     * Equivalent to {@link getFirst}.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T element() {
        return getFirst();
    }

    /**
     * Equivalent to {@link getFirst}.
     * 
     * @return the first element of this HashSet.
     */
    @Override
    public T peek() {
        return getFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(T item) {
        addFirst(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T pop() {
        return removeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            HashNode<T> cur = tail;

            public boolean hasNext() {
                return cur != null;
            }

            public T next() {
                T toReturn = cur.data;
                cur = cur.before;
                return toReturn;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashSet<T> clone() {
        HashSet<T> hashSet = new HashSet<T>();
        for (T t : this) {
            hashSet.add(t);
        }
        return hashSet;
    }
}