package GAME_SERVICES;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GameService extends ChannelInboundHandlerAdapter {
	/**
	 * 保存所有通道
	 */
	private static ChannelGroup allChannels = new DefaultChannelGroup(
			GlobalEventExecutor.INSTANCE);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		allChannels.add(ctx.channel());
		super.channelActive(ctx);
	}
	
	public static ChannelGroup getAllChannels() {
		return allChannels;
	}

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	//System.out.println("我所有的一切都是为了你。。。。。。。。。。。。。。。。。。。");
    } 
    
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();
    } 
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.getCause().printStackTrace();
        ctx.channel().close();
    } 
}
