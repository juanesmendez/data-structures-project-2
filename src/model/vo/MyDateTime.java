package model.vo;

public class MyDateTime implements Comparable<MyDateTime>{
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minutes;
	private int seconds;
	private int nanoseconds;
	
	public MyDateTime(int year,int month,int day, int hour,int minutes, int seconds, int nanoseconds) {
		this.year=year;
		this.month = month;
		this.day=day;
		this.hour = hour;
		this.minutes = minutes;
		this.seconds = seconds;
		this.nanoseconds = nanoseconds;
	}
	
	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getNanoseconds() {
		return nanoseconds;
	}

	@Override
	public int compareTo(MyDateTime dt) {
		// TODO Auto-generated method stub
		if(this.year == dt.year) {
			if(this.month == dt.month) {
				if(this.day ==  dt.day) {
					if(this.hour == dt.hour) {
						if (this.minutes == dt.minutes) {
							if(this.seconds == dt.seconds) {
								if(this.nanoseconds == dt.nanoseconds) {
									return 0;
								}else {
									if(this.nanoseconds<dt.nanoseconds) {
										return -1;
									}else {
										return 1;
									}
								}
							}else {
								if(this.seconds< dt.seconds) {
									return -1;
								}else {
									return 1;
								}
							}
						}else {
							if(this.minutes< dt.minutes) {
								return -1;
							}else {
								return 1;
							}
						}
					}else {
						if(this.hour < dt.hour) {
							return -1;
						}else{
							return 1;
						}
					}
				}else {
					if(this.day < dt.day) {
						return -1;
					}else {
						return 1;
					}
				}
			}else {
				if(this.month < dt.month) {
					return -1;
				}else {
					return 1;
				}
			}
		}else {
			if(this.year < dt.year){
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.year+"-"+this.month+"-"+this.day+"T"+this.hour+":"+this.minutes+":"+this.seconds+"."+this.nanoseconds;
	}
	
}
