package com.wwmi.naier.bean;


import java.util.List;


public class KeepersType {

	private String keeperTypeID;

	private String typeName;

	private String typeCode;

	private String typeDescription;

	private String keeperNum;

	private List<TypeKeepers> keepers;

	public KeepersType(String keeperNum, String keeperTypeID, List<TypeKeepers> keepers, String typeCode,
			String typeDescription, String typeName) {

		this.keeperNum = keeperNum;
		this.keepers = keepers;
		this.keeperTypeID = keeperTypeID;
		this.typeCode = typeCode;
		this.typeDescription = typeDescription;
		this.typeName = typeName;
	}

	public String getKeeperTypeID() {

		return keeperTypeID;
	}

	public void setKeeperTypeID(String keeperTypeID) {

		this.keeperTypeID = keeperTypeID;
	}

	public String getTypeName() {

		return typeName;
	}

	public void setTypeName(String typeName) {

		this.typeName = typeName;
	}

	public String getTypeCode() {

		return typeCode;
	}

	public void setTypeCode(String typeCode) {

		this.typeCode = typeCode;
	}

	public String getTypeDescription() {

		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {

		this.typeDescription = typeDescription;
	}

	public String getKeeperNum() {

		return keeperNum;
	}

	public void setKeeperNum(String keeperNum) {

		this.keeperNum = keeperNum;
	}

	public List<TypeKeepers> getKeepers() {

		return keepers;
	}

	public void setKeepers(List<TypeKeepers> keepers) {

		this.keepers = keepers;
	}

	public static class TypeKeepers {

		private String keeperID;

		private String keeperName;

		private String keeperPhoto;

		public TypeKeepers(String keeperID, String keeperName, String keeperPhoto) {

			this.keeperID = keeperID;
			this.keeperName = keeperName;
			this.keeperPhoto = keeperPhoto;
		}

		public String getKeeperID() {

			return keeperID;
		}

		public void setKeeperID(String keeperID) {

			this.keeperID = keeperID;
		}

		public String getKeeperName() {

			return keeperName;
		}

		public void setKeeperName(String keeperName) {

			this.keeperName = keeperName;
		}

		public String getKeeperPhoto() {

			return keeperPhoto;
		}

		public void setKeeperPhoto(String keeperPhoto) {

			this.keeperPhoto = keeperPhoto;
		}
	}
}
