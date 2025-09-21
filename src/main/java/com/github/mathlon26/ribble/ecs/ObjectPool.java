package com.github.mathlon26.ribble.ecs;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class ObjectPool<T> implements Iterable<T> {
    protected final Set<T> m_pool = new LinkedHashSet<>();

    public ObjectPool() {}

    public boolean add(T obj) {
        return m_pool.add(obj);
    }

    public boolean remove(T obj) {
        return m_pool.remove(obj);
    }

    public boolean contains(T obj) {
        return m_pool.contains(obj);
    }

    public int size() {
        return m_pool.size();
    }

    public void clear() {
        m_pool.clear();
    }

    public void forEach(Consumer<? super T> action) {
        m_pool.forEach(action);
    }

    public Set<T> getAll() {
        return Collections.unmodifiableSet(m_pool);
    }

    public Stream<T> stream() {
        return m_pool.stream();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return m_pool.iterator();
    }

    @Override
    public String toString() {
        return "ObjectPool{" + "size=" + size() + '}';
    }
}
