package com.me.dataStructure.linkedlist;

/**
 * @program TrainSquare
 * @description: 双向链表
 * @author: JiangQS
 * @create: 2023/10/10 16:20
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(new HeroNode2(1, "曹盖"));
        doubleLinkedList.addNode(new HeroNode2(2, "宋江"));
        doubleLinkedList.addNode(new HeroNode2(3, "吴用"));
        //新增
        doubleLinkedList.list();
        System.out.println("--------------");

        doubleLinkedList.updateNode(new HeroNode2(2, "及时雨-宋江"));
        doubleLinkedList.list();

        System.out.println("--------------");

        doubleLinkedList.deleteNode(2);
        doubleLinkedList.list();


    }
}
class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "");
    private HeroNode2 rear = head;

    public HeroNode2 getHead() {
        return head;
    }

    public void deleteNode(int n){
        HeroNode2 cur = head.next;
        if(cur == null){
            System.out.println("空链表，无法删除！");
            return;
        }

        while (true){
            if(cur.hNo == n){
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
                break;
            }
            if(cur.next == null){
                System.out.println("没有对应节点需要删除！");
                break;
            }
            cur = cur.next;
        }
    }


    public void updateNode(HeroNode2 node){
        HeroNode2 cur = head.next;
        if(cur == null){
            System.out.println("空链表，无法修改！");
            return;
        }

        while (true){
            if(cur.hNo == node.hNo){
                cur.pre.next = node;
                cur.next.pre = node;
                node.pre = cur.pre;
                node.next = cur.next;
                break;
            }
            if(cur.next == null){
                System.out.println("没有对应节点需要修改！");
                break;
            }
            cur = cur.next;
        }
    }

    public void addNode(HeroNode2 node){
        this.rear.next = node;
        node.pre = rear;
        this.rear = node;
    }

    public void list(){
        HeroNode2 cur = head.next;
        while (cur != null){
            System.out.println(cur);
            cur = cur.next;
        }
    }
}


class HeroNode2{
    public int hNo;
    public String name;
    public HeroNode2 pre;
    public HeroNode2 next;

    public HeroNode2(int hNo, String name) {
        this.hNo = hNo;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "hNo=" + hNo +
                ", name='" + name + '\'' +
                '}';
    }
}
