package serialize;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 解码
 * @author liqqc
 */
public class RpcByteToMessageDecode extends ByteToMessageDecoder {

    private Class<?> clazz;

    public RpcByteToMessageDecode(Class<?> c) {
        this.clazz = c;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();
        int len = in.readInt();
        if (in.readableBytes() < len) {
            in.resetReaderIndex();
            return;
        }
        byte[] datas = new byte[len];
        in.readBytes(datas);

        Object obj = SerializedUtils.jsonDecode(datas, clazz);
        out.add(obj);
    }

}
