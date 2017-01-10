package nio.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        //分配空间 隐含地在内存中分配了一个byte型数组来存储20个byte
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);

        //填充元素 buffer.hasRemaining()用于判断缓冲区是否达到上界limit
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            byteBuffer.put((byte) i++);
        }
        
        //翻转缓冲区 将缓冲区进行翻转操作，即在缓冲区写入完毕时，将缓冲区翻转成一个准备读出元素的状态
        byteBuffer.flip();

        //读取缓冲区
        int remainCount = byteBuffer.remaining();
        for (int j = 0; j < remainCount; j++) {
            System.out.print(byteBuffer.get() + " ");
            //byteBuffer.clear(); 清除整个缓冲区数据
            //byteBuffer.compact(); 清除已读的缓冲区数据
        }
        
        System.out.println();
        System.out.println();

        // 字节顺序
        System.out.println("ByteOrder的字节顺序为：" + ByteOrder.nativeOrder());
        System.out.println("ByteBuffer的字节顺序为：" + byteBuffer.order());
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("CharBuffer的字节顺序为：" + charBuffer.order());
        // 修改ButyBuffer的字节顺序
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println("ByteBuffer的字节顺序为：" + byteBuffer.order());

        //只有ByteBuffer可以创建直接缓冲区，用wrap函数创建的缓冲区都是非直接的缓冲区
        ByteBuffer redirectByteBuffer = ByteBuffer.allocateDirect(10);
        System.out.println("判断缓冲区是否为直接缓冲区：" + redirectByteBuffer.isDirect());

        System.out.println();
        
        // 先创建一个大端字节顺序的ByteBuffer，然后再创建一个字符视图缓冲区
        ByteBuffer bigByteBuffer = ByteBuffer.allocate(50).order(ByteOrder.BIG_ENDIAN);
        CharBuffer viewCharBuffer = bigByteBuffer.asCharBuffer();
        viewCharBuffer.put("how do you do ?");
        //在字符视图的基础上创建只读字符视图,只能读而不能写，否则抛出ReadOnlyBufferException
        CharBuffer onlyReadCharBuffer = viewCharBuffer.asReadOnlyBuffer();
//        onlyReadCharBuffer.put("aaa");   Exception in thread "main" java.nio.ReadOnlyBufferException
//        System.err.println(onlyReadCharBuffer.get(1));
        
        
        viewCharBuffer.flip();
        System.out.println("asCharBuffer()--->position=" + viewCharBuffer.position() + ",limit=" + viewCharBuffer.limit());
        while (viewCharBuffer.hasRemaining()) {
            System.out.print((char) viewCharBuffer.get());
        }

        System.out.println();
        System.out.println();
        System.out.println();
        
        //创建一个与原始缓冲区相似的新缓冲区，两个缓冲区共享数据元素，拥有同样的容量，但每个缓冲区拥有各自的位置、上界、标记属性。 对一个缓冲区的数据元素所做的改变会反映在另一个缓冲区上，新的缓冲区会继承原始缓冲区的这些属性。
        CharBuffer copyCharBuffer = viewCharBuffer.duplicate();
        System.out.println("duplicate()--->position=" + copyCharBuffer.position() + ",limit=" + copyCharBuffer.limit());
        copyCharBuffer.position(2);
        while (copyCharBuffer.hasRemaining()) {
            System.out.print((char) copyCharBuffer.get());
        }

        System.out.println();
        System.out.println();
        System.out.println();
        
        //创建原始缓冲区子集的新缓冲区,新缓冲区的内容将从该缓冲区的当前位置开始。对这个缓冲区的内容做出的改变将在反映在新的缓冲区上,可见,反之亦然;这两个缓冲区的位置、限制和标记值将是独立的。
        viewCharBuffer.position(1);
        CharBuffer cutCharBuffer = viewCharBuffer.slice();
        System.out.println("slice()--->position=" + cutCharBuffer.position() + ",limit=" + cutCharBuffer.limit()+",capacity="+cutCharBuffer.capacity());
        while (cutCharBuffer.hasRemaining()) {
            System.out.print((char) cutCharBuffer.get());
        }
        System.out.println();
        System.out.println();
        System.out.println();
        
    }
}