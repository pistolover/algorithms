package netty.example2;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import serialize.jackson.JsonUtil;

public class DecodeHandler extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int readInt = in.readInt();
        if(readInt < 0 ) {
            ctx.close();
        }
        if(readInt > in.readableBytes() ) {
            in.resetReaderIndex();
        }
        
        byte[] bytes = new byte[readInt];
        in.readBytes(bytes);
        String readValue = new String(bytes);
        ctx.write(readValue);
    }
}
