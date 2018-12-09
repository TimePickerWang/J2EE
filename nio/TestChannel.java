package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/*
 * һ��ͨ����Channel��������Դ�ڵ���Ŀ��ڵ�����ӡ��� Java NIO �и��𻺳��������ݵĴ��䡣Channel �����洢���ݣ������Ҫ��ϻ��������д��䡣
 * 
 * ����ͨ������Ҫʵ����
 * 	java.nio.channels.Channel �ӿڣ�
 * 		|--FileChannel(�����ļ�����)
 * 		|--SocketChannel(TCP)
 * 		|--ServerSocketChannel(TCP)
 * 		|--DatagramChannel(UDP)
 * 
 * ������ȡͨ��
 * 1. Java ���֧��ͨ�������ṩ�� getChannel() ����
 * 		���� IO��
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 * 
 * 		����IO��
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 * 		
 * 2. �� JDK 1.7 �е� NIO.2 ��Ը���ͨ���ṩ�˾�̬���� open()
 * 3. �� JDK 1.7 �е� NIO.2 �� Files ������� newByteChannel()
 * 
 * �ġ�ͨ��֮������ݴ���
 * transferFrom()
 * transferTo()
 * 
 * �塢��ɢ(Scatter)��ۼ�(Gather)
 * ��ɢ��ȡ��Scattering Reads������ͨ���е����ݷ�ɢ�������������
 * �ۼ�д�루Gathering Writes����������������е����ݾۼ���ͨ����
 * 
 * �����ַ�����Charset
 * ���룺�ַ��� -> �ֽ�����
 * ���룺�ֽ�����  -> �ַ���
 */

public class TestChannel {

	// ����ͨ������ļ��ĸ��ƣ���ֱ�ӻ�������
	@Test
	public void test1() {
		long start = System.currentTimeMillis();

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("./src/nio/1.jpg");
			fos = new FileOutputStream("./src/nio/2.jpg");

			// �ٻ�ȡͨ��
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();

			// �ڷ���ָ����С�Ļ�����
			ByteBuffer buf = ByteBuffer.allocate(1024);

			// �۽�ͨ���е����ݴ��뻺������
			while (inChannel.read(buf) != -1) {
				buf.flip(); // �л���ȡ���ݵ�ģʽ
				// �ܽ��������е�����д��ͨ����
				outChannel.write(buf);
				buf.clear();// ��ջ�����
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("�ķ�ʱ��Ϊ��" + (end - start));
	}

	// ʹ��ֱ�ӻ���������ļ��ĸ���(�ڴ�ӳ���ļ�)
	@Test
	public void test2() throws IOException {
		long start = System.currentTimeMillis();

		FileChannel inChannel = FileChannel.open(Paths.get("./src/nio/1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("./src/nio/3.jpg"), StandardOpenOption.READ,
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		// �ڴ�ӳ���ļ�
		MappedByteBuffer inMap = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMap = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

		// ֱ�ӶԻ������������ݵĶ�д����
		byte[] dst = new byte[inMap.limit()];
		inMap.get(dst);
		outMap.put(dst);

		inChannel.close();
		outChannel.close();

		long end = System.currentTimeMillis();
		System.out.println("�ķ�ʱ��Ϊ��" + (end - start));
	}

	// ͨ��֮������ݴ���(ֱ�ӻ�����)
	@Test
	public void test3() throws IOException {
		FileChannel inChannel = FileChannel.open(Paths.get("./src/nio/1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("./src/nio/4.jpg"), StandardOpenOption.READ,
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		inChannel.transferTo(0, inChannel.size(), outChannel);

		inChannel.close();
		outChannel.close();
	}

	// ��ɢ�;ۼ�
	@Test
	public void test4() throws IOException {
		RandomAccessFile accessFile = new RandomAccessFile("./src/nio/1.txt", "rw");

		// 1. ��ȡͨ��
		FileChannel channel1 = accessFile.getChannel();

		// 2. ����ָ����С�Ļ�����
		ByteBuffer buf1 = ByteBuffer.allocate(200);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);

		// 3. ��ɢ��ȡ
		ByteBuffer[] dsts = { buf1, buf2 };
		channel1.read(dsts);

		for (ByteBuffer buf : dsts) {
			buf.flip();
		}

		System.out.println(new String(buf1.array(), 0, buf1.limit()));
		System.out.println("--------------------------");
		System.out.println(new String(buf2.array(), 0, buf2.limit()));

		// 4. �ۼ�д��
		RandomAccessFile accessFile2 = new RandomAccessFile("./src/nio/2.txt", "rw");
		FileChannel channel2 = accessFile2.getChannel();
		channel2.write(dsts);

		accessFile.close();
		accessFile2.close();
		channel1.close();
		channel2.close();
	}

	// �ַ���
	@Test
	public void test5() throws IOException {
		Charset charset = Charset.forName("GBK");

		// ��ȡ������
		CharsetEncoder ce = charset.newEncoder();
		// ��ȡ������
		CharsetDecoder cd = charset.newDecoder();

		CharBuffer cBuf = CharBuffer.allocate(1024);
		cBuf.put("�������»�");
		cBuf.flip();

		// ����
		ByteBuffer bBuf = ce.encode(cBuf);
		for (int i = 0; i < 10; i++) {
			System.out.println(bBuf.get());
		}

		// ����
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println(cBuf2.toString());

		System.out.println("------------------------------");
		
		bBuf.flip();
		Charset charset2 = Charset.forName("UTF-8");
		CharBuffer cBuf3 = charset2.decode(bBuf);
		System.out.println(cBuf3.toString());
	}

}
