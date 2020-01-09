package com.learning.compress;

import java.io.IOException;

/**
 * 压缩以及解压缩接口
 */
public interface ICompress {

    byte[] compress(byte[] data) throws Exception;

    byte[] uncompress(byte[] data) throws Exception;
}
