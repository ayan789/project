package com.ccic.salesapp.noncar.dto.request.planelement;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTree2 {

	public static <T> Tree2<T> build(List<Tree2<T>> nodes) {

		if (nodes == null) {
			return null;
		}
		List<Tree2<T>> topNodes = new ArrayList<Tree2<T>>();

		for (Tree2<T> children : nodes) {

			String pid = children.getParentId();
			if ("0".equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree2<T> parent : nodes) {
				String id = parent.getId();
				if (id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}

		}

		Tree2<T> root = new Tree2<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("0");
			root.setHasParent(false);
			root.setChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setText("顶级节点");
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			root.setState(state);
		}

		return root;
	}

	public static <T> List<Tree2<T>> buildList(List<Tree2<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<Tree2<T>> topNodes = new ArrayList<Tree2<T>>();

		for (Tree2<T> children : nodes) {

			String pid = children.getParentId();
			if (pid.equals("0") || idParam.equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree2<T> parent : nodes) {
				String id = parent.getId();
				if (id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);

					continue;
				}
			}

		}
		return topNodes;
	}

}