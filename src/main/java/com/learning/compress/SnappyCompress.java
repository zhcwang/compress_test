package com.learning.compress;

import org.xerial.snappy.Snappy;

import java.io.IOException;

/**
 * @Auther wang.zhc
 * @Date 2020/1/9 09:12
 * @Description
 */
public class SnappyCompress implements ICompress {
    public byte[] compress(byte[] data) throws Exception {
        return Snappy.compress(data);
    }

    public byte[] uncompress(byte[] data) throws Exception {
        return Snappy.uncompress(data);
    }
}
