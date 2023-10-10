package com.me.dataStructure.queue;

import java.util.Scanner;

/**
 * @program TrainSquare
 * @description: 环形队列
 * @author: JiangQS
 * @create: 2023/10/10 10:01
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);

        char key;

        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 向队列添加元素");
            System.out.println("g(get): 获取队首元素");
            System.out.println("h(head): 查看队首元素");
            System.out.println("f(front and rear): 查看队首和队尾下标");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入添加数据");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int e = queue.getQueue();
                        System.out.println("取数为：" + e);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.println("队首元素为： " + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'f':
                    queue.showFrontAndRear();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出.");
            }
        }
    }
}

class CircleArrayQueue {
    private int maxSize;
    private int front; // 指向队列头
    private int rear; // 指向队尾后一个元素
    private int[] arr;//模拟队列

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }


    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int e) {
        if (isFull()) {
            System.out.println("队列已满，不能加入！");
            return;
        }
        arr[rear] = e;
        //将rear后移，考虑取模
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数");
        }
        //front指向队首元素
        int head = arr[front];

        front = (front + 1) % maxSize;

        return head;
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据！");
        }

        return arr[front];
    }
    public void  showFrontAndRear(){
        System.out.printf("front=%d, rear=%d", front, rear);
    }
}
