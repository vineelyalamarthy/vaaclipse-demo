/**
 * 
 */
package org.semanticsoft.vaaclipsedemo.cassandra.app.views;

import com.vaadin.shared.ui.MarginInfo;

import com.vaadin.ui.themes.Reindeer;

import com.vaadin.ui.themes.BaseTheme;

import org.apache.commons.io.IOUtils;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
 * @author rushan
 *
 */
public class Help
{
	private Panel panel;
	private Label content;
	
	@Inject
	public void Help(VerticalLayout parent, IEclipseContext context)
	{
		parent.setMargin(new MarginInfo(false, false, false, true));
		panel = new Panel();
		panel.addStyleName(Reindeer.PANEL_LIGHT);
		panel.setSizeFull();
		parent.addComponent(panel);
	}
	
	@PostConstruct
	public void loadHelpText()
	{
		String helpPath = "platform:/plugin/org.semanticsoft.vaaclipsedemo.cassandra.app/data/Help.html";
		try {
			URL cssUrl = new URL(helpPath);
			InputStream is = cssUrl.openStream();
			
			try {
				String contentString = IOUtils.toString(is);
				content = new Label(contentString, Label.CONTENT_XHTML);
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			content = new Label("Can not open file " + helpPath);
		}
		finally {
			if (content != null) {
				content.addStyleName("textview");
				panel.setContent(content);	
			}
		}
	}
}
