package tree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Connect;

public class Tree<T> {

	private Node<T> root;
	private int i = 0;

	public Tree(Node<T> root) {
		this.root = root;
	}

	public boolean isEmpty() {
		return (root == null) ? true : false;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public int getNumberOfNodes() {
		return getNumberOfDescendants(root) + 1;
	}

	public int getNumberOfDescendants(Node<T> node) {
		int n = node.getChildren().size();
		for (Node<T> child : node.getChildren())
			n += getNumberOfDescendants(child);
		return n;
	}

	private boolean find(Node<T> node, T keyNode) {
		boolean res = false;
		if (node.getData().equals(keyNode))
			return true;

		else {
			for (Node<T> child : node.getChildren())
				if (find(child, keyNode))
					res = true;
		}

		return res;
	}

	public Node<T> findNode(Node<T> node, T keyNode) {
		if (node == null)
			return null;
		if (node.getData().equals(keyNode))
			return node;
		else {
			Node<T> cnode = null;
			for (Node<T> child : node.getChildren())
				if ((cnode = findNode(child, keyNode)) != null)
					return cnode;
		}
		return null;
	}

	private void print(Node<T> node) {
		for (Node<T> child : node.getChildren()) {		
			System.out.println("\n Parent of Node:" + child.getParent());
			System.out.println(" Node:" + child.toString());
			System.out.println(" Qty " + child.getQtyPerParent() + ",\n Requirement: " + child.getRequirement()
					+ ",\n Shortage: " + child.getUpdatedreq() + ",\n Availability: " + child.getAvailability()
					+ ",\n Allocated:"+ child.getAllocated()+", Category:"+ child.getCategory());
			/*
											 * +", Code:"+ child.getCode()+
											 * ", Description:"+
											 * child.getDesc()+", Manufactured:"
											 * + child.getManufactured()+
											 * ", Stock:"+ child.getStock()+
											 * ", FG No.: "+
											 * child.getParentFG()+
											 * ", Material Group:"+
											 * child.getMatgrp()+
											 * ", Material Group Description:"+
											 * child.getMatgrpdesc()+
											 * ", Material Sub Group:"+
											 * child.getMatsubgrp()+
											 * ", Material Group Description:"+
											 * child.getMatsubgrpdesc()+
											 * ", Unit:"+ child.getUnit()+
											 * ", LeadTime:"+
											 * child.getLeadtime()+", Category:"
											 * + child.getCategory()+
											 * ", Nature Category:"+
											 * child.getNature_category()+
											 * ", Frequency Category:"+
											 * child.getFreq_category());
											 */
			System.out.println(" Children of Node:" + child.getChildren());	
			print(child);
		}
	}

	
	private void SaveDBRM(Node<T> node, int i) throws SQLException{
		
		for (Node<T> child : node.getChildren()) {
					Connection con = Connect.getconnection();
					PreparedStatement ps = con.prepareStatement(
					"INSERT INTO `star`.`rmshortage` (`Category`,`Material Group`,`Material Group Desc`,`Mat Sub-Group`,`Mat Sub-Group Desc`,`FGNo`,`Node`,`Unit`,`Requirement`,`Availability`,`Shortage`,`Parent`,`Stock`,`Week_No`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, (String) child.getCategory());
					ps.setString(2, (String) child.getMatgrp());
					ps.setString(3, (String) child.getMatgrpdesc());
					ps.setString(4, (String) child.getMatsubgrp());
					ps.setString(5, (String) child.getMatsubgrpdesc());
					ps.setString(6, (String) child.getParentFG());
					ps.setString(7, (String) child.getData());
					ps.setString(8, (String) child.getUnit());
					ps.setDouble(9, child.getRequirement());
					ps.setDouble(10, child.getStock());
					ps.setDouble(11, child.getUpdatedreq());
					ps.setString(12, (String) child.getParent().toString());
					ps.setDouble(13, child.getTemp_stock());
					ps.setInt(14, i);
					ps.executeUpdate();
					ps.close();
					con.close();
					SaveDBRM(child,i);
			}
		}

	public void genrateReport(int i) throws SQLException {
		SetQtyPerParent(getRoot(root));
		forwardTrack(getRoot(root));
		SetCate(getRoot(root));
		SaveDBRM(getRoot(root),i);
		BackTrack(getRoot(root));
		SaveDBSFG(getRoot(root),i);
		//print(getRoot(root));
	}
	private void SaveDBSFG(Node<T> node, int i) throws SQLException {
		/*if(node.toString().equals("A2C0098")||node.toString().equals("A2C009830")||node.toString().equals("2C0098")||node.toString().equals("2C0098100")||node.toString().equals("2C009860")||node.toString().equals("2C009840")||node.toString().equals("2C0006")||node.toString().equals("2C0087")||node.toString().equals("2C0087160")||node.toString().equals("2C0087130")||node.toString().equals("2C008780")||node.toString().equals("2C008770")||node.toString().equals("37283C")){
			System.out.println("Data: "+node.getData());
			System.out.println("Qty: "+node.getQtyPerParent());
			System.out.println("A: "+node.getAllocated());
			System.out.println("M: "+node.getManufactured());
			System.out.println("S: "+node.getStock());
			System.out.println("AVA: "+node.getAvailability());
		}*/
		for (Node<T> child : node.getChildren()) {
					Connection con = Connect.getconnection();
					PreparedStatement ps = con.prepareStatement("INSERT INTO `star`.`sfgshortage` (`Category`,`Material Group`,`Material Group Desc`,`Mat Sub-Group`,`Mat Sub-Group Desc`,`FGNo`,`Node`,`Unit`,`Requirement`,`Availability`,`Shortage`,`Parent`,`Stock`,`Manufactured`,`Week_No`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, (String) child.getCategory());
					ps.setString(2, (String) child.getMatgrp());
					ps.setString(3, (String) child.getMatgrpdesc());
					ps.setString(4, (String) child.getMatsubgrp());
					ps.setString(5, (String) child.getMatsubgrpdesc());
					ps.setString(6, (String) child.getParentFG());
					ps.setString(7, (String) child.getData());
					ps.setString(8, (String) child.getUnit());
					ps.setDouble(9, child.getRequirement());
					ps.setDouble(10, child.getTemp_stock()+child.getManufactured());
					ps.setDouble(11, SetShortage(child));
					ps.setString(12, (String) child.getParent().toString());
					ps.setDouble(13, child.getTemp_stock());
					ps.setDouble(14, child.getManufactured());
					ps.setInt(15, i);
					ps.executeUpdate();
					ps.close();
					con.close();
					SaveDBSFG(child,i);
			}
		
		
	}
	
	private double SetShortage(Node<T> child) {
		if(child.getTemp_stock()+child.getManufactured()>child.getRequirement()){
			return 0;
		}else{
		return Math.abs(child.getRequirement()-(child.getTemp_stock()+child.getManufactured()));	
		}
		
	}

	private void BackTrack(Node<T> node)
	{	   
	   for(Node<T> child: node.getChildren())
	   {
		   BackTrack(child);
	   }
	   if(node.toString().equals("Root")){}
	   else{
	   updatechildrenAvailability(node);}
	}
	@SuppressWarnings({ "unused", "null" })
	private void updatechildrenAvailability(Node<T> node)
	{
		
	   Double manu = null;	   
	   if(node.getChildren().isEmpty()&& node.getQtyPerParent()> 0){
		   //manu = (double) Math.round(node.getAllocated()/node.getQtyPerParent());
		   //node.getParent().setAllocated (manu + node.getParent().getAllocated());
	   }
	   else{	   
	   for(Node<T> child: node.getChildren())
	   {	   
		   if( child.getQtyPerParent() > 0){
			  manu = child.getAllocated()/child.getQtyPerParent();
	             if (manu < child.getParent().getManufactured())
	                {
	            	 child.getParent().setManufactured(manu);
	            	 //child.getParent().setAllocated (manu+child.getParent().getAllocated());
	            	 }
	             else
	             { 
	                manu = child.getParent().getManufactured();
	                child.getParent().setManufactured(manu);
	                //child.getParent().setAllocated (manu+child.getParent().getAllocated());
	              }
	             
		   }
		}
	   		node.setAllocated (node.getManufactured()+node.getAllocated());
	  }
	}
	
	
	@SuppressWarnings("unchecked")
	private void SetCate(Node<T> node) {
		if(node.getChildren().isEmpty()){
			node.setCategory((T)"ZRAW");
			//node.setManufactured(node.getAvailability());
		}
		else{
			for(Node<T> child: node.getChildren()){
				if(node.getParentFG().toString().equals(node.toString())){
					node.setCategory((T)"ZFIN");
				}
				else{
				node.setCategory((T)"ZSFG");
				}
				SetCate(child);
			}	
		}
	}

	
	public ArrayList<Node<T>> getPreOrderTraversal() {
		ArrayList<Node<T>> preOrder = new ArrayList<Node<T>>();
		buildPreOrder(root, preOrder);
		return preOrder;
	}

	private void SetQtyPerParent(Node<T> root2) {
		if (root2.toString().equals("Root")) {
			for(Node<T> child: root2.getChildren())
			{
			SetQtyPerParent(child);
			}
		} else {
			for(Node<T> child: root2.getChildren())
			{
			SetQtyPerParent(child);
			}
			root2.setQtyPerParent(root2.getQtyPerParent() / root2.getParent().getQtyPerParent());
		}
	}

	private void forwardTrack(Node<T> node) throws SQLException {
		for (Node<T> child : node.getChildren()) {
			setRequirementAsPerParent(child);
			UpdateRequirement(child);
			forwardTrack(child);
		}
	}
	public void UpdateRequirement(Node<T> child) throws SQLException {
		Connection con = Connect.getconnection();
		PreparedStatement ps = con.prepareStatement("select * from stock where `Row_Labels`=?");
		ps.setString(1, (String) child.getData());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			child.setAvailability(rs.getDouble("Temp_Stock"));
			//child.setStock(rs.getDouble("Sum_Qty"));
			child.setTemp_stock(rs.getDouble("Sum_Qty"));
			//child.setCategory((T) rs.getString("Category"));
			break;
		}
		ps.close();
		// System.out.println("Hello");
		check(child);
		PreparedStatement ps1 = con.prepareStatement("UPDATE stock SET Temp_Stock = ? WHERE `Row_Labels`=?");
		ps1.setDouble(1, child.getAvailability());
		ps1.setString(2, child.toString());
		ps1.executeUpdate();
		ps1.close();
		con.close();
	}

	private void check(Node<T> child) {
		if (child.getAvailability() >= child.getRequirement()) {
			child.setAllocated(child.getRequirement());
			child.setAvailability(child.getAvailability() - child.getRequirement());
			child.setUpdatedreq(0.0);
		} else { 
			child.setAllocated(child.getAvailability());
			child.setUpdatedreq(child.getRequirement() - child.getAllocated());
			child.setAvailability(0.0);
			
		}
		child.setStock(child.getAvailability());

	}
	public void setRequirementAsPerParent(Node<T> node) {
		node.setRequirement(node.getParent().getUpdatedreq() * node.getQtyPerParent());
		
	}
	
	private Node<T> getRoot(Node<T> node) {
		if (node.toString().equals("Root")) {
			return node;
		} else {
			node = node.getParent();
			node = getRoot(node);
		}
		return node;
	}

	public ArrayList<Node<T>> getPostOrderTraversal() {
		ArrayList<Node<T>> postOrder = new ArrayList<Node<T>>();
		buildPostOrder(root, postOrder);
		System.out.println(postOrder.toString());
		return postOrder;
	}

	private void buildPreOrder(Node<T> node, ArrayList<Node<T>> preOrder) {
		preOrder.add(node);
		for (Node<T> child : node.getChildren()) {
			buildPreOrder(child, preOrder);
		}
	}

	private void buildPostOrder(Node<T> node, ArrayList<Node<T>> postOrder) {
		for (Node<T> child : node.getChildren()) {
			buildPostOrder(child, postOrder);
		}
		postOrder.add(node);
	}

	public ArrayList<Node<T>> getLongestPathFromRootToAnyLeaf() {
		ArrayList<Node<T>> longestPath = null;
		int max = 0;
		for (ArrayList<Node<T>> path : getPathsFromRootToAnyLeaf()) {
			if (path.size() > max) {
				max = path.size();
				longestPath = path;
			}
		}
		return longestPath;
	}

	public int getMaxDepth() {
		return getLongestPathFromRootToAnyLeaf().size();
	}

	public ArrayList<ArrayList<Node<T>>> getPathsFromRootToAnyLeaf() {
		ArrayList<ArrayList<Node<T>>> paths = new ArrayList<ArrayList<Node<T>>>();
		ArrayList<Node<T>> currentPath = new ArrayList<Node<T>>();
		getPath(root, currentPath, paths);

		return paths;
	}

	private void getPath(Node<T> node, ArrayList<Node<T>> currentPath, ArrayList<ArrayList<Node<T>>> paths) {
		if (currentPath == null)
			return;

		currentPath.add(node);

		if (node.getChildren().size() == 0) {
			// This is a leaf
			paths.add(clone(currentPath));
		}
		for (Node<T> child : node.getChildren())
			getPath(child, currentPath, paths);

		int index = currentPath.indexOf(node);
		for (int i = index; i < currentPath.size(); i++)
			currentPath.remove(index);
	}

	private ArrayList<Node<T>> clone(ArrayList<Node<T>> list) {
		ArrayList<Node<T>> newList = new ArrayList<Node<T>>();
		for (Node<T> node : list)
			newList.add(new Node<T>(node));

		return newList;
	}
}