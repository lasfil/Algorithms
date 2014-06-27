package other;

import java.util.ArrayList;
import java.util.List;

public class MailBox {

	int mailNum, boxNum, min;
	int[] mails;
	List<int[]> mailBox;

	public MailBox(int mailNum, int boxNum, int min) {
		this.mailNum = mailNum;
		this.boxNum = boxNum;
		this.min = min;
		mails = new int[boxNum];
		mailBox = new ArrayList<int[]>();
	}

	public void mail() {

		int max = mailNum - boxNum * min + min;

		for (int b1 = min; b1 <= max; b1++) {
			for (int b2 = min; b2 <= max; b2++) {
				for (int b3 = min; b3 <= max; b3++) {
					for (int b4 = min; b4 <= max; b4++) {
						if (b1 + b2 + b3 + b4 == mailNum) {
							int[] mails = new int[] { b1, b2, b3, b4 };
							mailBox.add(mails);
						}
					}
				}
			}
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Total combination: " + mailBox.size() + " \n");
		for (int[] intArr : mailBox) {

			for (int a : intArr) {
				sb.append(a + " ");
			}
			sb.append("\n");
		}
		String result = sb.toString();
		sb.setLength(0);

		return result;
	}
}
