import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;

public class DownloadFileRunnable implements Runnable {
	private Queue<String> files;
	private int numFiles;
	private String downloadPath;
	private String path;
	private int nbr;

	DownloadFileRunnable(Queue<String> files, int numFiles, String downloadPath) {
		this.files = files;
		this.numFiles = numFiles;
		this.downloadPath = downloadPath;
	}

	private void downloadFile() {
		try {
			System.out.println(Thread.currentThread().getName() + " start download file number " + nbr);
			URL url = new URL(path);
			URLConnection urlConnection = url.openConnection();
			InputStream ins = urlConnection.getInputStream();
			OutputStream ous = new FileOutputStream(getFileName(downloadPath + path));
			byte [] content = ins.readAllBytes();
			ous.write(content);
			System.out.println(Thread.currentThread().getName() + " finish download file number " + nbr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFileName(String path) {
		String [] s = path.split("/");
		return s[s.length - 1];
	}

	@Override
	public void run() {
		while (files.size() > 0) {
			synchronized (files) {
				this.path = files.poll();
				this.nbr = numFiles - files.size();
			}
			downloadFile();
		}
	}
}
