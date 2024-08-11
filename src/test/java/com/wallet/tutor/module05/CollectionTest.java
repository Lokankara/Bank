package com.wallet.tutor.module05;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class CollectionTest {
    public void dumpCollection(Collection<?> c) {
        log.info("Collection has" + c.size() +
                " elements.");
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            Object elem = iter.next();
            log.info("Next element is" + elem);
        }
    }
    void addTwice(Set<Point> set) {
        set.clear();
        Point p1 = new Point(10, 20);
        Point p2 = new Point(10, 20);
        set.add(p1);
        set.add(p2);
        log.info(String.valueOf(set.size()));
    }

    private class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
