package com.example.demo.utils;

import java.nio.ByteBuffer;

public interface BufferPool {
    ByteBuffer borrowBuffer();
    void returnBuffer(ByteBuffer buffer);
}
