package GAME_SERVICES;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

//import pb.Common;
//import pb.MsgHeader;
//import tools.Utility;

public class TcpDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf data,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		// 前面4个字节存储长度
		if (data.readableBytes() < 4) {
			return;
		}
		data.markReaderIndex();

		// 解析出消息体的长度
		byte[] lenBytes = new byte[4];
		data.readBytes(lenBytes);
		int length = bytesToInt(lenBytes, 0);
		// 消息体长度不够，继续等待
		if (length <= 0 || data.readableBytes() < length) {
			data.resetReaderIndex();
			return;
		}

		// 解析出消息头的长度
		byte[] headerLenBytes = new byte[4];
		data.readBytes(headerLenBytes);
		int headerLength = bytesToInt(headerLenBytes, 0);

		// 解析出消息体长度
		byte[] msgLenBytes = new byte[4];
		data.readBytes(msgLenBytes);
		int msgLength = bytesToInt(msgLenBytes, 0);

		// 解析出消息头数据
		byte[] headerBytes = new byte[headerLength];
		data.readBytes(headerBytes);
		
		//MsgHeader.Header header = MsgHeader.Header.parseFrom(headerBytes);
		//int packetID = header.getPacketID();
		
		// 解析出消息体数据
		byte[] dataBytes = new byte[msgLength];
		data.readBytes(dataBytes);
		
		//链接频道和消息转发到消息解析方法
		//消息解析方法负责解析命令，并且返回相应数据
		//CommandParsing.CMDParsing(packetID, ctx.channel(), dataBytes);
	}

	/**
	 * bytes转换成int
	 * @param data
	 * @param offset
	 * @return
	 */
	public static int bytesToInt(byte[] data, int offset) {
		   int num = 0;
		   for (int i = offset; i < offset + 4; i++) {
		    num <<= 8;
		    num |= (data[i] & 0xff);
		   }
		   return num;
		}
	
	/**
	 * int转换成byte数组
	 * @param num
	 * @return
	 */
	public static byte[] intToBytes(int num) {   
		byte[] b = new byte[4];
		   for (int i = 0; i < 4; i++) {
		    b[i] = (byte) (num >>> (24 - i * 8));
		   }
		   return b;
	}
	
    public static Object ByteToObject(byte[] bytes) {  
        Object obj = null;  
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);  
        ObjectInputStream oi = null;  
        try {  
            oi = new ObjectInputStream(bi);  
            obj = oi.readObject();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                bi.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                oi.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return obj;  
    }
    
    public static byte[] ObjectToByte(Object obj) {  
        byte[] bytes = null;  
        ByteArrayOutputStream bo = new ByteArrayOutputStream();  
        ObjectOutputStream oo = null;  
        try {  
            oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
            bytes = bo.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                bo.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                oo.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return (bytes);  
    }
}
