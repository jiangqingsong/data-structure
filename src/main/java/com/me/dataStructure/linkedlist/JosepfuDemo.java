package com.me.dataStructure.linkedlist;

/**
 * @program TrainSquare
 * @description: 单向环形链表：约瑟夫环问题
 * @author: JiangQS
 * @create: 2023/10/10 17:25
 */
public class JosepfuDemo {

    public static void main(String[] args) {
        int boys = 5;
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(boys);
        linkedList.list();

        System.out.println("------------");
        /*linkedList.deleteBoy(1);
        linkedList.deleteBoy(2);
        linkedList.deleteBoy(3);
        linkedList.deleteBoy(4);
        linkedList.deleteBoy(5);*/
        linkedList.list();
        System.out.println("------------");
        linkedList.countBoy(2, 2, boys);
    }

}


class CircleSingleLinkedList{
    private Boy first;

    public Boy getFirst() {
        return first;
    }


    /**
     * 约瑟夫出圈问题
     * @param startNo 从第几个开始报数
     * @param countNum 每次报数次数
     * @param nums 总的小孩数
     */
    public void countBoy(int startNo, int countNum, int nums){
        if(startNo < 0 || countNum < 1 || startNo > nums){
            System.out.println("输入有误！");
            return;
        }

        //让temp移动到first前一个（最后一个） --- 当temp和first相等时，就说明只有最后一个boy
       /* Boy helper = first;
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        //先将first移动到startNum
        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始数数出圈操作
        while (true){
            if(helper == first){ // 表示只有最后一个
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getNo());*/

        /*
        //方法2：（效率不高，每次都要遍历做删除）
        Boy cur = first;

        for (int i = 0; i < startNo-1; i++) {
            cur = cur.getNext();
        }
        while (true){
            for (int i = 0; i < countNum-1; i++) {
                cur = cur.getNext();
            }
            int curNo = cur.getNo();
            //在删除前移动到next
            cur =cur.getNext();
            deleteBoy(curNo);
            System.out.println(curNo);
            if(cur.getNext() == cur){
                break;
            }
        }
        System.out.println(cur.getNo());*/
    }
    public void deleteBoy(int n){
        Boy cur = first;
        while (true){
            Boy next = cur.getNext();
            if(next.getNext() == next){
                first = null;
                break;
            }
            if(next.getNo() == n){
                if(next == first){
                    first = next.getNext();
                }

                cur.setNext(next.getNext());
                break;
            }
            if(next == first){
                System.out.println("没找到需要删除的节点");
                break;
            }
            cur = cur.getNext();
        }
    }
    //添加
    public void addBoy(int nums){
        if (nums < 1) {
            System.out.println("添加数值不正确");
            return;
        }
        Boy curBoy = null;//帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
                continue;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }
    }

    public void list(){
        Boy curBoy = first;
        if(curBoy == null){
            return;
        }
        if(curBoy.getNext() == first){
            System.out.println(curBoy);
            return;
        }

        while (true){
            System.out.println(curBoy);
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
}
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
