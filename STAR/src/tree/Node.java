package tree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.UpdatableResultSet;

import connection.Connect;

public class Node<T> {
	private T name;
	private List<Node<T>> children = null;
	private Node<T> parent;

	private T code;
	private T desc;
	private T parentFG;
	private T matgrp;
	private T matgrpdesc;
	private T matsubgrp;
	private T matsubgrpdesc;
	private T unit;
	private T leadtime;
	private T category;
	private T nature_category;
	private T freq_category;

	private double qtyPerParent;
	private double requirement;
	private double updatedreq;
	private double availability;
	private double manufactured;
	private double allocated;
	private double stock,temp_stock;

	public Node(T data, double qtyPerParent, double requirement, double updatedreq, double availability, T code, T desc,
			double manufactured, double allocated, double stock, T parentFG, T matgrp, T matgrpdesc, T matsubgrp,
			T matsubgrpdesc, T unit, T leadtime, T category, T nature_category, T freq_category) {
		this.name = data;
		this.qtyPerParent = qtyPerParent;
		this.requirement = requirement;
		this.updatedreq = updatedreq;
		this.availability = availability;
		this.code = code;
		this.desc = desc;
		this.manufactured = manufactured;
		this.allocated = allocated;
		this.stock = stock;
		this.parentFG = parentFG;
		this.matgrp = matgrp;
		this.matgrpdesc = matgrpdesc;
		this.matsubgrp = matsubgrp;
		this.matsubgrpdesc = matsubgrpdesc;
		this.unit = unit;
		this.leadtime = leadtime;
		this.category = category;
		this.nature_category = nature_category;
		this.freq_category = freq_category;

		this.children = new ArrayList<Node<T>>();
	}

	public Node(T data) {
		this.name = data;
		this.children = new ArrayList<Node<T>>();
	}

	public Node(Node<T> node) {
		this.name = (T) node.getData();
		this.qtyPerParent = node.getQtyPerParent();
		this.requirement = node.getRequirement();
		this.updatedreq = node.getUpdatedreq();
		this.availability = node.getAvailability();
		this.code = node.getCode();
		this.desc = node.getDesc();
		this.manufactured = node.getManufactured();
		this.allocated = node.getAllocated();
		this.stock = node.getStock();
		this.parentFG = node.getParentFG();
		this.matgrp = node.getMatgrp();
		this.matgrpdesc = node.getMatgrpdesc();
		this.matsubgrp = node.getMatsubgrp();
		this.matsubgrpdesc = node.getMatsubgrpdesc();
		this.unit = node.getUnit();
		this.leadtime = node.getLeadtime();
		this.category = node.getCategory();
		this.nature_category = node.getNature_category();
		this.freq_category = node.getFreq_category();
		this.temp_stock=node.getTemp_stock();
		children = new ArrayList<Node<T>>();
	}

	public T getName() {
		return name;
	}

	public void setName(T name) {
		this.name = name;
	}

	public double getTemp_stock() {
		return temp_stock;
	}

	public void setTemp_stock(double temp_stock) {
		this.temp_stock = temp_stock;
	}

	public void setQtyPerParent(double qtyPerParent) {
		this.qtyPerParent = qtyPerParent;
	}

	public void setRequirement(double requirement) {
		this.requirement = requirement;
	}

	public void setUpdatedreq(double updatedreq) {
		this.updatedreq = updatedreq;
	}

	public void setAvailability(double availability) {
		this.availability = availability;
	}

	public void setManufactured(double manufactured) {
		this.manufactured = manufactured;
	}

	public void setAllocated(double allocated) {
		this.allocated = allocated;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public void addChild(Node<T> child) {
		child.setParent(this);
		children.add(child);
	}

	public T getData() {
		return this.name;
	}

	public void setData(T data) {
		this.name = data;
	}

	public Double getQtyPerParent() {
		return this.qtyPerParent;
	}

	public void setQtyPerParent(Double qtyPerParent) {
		this.qtyPerParent = qtyPerParent;
	}

	public Double getRequirement() {
		return this.requirement;
	}

	public void setRequirement(Double requirement) {
		this.requirement = requirement;
	}

	public Double getUpdatedreq() {
		return this.updatedreq;
	}

	public void setUpdatedreq(Double updatedreq) {
		this.updatedreq = updatedreq;
	}

	public Double getAvailability() {
		return this.availability;
	}

	public void setAvailability(Double availability) {
		this.availability = availability;
	}

	public T getCode() {
		return this.code;
	}

	public void setCode(T code) {
		this.code = code;
	}

	public T getDesc() {
		return this.desc;
	}

	public void setDesc(T desc) {
		this.desc = desc;
	}

	public Double getManufactured() {
		return this.manufactured;
	}

	public void setManufactured(Double manufactured) {
		this.manufactured = manufactured;
	}

	public Double getAllocated() {
		return this.allocated;
	}

	public void setAllocated(Double allocated) {
		this.allocated = allocated;
	}

	public Double getStock() {
		return this.stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public T getParentFG() {
		return this.parentFG;
	}

	public void setParentFG(T parentFG) {
		this.parentFG = parentFG;
	}

	public T getMatgrp() {
		return this.matgrp;
	}

	public void setMatgrp(T matgrp) {
		this.matgrp = matgrp;
	}

	public T getMatgrpdesc() {
		return this.matgrpdesc;
	}

	public void setMatgrpdesc(T matgrpdesc) {
		this.matgrpdesc = matgrpdesc;
	}

	public T getMatsubgrp() {
		return this.matsubgrp;
	}

	public void setMatsubgrp(T matsubgrp) {
		this.matsubgrp = matsubgrp;
	}

	public T getMatsubgrpdesc() {
		return this.matsubgrpdesc;
	}

	public void setMatsubgrpdesc(T matsubgrpdesc) {
		this.matsubgrpdesc = matsubgrpdesc;
	}

	public T getUnit() {
		return this.unit;
	}

	public void setUnit(T unit) {
		this.unit = unit;
	}

	public T getLeadtime() {
		return this.leadtime;
	}

	public void setLeadtime(T leadtime) {
		this.leadtime = leadtime;
	}

	public T getCategory() {
		return this.category;
	}

	public void setCategory(T category) {
		this.category = category;
	}

	public T getNature_category() {
		return this.nature_category;
	}

	public void setNature_category(T nature_category) {
		this.nature_category = nature_category;
	}

	public T getFreq_category() {
		return this.freq_category;
	}

	public void setFreq_category(T freq_category) {
		this.freq_category = freq_category;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	public Node<T> getParent() {
		return this.parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return this.children;
	}

	public Node<T> getChildAt(int index) {
		return children.get(index);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;

		if (obj instanceof Node) {
			if (((Node<?>) obj).getData().equals(this.name))
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return this.name.toString();
	}
	/*
	@SuppressWarnings("unchecked")
	public void UpdateRequirement() throws SQLException {
		Connection con = Connect.getconnection();
		PreparedStatement ps = con.prepareStatement("select * from stock where `Row_Labels`=?");
		ps.setString(1, (String) this.getData());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.setAvailability(rs.getDouble("Temp_Stock"));
			this.setStock(rs.getDouble("Sum_Qty"));
			this.setCategory((T) rs.getString("Category"));
			break;
		}
		ps.close();
		// System.out.println("Hello");
		check();
		PreparedStatement ps1 = con.prepareStatement("UPDATE stock SET Temp_Stock = ? WHERE `Row_Labels`=?");
		ps1.setDouble(1, this.getAvailability());
		ps1.setString(2, this.toString());
		ps1.executeUpdate();
		ps1.close();
		con.close();
	}

	private void check() {
		// System.out.println(getAvailability());
		if (getAvailability() >= getRequirement()) {
			setAllocated(getRequirement());
			setAvailability(getAvailability() - getRequirement());
			setUpdatedreq(0.0);
		} else { // System.out.println(this.getAvailability());
					// System.out.println(this.getAllocated());
					// System.out.println("Hello");
			setAllocated(getAvailability());
			setUpdatedreq(getRequirement() - getAllocated());
			setAvailability(0.0);
		}

	}

	public void setRequirementAsPerParent() {
		
		setRequirement(getParent().getUpdatedreq() * getQtyPerParent());
		
	}*/
}
