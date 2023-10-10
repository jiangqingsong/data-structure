package com.me.dataStructure.linkedlist;

/**
 * @program TrainSquare
 * @description: 单链表
 * @author: JiangQS
 * @create: 2023/10/10 10:59
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByOrder(new HeroNode(1, "宋江"));
        linkedList.addByOrder(new HeroNode(3, "吴用"));
        linkedList.addByOrder(new HeroNode(3, "吴用"));
        linkedList.addByOrder(new HeroNode(2, "曹盖"));
        linkedList.addByOrder(new HeroNode(8, "李逵"));
        linkedList.addByOrder(new HeroNode(4, "杨志"));

        linkedList.list();

        System.out.println("------修改||删除某节点------");

//        linkedList.updateByHeroNo(new HeroNode(4, "青面兽-杨志"));
        linkedList.delete(3);
        linkedList.list();
        System.out.println("------获取链表有效节点数------");
        System.out.println(getLength(linkedList.getHead()));


        System.out.println("------获取倒数第K个节点------");
        System.out.println(getLastIndexNode(linkedList.getHead(), 2));

        System.out.println("------ 反转单链表------");
        HeroNode reversedHead = reverseLinkedList(linkedList.getHead());
        list(reversedHead);

        System.out.println("------ 合并两个链表，保持有序 ------");
        SingleLinkedList linkedList1 = new SingleLinkedList();
        linkedList1.addByOrder(new HeroNode(1, "宋江"));
        linkedList1.addByOrder(new HeroNode(3, "吴用"));
        linkedList1.addByOrder(new HeroNode(2, "曹盖"));

        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.addByOrder(new HeroNode(8, "李逵"));
        linkedList2.addByOrder(new HeroNode(4, "杨志"));

        list(mergeLinkedList(linkedList1.getHead(), linkedList2.getHead()));

    }

    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2){

        //初始化一个节点作为merge后的head
        HeroNode mergedHead = new HeroNode(0, "");
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode temp = mergedHead;

        while (true){

            //当两个链表都没有遍历完时，每次只取两者最小的一个加入新链表的末尾
            if(cur1 != null && cur2 != null){
                if(cur1.hNo < cur2.hNo){
                    temp.next = cur1;
                    temp = cur1;
                    cur1 = cur1.next;
                    continue;
                }else {
                    temp.next = cur2;
                    temp = cur2;
                    cur2 = cur2.next;
                    continue;
                }
            }

            //只剩任意一个就依次放入新链表末尾
            if(cur1 != null){
                temp.next = cur1;
                temp = cur1;
                cur1 = cur1.next;
            }

            if(cur2 != null){
                temp.next = cur2;
                temp = cur2;
                cur2 = cur2.next;
            }

            if(cur1 == null && cur2 == null){
                break;
            }

        }

        return mergedHead;
    }

    /**
     * 单链表反转
     * @param head
     */
    public static HeroNode reverseLinkedList(HeroNode head){

        HeroNode cur = head.next;
        if(cur == null){
            return null;
        }
        HeroNode newHead = new HeroNode(0, "");//表示反转后链表的头
        HeroNode temp = null;//临时节点
        while (true){
            HeroNode next = cur.next;
            //当前节点的下个节点指向temp
            cur.next = temp;
            //移动临时节点到当前位置
            temp = cur;
            cur = next;

            if(cur == null){
                newHead.next = temp;
                break;
            }
        }
        return newHead;
    }

    /**
     * 找到倒数第index个节点
     * @param head
     * @param index
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode head, int index){
        //遍历拿到总length
        int length = getLength(head);
        int n = 0;
        if (index > length){
            System.out.println("超出链表有效长度！");
            return null;
        }
        HeroNode cur  = head.next;
        while (true){
            //找到倒数第index个节点
            if(length - n == index){
                return cur;
            }
            n++;
            cur = cur.next;
        }
    }
    /**
     * 获取单链表的有效节点数，不包含头节点
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        int length = 0;
        HeroNode cur = head.next;
        if (cur == null) {
            length = 0;
        }

        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    public static void list(HeroNode head) {
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}


class SingleLinkedList {
    //初始化头结点，头结点不动它

    private HeroNode head = new HeroNode(0, "");
    private HeroNode rear = head;

    public HeroNode getHead() {
        return head;
    }

    /**
     * 往链表尾部无脑添加节点
     *
     * @param node
     */
    public void add(HeroNode node) {
        rear.next = node;
        rear = node;
    }

    /**
     * 按顺序添加节点
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head; //遍历的当前节点
        while (true) {
            HeroNode next = temp.next;

            if (temp.hNo == node.hNo) {
                System.out.printf("编号%d已存在，不允许再添加！\n", node.hNo);
                break;
            }

            //如果是链表末尾，则直接往后加
            if (next == null) {
                temp.next = node;
                break;
            }


            if (node.hNo > temp.hNo && node.hNo < next.hNo) {
                temp.next = node;
                node.next = next;
                break;
            }

            temp = next;
        }
    }


    /**
     * 通过编号修改节点信息
     *
     * @param node
     */
    public void updateByHeroNo(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空，无法进行修改！");
            return;
        }
        HeroNode temp = head.next;
        while (true) {

            HeroNode next = temp.next;

            if (next == null) {
                System.out.println("没找到对应节点，无法进行修改！");
                break;
            }

            if (temp.hNo == node.hNo) {
                temp.name = node.name;
                break;
            }
            temp = next;

        }
    }

    public void delete(int no) {
        HeroNode preNo = head;
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("链表为空！");
                break;
            }

            HeroNode next = temp.next;

            if (temp.hNo == no) {
                preNo.next = next;
                temp.next = null;
                break;
            }
            preNo = temp;
            temp = next;
        }
    }

    /**
     * 遍历链表元素
     */
    public void list() {
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

/**
 * 节点数据实体类
 */
class HeroNode {
    public int hNo;
    public String name;
    public HeroNode next;

    public HeroNode(int hNo, String name) {
        this.hNo = hNo;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "hNo=" + hNo +
                ", name='" + name + '\'' +
                '}';
    }
}

