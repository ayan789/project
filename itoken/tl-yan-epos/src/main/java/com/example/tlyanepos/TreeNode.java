package com.example.tlyanepos;



import java.util.ArrayList;

public class TreeNode {
	private long id;
	private long pid;
	private String name;
	private int status;
	private ArrayList<TreeNode> children=new ArrayList<TreeNode>();
	
	public TreeNode(long id, long pid, String name, int status) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.status = status;
	}
	
	public void add(TreeNode node)
	{
		if("0".equals(node.pid))
		{
			this.children.add(node);
		}
		else if(node.pid==(long)this.id)
		{
			this.children.add(node);
		}
		else
		{
			for(TreeNode tmp_node:children)
			{
				tmp_node.add(node);
			}
		}
	}
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}
	
	

}
