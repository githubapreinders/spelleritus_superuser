package afr.tafeltrainer3.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;

public class DemoSuperuserPage extends Composite 

{
private FlexTable flex1;
	
	public DemoSuperuserPage()
	{
		flex1 = new FlexTable();
		initWidget(flex1);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "500px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		this.flex1.setStyleName("tafeltabel");
		Frame htmlframe = new Frame("/images/demo/demo_begeleider/index.html");
		htmlframe.setWidth("960px");
		htmlframe.setHeight("500px");
		flex1.setWidget(0, 0, htmlframe);
	}
	
}
