package GAME_SERVICES;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务启动
 * 
 * @author publiclll
 *
 */
public class Bootstrap {

	/** 端口 */
	private static final int PORT = 1080;
	
	private static final Runtime runtime = Runtime.getRuntime();

	/** 用于分配处理业务线程的线程组个数 */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime()
			.availableProcessors() * 2; // 默认

	/** 业务出现线程大小 */
	protected static final int BIZTHREADSIZE = 4;

	/*
	 * private static final EventLoopGroup bossGroup = new NioEventLoopGroup(
	 * BIZGROUPSIZE); private static final EventLoopGroup workerGroup = new
	 * NioEventLoopGroup( BIZTHREADSIZE);
	 */

	/**
	 * Application入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Bootstrap server = new Bootstrap();
			server.start(PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动服务
	 * 
	 * @param port
	 * @throws Exception
	 */
	public void start(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// 服务
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							// 上行解码器
							ch.pipeline().addLast(new TcpDecoder());
							// 下行编码器
							ch.pipeline().addLast(new TcpEncoder());
							ch.pipeline().addLast(new GameService());
						}
					}).option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			showServerInfo(runtime);
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
			
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	/**
	 * 显示服务器信息
	 * @param runtime
	 */
	public static void showServerInfo(Runtime runtime){
		StringBuilder sb = new StringBuilder();	
		NewDate startDate = new NewDate();
		String sStartDate = startDate.fmt_yyyyMMddHHmmss();
		long freeMem1 = runtime.freeMemory();

		// ////////////////////////////////////
		long freeMem2 = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();

		sb.append("/////////////////////////////////////////");
		sb.append("\n");
		sb.append("// 服务器启动成功！端口号:" + PORT);
		sb.append("\n");
		sb.append("// 已使用内存  :" + ((freeMem1 - freeMem2) / (1024 * 1024))
				+ "MB");
		sb.append("\n");
		sb.append("// 剩余内存  :" + ((freeMem2) / (1024 * 1024)) + "MB");
		sb.append("\n");
		sb.append("// 总内存  :" + ((totalMemory) / (1024 * 1024)) + "MB");
		sb.append("\n");
		sb.append("// 开启时间  :" + sStartDate + "");
		sb.append("\n");
		sb.append("/////////////////////////////////////////");
		System.out.print(sb.toString());
	}

}
