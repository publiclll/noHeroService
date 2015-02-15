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
		// ǰ��4���ֽڴ洢����
		if (data.readableBytes() < 4) {
			return;
		}
		data.markReaderIndex();

		// ��������Ϣ��ĳ���
		byte[] lenBytes = new byte[4];
		data.readBytes(lenBytes);
		int length = bytesToInt(lenBytes, 0);
		// ��Ϣ�峤�Ȳ����������ȴ�
		if (length <= 0 || data.readableBytes() < length) {
			data.resetReaderIndex();
			return;
		}

		// ��������Ϣͷ�ĳ���
		byte[] headerLenBytes = new byte[4];
		data.readBytes(headerLenBytes);
		int headerLength = bytesToInt(headerLenBytes, 0);

		// ��������Ϣ�峤��
		byte[] msgLenBytes = new byte[4];
		data.readBytes(msgLenBytes);
		int msgLength = bytesToInt(msgLenBytes, 0);

		// ��������Ϣͷ����
		byte[] headerBytes = new byte[headerLength];
		data.readBytes(headerBytes);
		
		//MsgHeader.Header header = MsgHeader.Header.parseFrom(headerBytes);
		//int packetID = header.getPacketID();
		
		// ��������Ϣ������
		byte[] dataBytes = new byte[msgLength];
		data.readBytes(dataBytes);
		
		//����Ƶ������Ϣת������Ϣ��������
		//��Ϣ���������������������ҷ�����Ӧ����
		//CommandParsing.CMDParsing(packetID, ctx.channel(), dataBytes);
	}

	/**
	 * bytesת����int
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
	 * intת����byte����
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
