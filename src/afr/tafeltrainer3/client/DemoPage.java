package afr.tafeltrainer3.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;



public class DemoPage extends Composite 
{

private FlexTable flex1;
private Image img1;
public static int pagenumber = 1;
	
	public DemoPage()
	{
		flex1 = new FlexTable();
		initWidget(flex1);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "500px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		this.flex1.setStyleName("tafeltabel");
		img1 = new Image("/images/demo/demo"+String.valueOf(pagenumber)+".png");
		img1.addClickHandler(new image1ClickHandler());
		flex1.setWidget(0, 0, img1);
	}

	private class image1ClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			pagenumber++;
			img1 = new Image("/images/demo/demo"+String.valueOf(pagenumber)+".png");
			img1.addClickHandler(new image1ClickHandler());
			flex1.setWidget(0,0,img1);
			if(pagenumber>4)
			{
				pagenumber=0;
			}
		}
	}
}
