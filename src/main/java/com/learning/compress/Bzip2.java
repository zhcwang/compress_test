package com.learning.compress;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Auther wang.zhc
 * @Date 2020/1/9 09:22
 * @Description
 */
public class Bzip2 implements ICompress {
    public byte[] compress(byte[] data) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(out);
        bcos.write(data);
        bcos.close();
        return out.toByteArray();

    }

    public byte[] uncompress(byte[] data) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(in);
        byte[] buffer = new byte[2048];
        int n;
        while ((n = ungzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();

    }
}
