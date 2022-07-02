package ru.job4j.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Test {
    public static void main(String[] args) {
        Object reference = new Object();
        SoftReference<Object> softReference = new SoftReference<>(reference);
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(reference);
        reference = null;
        System.gc();

        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        /**
         * SoftReference - будет удален при заполнении памяти;
         * WeakReference - будет удален при первой сборке мусора;
         * При использовании SoftReference и WeakReference одновременно, WeakReference не удалится
         * пока ссылка SoftReference не станет null;
         *
         * Так же объект можно будет временно получить из очереди referenceQueue, пока объект не будет удален физически.
         * С помощью referenceQueue.poll()
         */
    }
}
