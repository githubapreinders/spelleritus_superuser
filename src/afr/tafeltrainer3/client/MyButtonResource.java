package afr.tafeltrainer3.client;

import com.google.gwt.cell.client.ButtonCellBase;
import com.google.gwt.resources.client.ImageResource;

public interface MyButtonResource extends ButtonCellBase.DefaultAppearance.Resources {
	  @Override
	  @Source(value = {ButtonCellBase.DefaultAppearance.Style.DEFAULT_CSS, "buttonstyle.css"})
	  ButtonCellBase.DefaultAppearance.Style buttonCellBaseStyle();
	  public ImageResource cellTableLoading();
}