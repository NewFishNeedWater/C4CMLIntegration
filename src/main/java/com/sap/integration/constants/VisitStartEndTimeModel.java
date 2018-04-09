package com.sap.integration.constants;

import java.util.ArrayList;
import java.util.List;

import com.sap.integration.model.VisitTimeModel;

public class VisitStartEndTimeModel {
	
	public static final List<VisitTimeModel> DUMMY_VISITTIME_LIST = new ArrayList<>();
	
	static{
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("9:30AM","10:30AM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("9:00AM","10:00AM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("10:00AM","11:00AM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("10:30AM","11:30AM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("1:30PM","2:30PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("2:00PM","3:00PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("2:00PM","3:30PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("3:00PM","4:30PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("2:30PM","4:00PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("3:00PM","4:00PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("3:30PM","4:30PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("4:30PM","6:00PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("4:00PM","5:00PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("4:30PM","5:30PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("5:00PM","6:00PM"));
		DUMMY_VISITTIME_LIST.add(new VisitTimeModel("5:30PM","6:30PM"));
	}
	
	

}
