package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * tree TODO <br>
 * 
 * @author kangxu2 2017-1-7
 * 
 */
public class Tree2<T> {
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 显示节点文本
	 */
	private String text;
	
	private String grage;
	/**
	 * 节点状态，open closed
	 */
	@JsonIgnore
	private Map<String, Object> state;
	/**
	 * 节点是否被选中 true false
	 */
	@JsonIgnore
	private boolean checked = false;
	/**
	 * 节点属性
	 */
	@JsonIgnore
	private Map<String, Object> attributes;

	/**
	 * 节点的子节点
	 */
	private List<Tree2<T>> children = new ArrayList<Tree2<T>>();

	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 是否有父节点
	 */
	@JsonIgnore
	private boolean hasParent = false;
	/**
	 * 是否有子节点
	 */
	@JsonIgnore
	private boolean hasChildren = false;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, Object> getState() {
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<Tree2<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Tree2<T>> children) {
		this.children = children;
	}

	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean isParent) {
		this.hasParent = isParent;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setChildren(boolean isChildren) {
		this.hasChildren = isChildren;
	}

	public Tree2(String id, String text, Map<String, Object> state, boolean checked, Map<String, Object> attributes,
			List<Tree2<T>> children, boolean isParent, boolean isChildren, String parentID) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.hasParent = isParent;
		this.hasChildren = isChildren;
		this.parentId = parentID;
	}

	public Tree2() {
		super();
	}

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGrage() {
		return grage;
	}

	public void setGrage(String grage) {
		this.grage = grage;
	}
	
	

}