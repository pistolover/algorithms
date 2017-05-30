package netty.example2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import serialize.jackson.JsonUtil;

public class EncodeHandler extends MessageToByteEncoder{

    public EncodeHandler() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] writeValueAsBytes = JsonUtil.OBJECT_MAPPER.writeValueAsBytes(msg);
        out.writeInt(writeValueAsBytes.length);
        out.writeBytes(writeValueAsBytes);
    }
    
}
