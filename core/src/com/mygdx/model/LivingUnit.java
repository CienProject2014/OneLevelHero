package com.mygdx.model;


public class LivingUnit extends Unit implements Comparable<LivingUnit> {
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int compareTo(LivingUnit obj) {
		if (this.getStatus().getSpd() == obj.getStatus().getSpd())
			return 0;
		else if (this.getStatus().getSpd() > obj.getStatus().getSpd())
			return 1;
		else
			return -1;
	}

}
