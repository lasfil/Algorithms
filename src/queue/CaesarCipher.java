package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CaesarCipher {
	private int[] keys;
	private Queue<Integer> keyQueue;

	public void setKeys(int[] keys) {
		this.keys = keys;
	}

	public void initialKeyQueue() {
		keyQueue = new LinkedList<Integer>();
		for (int key : keys) {
			keyQueue.offer(key);
		}
	}

	public String cipher(String source) {
		this.initialKeyQueue();
		StringBuffer sb = new StringBuffer();
		char[] sChars = source.toCharArray();
		int key;

		for (char c : sChars) {
			key = keyQueue.poll();
			sb.append((char) (c + key));
			keyQueue.offer(key);
		}

		return sb.toString();
	}
	
	public String decipher(String cipheredMsg) {
		this.initialKeyQueue();
		StringBuffer sb = new StringBuffer();
		char[] sChars = cipheredMsg.toCharArray();
		int key;

		for (char c : sChars) {
			key = keyQueue.poll();
			sb.append((char) (c - key));
			keyQueue.offer(key);
		}
		return sb.toString();
	}

}
