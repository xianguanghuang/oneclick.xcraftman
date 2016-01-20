package impatient.java.lang.multithreads.cancellation_and_shutdown.task_cancellation;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


/*
 * 将使用socket 的线程封装起来，处理中断逻辑
 * */
public class ReaderThread extends Thread {

	private static final int BUFSZ = 0;
	private final Socket socket;
	private final InputStream in;

	public ReaderThread(Socket socket) throws IOException {
		this.socket = socket;
		this.in = socket.getInputStream();
	}

	public void interrupt() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.interrupt();
		}
		;
	}

	public void run() {
		try {
			byte[] buf = new byte[BUFSZ];
			while (true) {
				int count = in.read(buf);
				if (count < 0)
					break;
				else if (count > 0)
					processBuff(buf, count);
			}
		} catch (IOException e) {
			/* Allow thread to exit */
		}
	}

	private void processBuff(byte[] buf, int count) {
		// TODO Auto-generated method stub

	}
}
