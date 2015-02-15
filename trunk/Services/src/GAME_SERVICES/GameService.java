package GAME_SERVICES;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GameService extends ChannelInboundHandlerAdapter {
	/**
	 * ��������ͨ��
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
    	//System.out.println("�����е�һ�ж���Ϊ���㡣������������������������������������");
    } 
    
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();
    } 
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.getCause().printStackTrace();
        ctx.channel().close();
    } 
}
