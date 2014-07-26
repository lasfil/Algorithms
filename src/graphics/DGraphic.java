package graphics;

import java.util.ArrayList;

public class DGraphic {
	private int verNum;
	private int arcNum;

	private static class Vertice {
		private String info;
		private Arc adjArc;

		private Vertice(String info) {
			this.info = info;
		}
	}

	private static class Arc {
		private int weight;
		private Arc next;
		private int headIndex;

		private Arc(int weight, Arc next, int headInfo) {
			this.weight = weight;
			this.next = next;
			this.headIndex = headInfo;
		}
	}

	private Vertice[] vertices;

	public DGraphic(int size) {
		this.verNum = size;
		vertices = new Vertice[verNum];
	}

	public DGraphic(String[] infoArr) {
		this.verNum = infoArr.length;
		vertices = new Vertice[verNum];
		for (int i = 0; i < verNum; i++) {
			vertices[i] = new Vertice(infoArr[i]);
		}
	}

	public void addArc(int tail, int head, int weight) {
		// int verIndex = getIndex(tail);
		Arc newArc = new Arc(weight, vertices[tail].adjArc, head);
		vertices[tail].adjArc = newArc;
		arcNum++;
	}
	
	public ArrayList<Vertice> getAdjVer(int verIndex){
		ArrayList<Vertice> adjVerArr = new ArrayList<Vertice>();
		Arc temp = vertices[verIndex].adjArc;
		while(temp != null) {
			adjVerArr.add(vertices[temp.headIndex]);
			temp = temp.next;	
		}
		return adjVerArr;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (Vertice ver : vertices) {
			sb.append(ver.info + "(" + index++ + ")" + " -> ");
			Arc temp = ver.adjArc;
			while (temp != null) {
				sb.append(temp.headIndex + ":[" + temp.weight + "]-> ");
				temp = temp.next;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
