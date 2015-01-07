package afr.tafeltrainer3.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class AlertWidget extends Composite 
{
public DialogBox box;
public String header;
public Label contentlabel;	


	public AlertWidget(tafeltrainer3messages messages)
	{
		box = new DialogBox();
        contentlabel = new Label();
		header = "";
        box.setPixelSize(300, 400);
        box.setStyleName("popup2");
        VerticalPanel panel = new VerticalPanel();
        panel.setBorderWidth(0);
        box.setText(header);
        Image img1 = new Image("/images/startpage_curious.png");
        img1.setHeight("200px");
        panel.add(img1);
        panel.add(contentlabel);
        Button buttonClose = new Button(messages.utilities_sluitbutton()) ;
        buttonClose.addClickHandler(new buttonCloseClickHandler());
        buttonClose.setStyleName("standardbutton");
        Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
        panel.add(buttonClose);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_CENTER);
        box.add(panel);
	}
	
	public class buttonCloseClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			box.hide();
		}
	}

	
	public DialogBox getBox() {
		return box;
	}

	

	public String getHeader() {
		return header;
	}



	public Label getContentlabel() {
		return contentlabel;
	}



	public void setHeader(String header) {
		this.header = header;
	}

	public void setContentlabel(Label contentlabel) {
		this.contentlabel = contentlabel;
	}

}
