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
 * ��������
 * 
 * @author publiclll
 *
 */
public class Bootstrap {

	/** �˿� */
	private static final int PORT = 1080;
	
	private static final Runtime runtime = Runtime.getRuntime();

	/** ���ڷ��䴦��ҵ���̵߳��߳������ */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime()
			.availableProcessors() * 2; // Ĭ��

	/** ҵ������̴߳�С */
	protected static final int BIZTHREADSIZE = 4;

	/*
	 * private static final EventLoopGroup bossGroup = new NioEventLoopGroup(
	 * BIZGROUPSIZE); private static final EventLoopGroup workerGroup = new
	 * NioEventLoopGroup( BIZTHREADSIZE);
	 */

	/**
	 * Application���
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
	 * ��������
	 * 
	 * @param port
	 * @throws Exception
	 */
	public void start(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// ����
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							// ���н�����
							ch.pipeline().addLast(new TcpDecoder());
							// ���б�����
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
	 * ��ʾ��������Ϣ
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
		sb.append("// �����������ɹ����˿ں�:" + PORT);
		sb.append("\n");
		sb.append("// ��ʹ���ڴ�  :" + ((freeMem1 - freeMem2) / (1024 * 1024))
				+ "MB");
		sb.append("\n");
		sb.append("// ʣ���ڴ�  :" + ((freeMem2) / (1024 * 1024)) + "MB");
		sb.append("\n");
		sb.append("// ���ڴ�  :" + ((totalMemory) / (1024 * 1024)) + "MB");
		sb.append("\n");
		sb.append("// ����ʱ��  :" + sStartDate + "");
		sb.append("\n");
		sb.append("/////////////////////////////////////////");
		System.out.print(sb.toString());
	}

}
