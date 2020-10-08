package com.hsbc.asset.model.bean;

import java.sql.Timestamp;

//Product class for storing product related details.


public class Item {
private int item_id;
private String item_name;
private String item_description;
private String item_type;
private int quantity;
private Timestamp date_issued;
private Timestamp date_return;
private Timestamp actual_return;
private int order_id;

public Timestamp getDate_issued() {
	return date_issued;
}
public Timestamp getDate_return() {
	return date_return;
}
public Timestamp getActual_return() {
	return actual_return;
}
public void setActual_return(Timestamp actual_return) {
	this.actual_return = actual_return;
}

public void setDate_issued(Timestamp date_issued) {
	this.date_issued = date_issued;
}
public void setDate_return(Timestamp date_return) {
	this.date_return = date_return;
}

public int getOrder_id() {
	return order_id;
}
public void setOrder_id(int order_id) {
	this.order_id = order_id;
}
public int getItem_id() {
	return item_id;
}
public void setItem_id(int item_id) {
	this.item_id = item_id;
}
public String getItem_name() {
	return item_name;
}

public Item() {
	super();
}

public void setItem_name(String item_name) {
	this.item_name = item_name;
}
public String getItem_description() {
	return item_description;
}
public void setItem_description(String item_description) {
	this.item_description = item_description;
}
public String getItem_type() {
	return item_type;
}
public void setItem_type(String item_type) {
	this.item_type = item_type;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Override
public String toString() {
	return "Item [item_id=" + item_id + ", item_name=" + item_name + ", item_description=" + item_description
			+ ", item_type=" + item_type + ", quantity=" + quantity + ", date_issued=" + date_issued + ", date_return="
			+ date_return + ", actual_return=" + actual_return + ", order_id=" + order_id + "]";
}

}
