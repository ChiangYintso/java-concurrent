package pers.jiangyinzuo.study.concurrent.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author Jiang Yinzuo
 */
public class ThreadMXBeanDetection {
    public static void main(String[] args) throws InterruptedException {
        MustDeadLock2.main(args);
        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null) {
            for (long deadlockedThread : deadlockedThreads) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThread);
                System.out.println(threadInfo.getThreadName());
            }
        }
    }
}
