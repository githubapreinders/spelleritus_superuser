package afr.tafeltrainer3.client;

import java.math.BigDecimal;

import afr.tafeltrainer3.shared.SuperUser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Utilities {

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
	
	public static DialogBox alertWidget(final String header, final String content,final tafeltrainer3messages messages) {
        final DialogBox box = new DialogBox();
        box.setPixelSize(300, 400);
        box.setStyleName("popup2");
        final VerticalPanel panel = new VerticalPanel();
        panel.setBorderWidth(0);
        //TODO
        box.setText(header);
        Image img1 = new Image("/images/startpage_curious.png");
        img1.setHeight("200px");
        panel.add(img1);
        panel.add(new Label(content));
        final Button buttonClose = new Button(messages.utilities_sluitbutton(),new ClickHandler() {
           
			@Override
			public void onClick(ClickEvent event) {
				 box.hide();				
			}
        });
        buttonClose.setStyleName("standardbutton");
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
        panel.add(buttonClose);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_CENTER);
        box.add(panel);
        return box;
    }

	public static DialogBox alertWidgetButtonVerifMail(String header,
			String content, tafeltrainer3messages messages,final SuperUser superuser,final MainView main)
	{
		final DialogBox box = new DialogBox();
        box.setPixelSize(300, 400);
        box.setStyleName("popup2");
        final VerticalPanel panel = new VerticalPanel();
        panel.setBorderWidth(0);
        box.setText(header);
        Image img1 = new Image("/images/startpage_curious.png");
        img1.setHeight("200px");
        panel.add(img1);
        panel.add(new Label(content));
        final Button buttonClose = new Button(messages.utilities_sluitbutton(),new ClickHandler() {
           
			@Override
			public void onClick(ClickEvent event) {
				 box.hide();				
			}
        });
        buttonClose.setStyleName("standardbutton");
        
        final Button buttonnewmail = new Button("Stuur mail", new ClickHandler(){

			@Override
			public void onClick(ClickEvent event)
			{
				main.client.sendAnotherVerificationMail(superuser);
				box.hide();
				
			}});
        buttonnewmail.setStyleName("standardbutton");

        
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
       HorizontalPanel hpanel = new HorizontalPanel();
       hpanel.add(buttonClose);
       hpanel.add(buttonnewmail);
       hpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(hpanel);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_CENTER);
        box.add(panel);
        return box;

	}

	
	
}
