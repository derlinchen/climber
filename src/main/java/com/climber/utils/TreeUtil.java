package com.climber.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
	static List<TreeBean> data = new ArrayList<TreeBean>();
	static {
		TreeBean l = new TreeBean(1, "北京市", 0, 1);
		TreeBean l2 = new TreeBean(3, "朝阳区", 1, 2);
		TreeBean l5 = new TreeBean(6, "大望路", 3, 3);
		TreeBean l4 = new TreeBean(5, "大郊亭中街", 3, 3);
		TreeBean l11 = new TreeBean(12, "二号院", 5, 4);
		TreeBean l12 = new TreeBean(13, "三号楼", 12, 5);
		TreeBean l13 = new TreeBean(14, "四号楼", 12, 5);
		TreeBean l6 = new TreeBean(7, "南磨房路", 3, 3);
		TreeBean l3 = new TreeBean(4, "海淀区", 1, 2);
		TreeBean l7 = new TreeBean(8, "唐家岭路", 4, 3);
		TreeBean l8 = new TreeBean(9, "上地路", 4, 3);
		TreeBean l1 = new TreeBean(2, "天津市", 0, 1);
		TreeBean l9 = new TreeBean(10, "红桥区", 2, 2);
		TreeBean l10 = new TreeBean(11, "北辰区", 2, 2);
		data.add(l2);
		data.add(l);
		data.add(l1);
		data.add(l3);
		data.add(l10);
		data.add(l9);
		data.add(l5);
		data.add(l4);
		data.add(l7);
		data.add(l12);
		data.add(l11);
		data.add(l13);
		data.add(l6);
		data.add(l8);
	}

	public static void main(String[] args) {

		List<TreeBean> resultList = new ArrayList<>();
//		System.out.println("查询前集合大小:" + data.size());
//		long start = System.currentTimeMillis();
		for (TreeBean TreeBean : data) {
//			times++;
			if (TreeBean.getParentId() == 0) {// 父级菜单开始添加
				resultList.add(TreeBean);
				if (ifChilds(data, TreeBean.getId())) {// 存在子集
					List<TreeBean> childs = new ArrayList<TreeBean>();
					childs = getChildList(data, TreeBean.getId(), childs);
					resultList.addAll(childs);
				} 
			}
		}
//		long end = System.currentTimeMillis();
//		System.out.println("查询后集合大小:" + resultList.size());
//		System.out.println("查询次数:" + times + "次");
//		System.out.println("查询耗时:" + (end - start) + "毫秒");

//		for (TreeBean TreeBean : resultList) {
//			System.out.println(TreeBean.getName());
//		}
		
		for (TreeBean TreeBean : resultList) {
			String s = "";
			for (int i = 1; i < TreeBean.getLevel(); i++) {
				s = s + "\t";
			}
			System.out.println(s + "I____" + TreeBean.getName() + "----" + TreeBean.isLeaf());
		}
	}

	private static boolean ifChilds(List<TreeBean> list, int pId) {
		boolean flag = false;
		for (TreeBean TreeBean : list) {
//			times++;
			if (TreeBean.getParentId() == pId) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// 获取父id下的子集合
	private static List<TreeBean> getChildList(List<TreeBean> list, int pId,
			List<TreeBean> reList) {
		for (TreeBean TreeBean : list) {
//			times++;
			if (TreeBean.getParentId() == pId) {// 查询下级菜单
				reList.add(TreeBean);
				if (ifChilds(list, TreeBean.getId())) {
					getChildList(list, TreeBean.getId(), reList);
				} else{
					TreeBean.setLeaf(true);
				}
			}
		}
		return reList;
	}

}
