package com.me.dataStructure.linkedlist;

/**
 * @program TrainSquare
 * @description: 单链表，无排序
 * @author: JiangQS
 * @create: 2023/10/10 10:59
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new HeroNode(1, "宋江"));
        linkedList.add(new HeroNode(3, "吴用"));
        linkedList.add(new HeroNode(2, "曹盖"));

        linkedList.list();
    }

}


class SingleLinkedList{
    //初始化头结点，头结点不动它

    private HeroNode head = new HeroNode(0, "");
    private HeroNode rear = head;

    public void add(HeroNode node){
        rear.next = node;
        rear = node;
    }

    public void list(){
        HeroNode temp = head.next;
        while (true){
            System.out.println(temp);
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }

}
class HeroNode {
    private int hNo;
    private String name;
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

