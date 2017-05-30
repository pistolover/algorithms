package netty.example2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ClientHandler02 extends SimpleChannelInboundHandler{
    private ByteBuf byteBuf;
    public ClientHandler02(){
        byte[] bytes = "I am ClientHandler02".getBytes();
        byteBuf = Unpooled.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
    }
    
    
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("ClientHandler02 channelActive");
        ctx.writeAndFlush(byteBuf);
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bfBuf  = (ByteBuf) msg;
        byte[] bs = new byte[bfBuf.readableBytes()];
        bfBuf.readBytes(bs);
        
        System.err.println("ClientHandler02 channelRead: " + new String(bs));
    }
}
