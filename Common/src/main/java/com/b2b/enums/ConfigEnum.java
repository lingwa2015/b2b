package com.b2b.enums;



public enum ConfigEnum {
	
		TRAN_SUM_DATE("TRAN_SUM_DATE", "核算日期");
		
		private String name;
		private String value;
		
		private ConfigEnum(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		
	}
