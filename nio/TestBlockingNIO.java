package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestBlockingNIO {

	// �ͻ���
	@Test
	public void client() throws IOException {
		// 1. ��ȡͨ��
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

		FileChannel inChannel = FileChannel.open(Paths.get("./src/nio/1.jpg"), StandardOpenOption.READ);

		// 2. ����ָ����С�Ļ�����
		ByteBuffer buf = ByteBuffer.allocate(1024);

		// 3. ��ȡ�����ļ��������͵������
		while (inChannel.read(buf) != -1) {
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}

		// 4. �ر�ͨ��
		sChannel.close();
		inChannel.close();
	}

	@Test
	public void server() throws IOException {
		// 1. ��ȡͨ��
		ServerSocketChannel ssc = ServerSocketChannel.open();

		FileChannel outChannel = FileChannel.open(Paths.get("./src/nio/2.jpg"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);

		// 2. ������
		ssc.bind(new InetSocketAddress(9898));

		// 3. ��ȡ�ͻ������ӵ�ͨ��
		SocketChannel sc = ssc.accept();

		// 4. ����ָ����С�Ļ�����
		ByteBuffer buf = ByteBuffer.allocate(1024);

		// 5. ���տͻ��˵����ݣ������浽����
		while (sc.read(buf) != -1) {
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}

		// 6. �ر�ͨ��
		outChannel.close();
		ssc.close();
		sc.close();
	}

}
