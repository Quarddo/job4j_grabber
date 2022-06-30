package ru.job4j.gc.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class PhantomDemo {
    /**
     * Фантомный тип ссылок имеет две особенности:
     *
     * 1) Метод get() всегда возвращает null, поэтому доступ можно осуществить только через ReferenceQueue
     *
     * 2) PhantomReference попадает в ReferenceQueue только после выполнения finalize(), что значит мы еще имеем
     * доступ к объекту некоторое время.
     *
     * Данный тип ссылки используется для более гибкого управления удалением объектов, минуя минусы finalize()
     * (будут описаны в статье по первой ссылки)
     */

    private static class MyPhantom extends PhantomReference<String> {

        private String name;

        public MyPhantom(String referent, ReferenceQueue<? super String> q, String name) {
            super(referent, q);
            this.name = name;
        }

        @Override
        public String get() {
            return name;
        }
    }

    private static class PhantomStorage {

        private ReferenceQueue<String> queue = new ReferenceQueue<>();

        private List<MyPhantom> phantoms = new LinkedList<>();

        public void add(String someData) {
            MyPhantom phantom = new MyPhantom(someData, queue, "my ref");
            phantoms.add(phantom);
        }

        public void utilizeResource() {
            for (ListIterator<MyPhantom> i = phantoms.listIterator(); i.hasNext();) {
                MyPhantom current = i.next();
                if (current != null && current.isEnqueued()) {
                    System.out.println("Utilized " + current.get());
                    current.clear();
                    i.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PhantomStorage storage = new PhantomStorage();
        String data = "123".repeat(1000);
        storage.add(data);
        data = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        storage.utilizeResource();
    }
}
/**
 * Для начала создаем класс наследующийся от PhantomReference и переопределяем get(), чтобы
 * проконтролировать что удаляется наш ресурс.
 *
 * Создаем хранилище. В нем есть очередь, которая необходима для ссылок.  Но эта очередь read-only, поэтому
 * создаем свой список и в него помещаем наши фантомные ссылки. Когда вызывает метод для утилизации ресурсов
 * мы проверяем если ссылка в очереди, т.е. помечен ли объект на удаление. Далее вызываем явно метод clear(),
 * чтобы указать GC, что нужно удалить объект в будущем и удаляем из нашего списка.
 */

/**
 * ReferenceQueue
 *
 * Все типы ссылок, за исключением сильных, в Java являются наследниками класса Reference. Все его наследники всегда попадают в ReferenceQueue,
 *
 * это может происходить как явно (мы можем задать свою очередь) или неявно (когда мы не задаем). В нее попадают ссылки тех объектов, которые уже помечены на удаление.
 *
 * Особенности ссылок WeakReference и PhantomReference (будет рассмотрена позже) связаны с применением очереди ссылок.
 *
 * Что касается особенности WeakReference, так это то, что когда на объект уже нет сильных или безопасных ссылок происходит за'null'ение слабой ссылки, как в примере выше.
 *
 * Далее WearReference будет помещена в очередь ReferenceQueue и мы можем пока объект не удален физически получить его из этой очереди.
 *
 * private static void example3() throws InterruptedException {
 *     Object object = new Object() {
 *         @Override
 *         protected void finalize() throws Throwable {
 *             System.out.println("Removed");
 *         }
 *     };
 *     ReferenceQueue<Object> queue = new ReferenceQueue<>();
 *     WeakReference<Object> weak = new WeakReference<>(object, queue);
 *     object = null;
 *
 *     System.gc();
 *
 *     TimeUnit.SECONDS.sleep(3);
 *     System.out.println("from link " + weak.get());
 *     System.out.println("from queue " + queue.poll());
 * }
 */
