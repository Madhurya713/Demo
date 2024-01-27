package com.example.demo.utils;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class SimpleBufferPool implements BufferPool {
    private final LinkedList<ByteBuffer> availableBuffers = new LinkedList<>();
    private final int bufferSize;
    private final int poolSize;

    public SimpleBufferPool(int bufferSize, int poolSize) {
        this.bufferSize = bufferSize;
        this.poolSize = poolSize;
    }

    @Override
    public synchronized ByteBuffer borrowBuffer() {
        if (!availableBuffers.isEmpty()) {
            return availableBuffers.removeFirst();
        }
        return ByteBuffer.allocate(bufferSize);
    }

    @Override
    public synchronized void returnBuffer(ByteBuffer buffer) {
        if (buffer != null && availableBuffers.size() < poolSize) {
            buffer.clear(); // Reset the buffer for reuse
            availableBuffers.addFirst(buffer);
        }
    }
}

