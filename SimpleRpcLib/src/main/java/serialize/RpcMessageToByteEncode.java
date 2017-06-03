package serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码 对象-->字符数字
 * @author liqqc
 */
public class RpcMessageToByteEncode extends MessageToByteEncoder<Object> {
    private Class<?> clazz;

    public RpcMessageToByteEncode(Class<?> c) {
        this.clazz = c;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (clazz != null & clazz.isInstance(msg)) {
            byte[] datas = SerializedUtils.jsonEncode(msg);
            if (datas != null && datas.length != 0) {
                out.writeInt(datas.length);
                out.writeBytes(datas);
            }
        }
    }

}
