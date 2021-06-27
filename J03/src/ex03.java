import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ex03 {
	public static void main(String[] args) {
		int num_threads = 3;
		Queue<String> files = initFilesQue("/home/kiborroq/Desktop/git/java_piscine/J03/src/files_urls.txt");
		int num_files = files.size();

		Thread [] threads = new Thread[num_threads];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new DownloadFileRunnable(files, num_files, "/home/kiborroq/Desktop/git/java_piscine/J03/src"));
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static Queue<String> initFilesQue(String path) {
		Queue<String> files = new PriorityQueue<String>();
		try {
			FileInputStream fin = new FileInputStream(path);
			String content = new String(fin.readAllBytes());
			String [] lines = content.split("\n");
			for (String s : lines)
				files.add(s.split(" ")[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}

}
