package com.learning.compress;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther wang.zhc
 * @Date 2020/1/9 09:26
 * @Description
 */
public class CompressFactory {
    private CompressFactory(){};

    private static final CompressFactory INSTANCE = new CompressFactory();

    public enum CompressType{
        BZIP2, DEFLATE, LZ4, LZO, SNAPPY
    }

    private static final ICompress BZIP2 = new Bzip2();
    private static final ICompress DEFLATE = new DeflateCompress();
    private static final ICompress LZ4 = new Lz4Compress();
    private static final ICompress LZO = new LzoCompress();
    private static final ICompress SNAPPY = new SnappyCompress();

    private static final Map<CompressType, ICompress> CONTAINER = new HashMap<>();
    static {
        CONTAINER.put(CompressType.BZIP2, BZIP2);
        CONTAINER.put(CompressType.DEFLATE, DEFLATE);
        CONTAINER.put(CompressType.LZ4, LZ4);
        CONTAINER.put(CompressType.LZO, LZO);
        CONTAINER.put(CompressType.SNAPPY, SNAPPY);
    }

    public static CompressFactory getInstance(){
        return INSTANCE;
    }

    public ICompress getCompress(CompressType type){
        return CONTAINER.get(type);
    }
}
