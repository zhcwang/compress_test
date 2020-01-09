package com.learning.test;

import com.learning.compress.CompressFactory;
import com.learning.compress.ICompress;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther wang.zhc
 * @Date 2020/1/9 09:06
 * @Description
 */
public class CompressTest {

    private static final File TEST_FILE = new File("E:\\source\\source_compress\\src\\test\\resources\\test.json");
    private static final CompressFactory FACTORY = CompressFactory.getInstance();

    public static void testCompress(ICompress compressUtil) throws Exception {
        FileInputStream fis = new FileInputStream(TEST_FILE);
        FileChannel channel = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate((int) channel.size());
        channel.read(bb);
        byte[] beforeBytes = bb.array();

        int times = 2000;
        System.out.println("压缩前大小：" + beforeBytes.length + " bytes");
        long startTime1 = System.currentTimeMillis();
        byte[] afterBytes = null;
        for (int i = 0; i < times; i++) {
            afterBytes = compressUtil.compress(beforeBytes);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("压缩后大小：" + afterBytes.length + " bytes");
        System.out.println("压缩次数：" + times + "，时间：" + (endTime1 - startTime1)
                + "ms");

        byte[] resultBytes = null;
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            resultBytes = compressUtil.uncompress(afterBytes);
        }
        System.out.println("解压缩后大小：" + resultBytes.length + " bytes");
        long endTime2 = System.currentTimeMillis();
        System.out.println("解压缩次数：" + times + "，时间：" + (endTime2 - startTime2) + "ms");
    }

    @Test
    public void testBzip2() throws Exception{
        System.out.println("--------------------------BZIP2------------------------------");
        testCompress(FACTORY.getCompress(CompressFactory.CompressType.BZIP2));
    }

    @Test
    public void testDeflate() throws Exception{
        System.out.println("--------------------------DEFLATE------------------------------");
        testCompress(FACTORY.getCompress(CompressFactory.CompressType.DEFLATE));
    }

    @Test
    public void testLZ4() throws Exception{
        System.out.println("--------------------------LZ4------------------------------");
        testCompress(FACTORY.getCompress(CompressFactory.CompressType.LZ4));
    }

    @Test
    public void testLZO() throws Exception{
        System.out.println("--------------------------LZO------------------------------");
        testCompress(FACTORY.getCompress(CompressFactory.CompressType.LZO));
    }

    @Test
    public void testSnappy() throws Exception{
        System.out.println("--------------------------SNAPPY------------------------------");
        testCompress(FACTORY.getCompress(CompressFactory.CompressType.SNAPPY));
    }

}
