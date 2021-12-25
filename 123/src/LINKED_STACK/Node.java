package LINKED_STACK;

class Node<E>
{
    protected E data;
    protected Node<E> link;
    public Node(){}
    public Node(E initialData, Node initialLink)
    {
        data = initialData;
        link = initialLink;
    }
    public void addNodeAfter(E element)
    {
        link = new Node(element, link);
    }
    public E getData( )
    {
        return data;
    }
    public Node getLink( )
    {
        return link;
    }
    public static Node listCopy(Node source)
    {
        Node copyHead;
        Node copyTail;

        if (source == null)
            return null;

        copyHead = new Node(source.data, null);
        copyTail = copyHead;

        while (source.link != null)
        {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }
        return copyHead;
    }
    public static Node[ ] listCopyWithTail(Node source)
    {

        Node copyHead;
        Node copyTail;
        Node[ ] answer = new Node[2];

        if (source == null)
            return answer;
        copyHead = new Node(source.data, null);
        copyTail = copyHead;

        while (source.link != null)
        {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }
    public static int listLength(Node head)
    {
        Node cursor;
        int answer;
        answer = 0;
        for (cursor = head; cursor != null; cursor = cursor.link)
            answer++;
        return answer;
    }
    public static Node[ ] listPart(Node start, Node end)
    {

        Node copyHead;
        Node copyTail;
        Node[ ] answer = new Node[2];

        if (start == null)
            throw new IllegalArgumentException("start is null");
        if (end == null)
            throw new IllegalArgumentException("end is null");
        copyHead = new Node(start.data, null);
        copyTail = copyHead;

        while (start != end)
        {
            start = start.link;
            if (start == null)
                throw new IllegalArgumentException
                        ("end node was not found on the list");
            copyTail.addNodeAfter(start.data);
            copyTail = copyTail.link;
        }
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }
    public Node listPosition(Node head, int position)
    {
        Node cursor;
        int i;
        if (position <= 0)
            throw new IllegalArgumentException("position is not positive.");
        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++)
            cursor = cursor.link;
        return cursor;
    }
    public Node listSearch(Node head, E target)
    {
        Node cursor;
        for (cursor = head; cursor != null; cursor = cursor.link)
            if (target == cursor.data)
                return cursor;
        return null;
    }
    public void removeNodeAfter( )
    {
        link = link.link;
    }
    public void setData(E newData)
    {
        data = newData;
    }
    public void setLink(Node newLink)
    {
        link = newLink;
    }
}
