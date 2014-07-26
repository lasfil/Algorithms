package graphics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class AGraphic {
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
		private Arc next;
		private int headIndex;
		private int weight;

		private Arc(Arc next, int headIndex) {
			this.next = next;
			this.headIndex = headIndex;
		}
	}

	private ArrayList<Vertice> vertices;

	public AGraphic(int size) {
		this.verNum = size;
		vertices = new ArrayList<Vertice>();
	}

	public AGraphic(String[] infoArr) {
		this.verNum = infoArr.length;
		vertices = new ArrayList<Vertice>();
		for (int i = 0; i < verNum; i++) {
			vertices.add(new Vertice(infoArr[i]));
		}
	}

	public void addArc(int tail, int head) {
		// int verIndex = getIndex(tail);
		Arc newArc = new Arc(vertices.get(tail).adjArc, head);
		vertices.get(tail).adjArc = newArc;
		arcNum++;
	}

	public void addArc(String tailInfo, String headInfo) {
		int tailIndex = getIndex(tailInfo);
		int headIndex = getIndex(headInfo);
		if (tailIndex >= 0 && headIndex >= 0) {
			addArc(tailIndex, headIndex);
			addArc(headIndex, tailIndex);
		}
	}

	private int getIndex(String tailInfo) {
		int index = -1;
		for (int i = 0; i < verNum; i++) {
			if (vertices.get(i).info.equals(tailInfo))
				index = i;
		}
		return index;
	}

	public ArrayList<Vertice> getAdjVer(int verIndex) {
		ArrayList<Vertice> adjVerArr = new ArrayList<Vertice>();
		Arc temp = vertices.get(verIndex).adjArc;
		while (temp != null) {
			adjVerArr.add(vertices.get(temp.headIndex));
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
				sb.append(temp.headIndex + "-> ");
				temp = temp.next;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void depthFirstSearch() {
		boolean[] marked = new boolean[verNum];
		for (int i = 0; i < verNum; i++) {

			dfs(i, marked);

		}
	}

	private void dfs(int index, boolean[] marked) {
		if (marked[index] == false) {
			Vertice ver = vertices.get(index);
			visit(ver);
			marked[index] = true;
			Arc temp = ver.adjArc;
			while (temp != null) {
				// System.out.println("go to" + temp.headIndex);
				if (marked[temp.headIndex] == false)
					dfs(temp.headIndex, marked);
				temp = temp.next;
			}
		}
	}

	private void visit(Vertice vertice) {
		System.out.println(vertice.info);
	}

	public Iterable<String> depthFirstPath(String sInfo, String dInfo) {
		int sIndex = this.getIndex(sInfo);
		int dIndex = this.getIndex(dInfo);
		boolean[] marked = new boolean[verNum];
		int[] lastVer = new int[verNum];
		for (int i = 0; i < verNum; i++) {
			lastVer[i] = -1;
		}
		dfs(sIndex, marked, lastVer);
		Stack<String> path = new Stack<String>();
		int x = dIndex;
		if (lastVer[x] == -1)
			// no path from sInfo to dInfo
			path = null;
		else {
			// reverse get every last vertice until sInfo
			while (x != sIndex) {
				path.push(vertices.get(x).info);
				x = lastVer[x];
			}
		}

		return path;

	}

	private void dfs(int sIndex, boolean[] marked, int[] lastVer) {
		if (marked[sIndex] == false) {
			Vertice ver = vertices.get(sIndex);
			visit(ver);
			marked[sIndex] = true;
			Arc temp = ver.adjArc;
			while (temp != null) {
				// System.out.println("go to" + temp.headIndex);
				if (marked[temp.headIndex] == false) {
					lastVer[temp.headIndex] = sIndex;
					dfs(temp.headIndex, marked, lastVer);
				}
				temp = temp.next;
			}
		}
	}

	public void breadthFirstSearch() {
		boolean[] marked = new boolean[verNum];
		for (int i = 0; i < verNum; i++) {
			if(!marked[i])
				bfs(i, marked);
		}
	}
	private void bfs(int index, boolean[] marked) {
		Queue<Vertice> verQueue = new ArrayDeque<Vertice>();
		
		verQueue.offer(vertices.get(index));
		this.visit(vertices.get(index));
		marked[index] = true;
		Arc temp = null;
		while (!verQueue.isEmpty()) {

			// this.visit(visit);
			// marked[this.getIndex(visit.info)] = true;
			temp = verQueue.poll().adjArc;
			while (temp != null) {
				if (!marked[temp.headIndex]) {
					this.visit(vertices.get(temp.headIndex));
					marked[temp.headIndex] = true;
					verQueue.offer(vertices.get(temp.headIndex));
				}
				temp = temp.next;
			}
		}
	}
	
	private void bfs(int index, boolean[] marked, int[] lastVer) {
		Queue<Vertice> verQueue = new ArrayDeque<Vertice>();
		
		verQueue.offer(vertices.get(index));
		//this.visit(vertices.get(index));
		marked[index] = true;
		Arc temp = null;
		Vertice tempVer = null;
		while (!verQueue.isEmpty()) {

			// this.visit(visit);
			// marked[this.getIndex(visit.info)] = true;
			tempVer = verQueue.poll();
			temp = tempVer.adjArc;
			while (temp != null) {
				if (!marked[temp.headIndex]) {
					//this.visit(vertices.get(temp.headIndex));
					marked[temp.headIndex] = true;
					lastVer[temp.headIndex] = vertices.indexOf(tempVer);
					verQueue.offer(vertices.get(temp.headIndex));
				}
				temp = temp.next;
			}
		}
	}
	
	public Iterable<String> breadthFirstPath(String sInfo, String dInfo) {
		int sIndex = this.getIndex(sInfo);
		int dIndex = this.getIndex(dInfo);
		boolean[] marked = new boolean[verNum];
		int[] lastVer = new int[verNum];
		for (int i = 0; i < verNum; i++) {
			lastVer[i] = -1;
		}
		bfs(sIndex, marked, lastVer);
		Stack<String> path = new Stack<String>();
		int x = dIndex;
		if (lastVer[x] == -1)
			// no path from sInfo to dInfo
			path = null;
		else {
			// reverse get every last vertice until sInfo
			while (x != sIndex) {
				path.push(vertices.get(x).info);
				x = lastVer[x];
			}
		}

		return path;

	}

}
