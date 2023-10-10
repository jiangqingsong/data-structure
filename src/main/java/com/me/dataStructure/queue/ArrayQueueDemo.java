package com.me.dataStructure.queue;

import java.util.Scanner;

/**
 * @program TrainSquare
 * @description: 队列训练:
 *
 * @author: JiangQS
 * @create: 2023/10/10 09:30
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key;

        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 向队列添加元素");
            System.out.println("g(get): 获取队首元素");
            System.out.println("h(head): 查看队首元素");

            key = scanner.next().charAt(0);
            switch (key){
                case 's': queue.showQueue(); break;
                case 'a':
                    System.out.println("输入添加数据");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int e = queue.getQueue();
                        System.out.println("取数为：" + e);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.println("队首元素为： " + head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出.");
            }
        }

    }
}

/**
 * 该队列存在一个问题： 只能使用一次，不能重复使用   ->  使用环形队列
 */
class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;//模拟队列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;// 指向队首，并不包含
        rear = -1;//指向队尾，包含
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }


    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int e){
        if(isFull()){
            System.out.println("队列已满，不能加入！");
            return;
        }
        rear ++;
        arr[rear] = e;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }

        return arr[front + 1];
    }


}
