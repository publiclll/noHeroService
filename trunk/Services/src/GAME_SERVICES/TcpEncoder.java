package GAME_SERVICES;

import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class TcpEncoder extends MessageToByteEncoder<ByteBuf> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ByteBuf byteBuf, ByteBuf out)
			throws Exception {
		//直接返回到客户端
        out.writeBytes(byteBuf.array());
        ctx.flush();
	}

}
