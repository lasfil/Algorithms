package graphics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class DGraphic {
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

	private static class DirectedCycle {
		private boolean[] marked; // marked[v] = has vertex v been marked?
		private int[] edgeTo; // edgeTo[v] = previous vertex on path to v
		private boolean[] onStack; // onStack[v] = is vertex on the stack?
		private Stack<Integer> cycle; // directed cycle (or null if no such
										// cycle)

		/**
		 * Determines whether the digraph <tt>G</tt> has a directed cycle and,
		 * if so, finds such a cycle.
		 * 
		 * @param G
		 *            the digraph
		 */
		public DirectedCycle(DGraphic G) {
			marked = new boolean[G.verNum];
			onStack = new boolean[G.verNum];
			edgeTo = new int[G.verNum];
			for (int v = 0; v < G.verNum; v++)
				if (!marked[v])
					dfs(G, v);
		}

		// certify that digraph is either acyclic or has a directed cycle
		private boolean check(DGraphic G) {

			if (hasCycle()) {
				// verify cycle
				int first = -1, last = -1;
				for (int v : cycle()) {
					if (first == -1)
						first = v;
					last = v;
				}
				if (first != last) {
					System.err.printf(
							"cycle begins with %d and ends with %d\n", first,
							last);
					return false;
				}
			}

			return true;
		}

		/**
		 * Returns a directed cycle if the digraph has a directed cycle, and
		 * <tt>null</tt> otherwise.
		 * 
		 * @return a directed cycle (as an iterable) if the digraph has a
		 *         directed cycle, and <tt>null</tt> otherwise
		 */
		public Iterable<Integer> cycle() {
			return cycle;
		}

		// check that algorithm computes either the topological order or finds a
		// directed cycle
		private void dfs(DGraphic G, int v) {
			onStack[v] = true;
			marked[v] = true;
			Arc temp = G.vertices.get(v).adjArc;
			for (; temp != null; temp = temp.next) {

				// short circuit if directed cycle found
				if (cycle != null)
					return;

				// found new vertex, so recur
				else if (!marked[temp.headIndex]) {
					edgeTo[temp.headIndex] = v;
					dfs(G, temp.headIndex);
				}

				// trace back directed cycle
				else if (onStack[temp.headIndex]) {
					cycle = new Stack<Integer>();
					for (int x = v; x != temp.headIndex; x = edgeTo[x]) {
						cycle.push(x);
					}
					cycle.push(temp.headIndex);
					cycle.push(v);
				}
			}

			onStack[v] = false;
		}

		/**
		 * Does the digraph have a directed cycle?
		 * 
		 * @return <tt>true</tt> if the digraph has a directed cycle,
		 *         <tt>false</tt> otherwise
		 */
		public boolean hasCycle() {
			return cycle != null;
		}

	}

	private static class Vertice {
		private String info;
		private Arc adjArc;

		private int index;

		private int unionId;

		private Vertice(String info) {
			this.info = info;
			this.unionId = -1;
		}

		public Vertice(String info, int i) {
			this.info = info;
			this.unionId = -1;
			this.index = i;
		}

		public String toString() {
			return info + "[" + index + "]" + ":" + unionId;
		}
	}

	private int verNum;

	private int arcNum;

	private int[] dfunionId;

	private Stack<Vertice> topoOrder;

	private ArrayList<Vertice> vertices;

	public DGraphic(DGraphic g) {
		this.verNum = g.verNum;
		vertices = new ArrayList<Vertice>();
		for (int i = 0; i < verNum; i++) {
			vertices.add(new Vertice(g.vertices.get(i).info, i));
		}
		topoOrder = new Stack<Vertice>();

	}

	public DGraphic(int size) {
		this.verNum = size;
	}

	public DGraphic(String[] infoArr) {
		this.verNum = infoArr.length;
		vertices = new ArrayList<Vertice>();
		for (int i = 0; i < verNum; i++) {
			vertices.add(new Vertice(infoArr[i], i));
		}
		dfunionId = new int[verNum];
		for (int i = 0; i < verNum; i++)
			dfunionId[i] = -1;
		topoOrder = new Stack<Vertice>();
	}

	public void addArc(int tail, int head, int weight) {
		// int verIndex = getIndex(tail);
		Arc newArc = new Arc(weight, vertices.get(tail).adjArc, head);
		vertices.get(tail).adjArc = newArc;
		arcNum++;
	}

	public void addArc(String tailInfo, String headInfo, int weight) {
		int tailIndex = getIndex(tailInfo);
		int headIndex = getIndex(headInfo);
		if (tailIndex >= 0 && headIndex >= 0)
			addArc(tailIndex, headIndex, weight);
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
		// this.visit(vertices.get(index));
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
					// this.visit(vertices.get(temp.headIndex));
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

	public void breadthFirstSearch() {
		boolean[] marked = new boolean[verNum];
		for (int i = 0; i < verNum; i++) {
			if (!marked[i]) {
				bfs(i, marked);
				System.out.println("another ");
			}
		}
	}

	public void connectedVertice(String verInfo) {
		int index = this.getIndex(verInfo);
		boolean[] marked = new boolean[verNum];
		dfs(index, marked);
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

	public void depthFirstSearch() {
		boolean[] marked = new boolean[verNum];

		for (int i = 0; i < verNum; i++) {
			if (!marked[i]) {
				dfs(i, marked);
				// System.out.println("another ");
			}
		}
	}

	private void dfs(int i, boolean[] marked) {
		if (!marked[i]) {
			Vertice ver = vertices.get(i);
			visit(ver);
			marked[i] = true;

			Arc temp = ver.adjArc;
			while (temp != null) {
				if (!marked[temp.headIndex]) {
					vertices.get(temp.headIndex).unionId = ver.unionId;

					dfs(temp.headIndex, marked);
				}
				temp = temp.next;
			}
			topoOrder.push(ver);
		}
	}

	// used for depthFirstPath
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

	public ArrayList<Vertice> getAdjVer(int verIndex) {
		ArrayList<Vertice> adjVerArr = new ArrayList<Vertice>();
		Arc temp = vertices.get(verIndex).adjArc;
		while (temp != null) {
			adjVerArr.add(vertices.get(temp.headIndex));
			temp = temp.next;
		}
		return adjVerArr;
	}

	private int getIndex(String tailInfo) {
		int index = -1;
		for (int i = 0; i < verNum; i++) {
			if (vertices.get(i).info.equals(tailInfo))
				index = i;
		}
		return index;
	}

	public boolean hasCycle() {
		DirectedCycle cycle = new DirectedCycle(this);
		System.out.println(cycle.cycle);
		return cycle.hasCycle();
	}

	private void kosarajuCC() {
		boolean[] marked = new boolean[verNum];
		Stack<Vertice> reversePostOfReverseG = this.reverse().topoOrder();
		while(!reversePostOfReverseG.isEmpty()) {
			Vertice ver = reversePostOfReverseG.pop();
			int index = this.getIndex(ver.info);
			if (!marked[index]) {
				vertices.get(index).unionId = index;
				dfs(index, marked);
			}
		}
	}

	public DGraphic reverse() {
		DGraphic reverse = new DGraphic(this);
		for (int i = 0; i < verNum; i++) {
			Vertice ver = vertices.get(i);
			for (Arc arc = ver.adjArc; arc != null; arc = arc.next) {
				reverse.addArc(arc.headIndex, i, arc.weight);
			}
		}
		return reverse;
	}

	public boolean stronglyConnected(String v1Info, String v2Info) {
		int i1 = this.getIndex(v1Info);
		int i2 = this.getIndex(v2Info);

		if (vertices.get(0).unionId == -1)
			this.kosarajuCC();

		return vertices.get(i1).unionId == vertices.get(i2).unionId;
	}

	public Stack<Vertice> topoOrder() {
		if (this.topoOrder.isEmpty())
			this.depthFirstSearch();
		return this.topoOrder;
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

	private void visit(Vertice vertice) {
		//System.out.println(vertice.info);

	}

}
