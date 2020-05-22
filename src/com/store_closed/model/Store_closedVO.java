package com.store_closed.model;

import java.sql.Date;

public class Store_closedVO {
	
		private String store_closed_id;
		private String store_id;
		private Date store_closed_day;
		
		public String getStore_closed_id() {
			return store_closed_id;
		}
		public void setStore_closed_id(String store_closed_id) {
			this.store_closed_id = store_closed_id;
		}
		public String getStore_id() {
			return store_id;
		}
		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}
		public Date getStore_closed_day() {
			return store_closed_day;
		}
		public void setStore_closed_day(Date store_closed_day) {
			this.store_closed_day = store_closed_day;
		}

}
