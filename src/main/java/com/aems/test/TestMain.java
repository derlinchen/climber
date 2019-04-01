package com.aems.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestMain {
	public static void main(String[] args) {
		Node node1 = new Node("1", "");
		Node node2 = new Node("2", "1");
		Node node3 = new Node("22", "1");
		Node node4 = new Node("3", "2", 3);
		Node node5 = new Node("33", "22", 33);
		Node node6 = new Node("44", "3", 44);
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);

		List<Node> pnode = new ArrayList<Node>();
		List<Node> lnode = new ArrayList<Node>();
		for (Node node : nodes) {
			if (ifChilds(nodes, node.getId())) {// 存在子集
				pnode.add(node);
			} else {
				lnode.add(node);
				node.setLeaf(true);
			}
		}
//		for (Node node : lnode) {
//			System.out.println(node.getId()+":" + node.isLeaf());
//		}
//		System.out.println("=======");
//		for (Node node : pnode) {
//			System.out.println(node.getId()+":" + node.isLeaf());
//		}
		
		getinfo(pnode, lnode);

//		for (Node node : lnode) {
//			System.out.println(node.getId() + ":" + node.getValue());
//		}
	}

	private static void getinfo(List<Node> pnode, List<Node> lnode) {
		Iterator<Node> pit = pnode.iterator();
		Iterator<Node> lit = lnode.iterator();
		
			while (pit.hasNext()) {
				double newvalue = 0;
				Node ppnode = pit.next();
				System.out.println("非叶子节点-----"+ppnode.getId() +  "----" +ppnode.getValue());
				boolean isadd = false;
				while (lit.hasNext()) {
					Node llnode = lit.next();
					System.out.println("叶子节点===="+llnode.getId() + "====" + llnode.getPid());
					if(llnode.getPid().equals(ppnode.getId())) {
						newvalue += llnode.getValue();
						isadd = true;
					}
				}
				System.out.println("可传递-----"+ppnode.getId() +  "----" +ppnode.getValue() + "-----" + isadd);
//				if(isadd) {
//					ppnode.setValue(newvalue);
//					lnode.add(ppnode);
//				}
				pit.remove();
//				lnode.add(ppnode);
				getinfo(pnode, lnode);
			}
	}
	
	private static boolean ifChilds(List<Node> list, String pId) {
		boolean flag = false;
		for (Node node : list) {
			if (node.getPid().equals(pId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
