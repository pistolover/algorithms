package netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler2 extends ChannelInboundHandlerAdapter {

    private final ByteBuf buteBuf;

    public TimeClientHandler2() {
        byte[] req = "kkkdsdk".getBytes();
        buteBuf = Unpooled.buffer(req.length);
        buteBuf.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channelActive22222222..");
        ctx.writeAndFlush(buteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println("handler22222 ... :" + body);
    }

}
