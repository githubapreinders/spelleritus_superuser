package afr.tafeltrainer3.client;

import com.google.gwt.user.cellview.client.CellTable;

public interface TableResources extends CellTable.Resources
{

	/**
	 * The styles applied to the table.
	 */
	interface TableStyle extends CellTable.Style
	{
	}
	@Override
	@Source({ CellTable.Style.DEFAULT_CSS, "celltable.css" })
	TableStyle cellTableStyle();

	
}