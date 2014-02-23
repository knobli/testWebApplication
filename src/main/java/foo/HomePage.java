package foo;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch.grafstal.model.Event;
import ch.grafstal.model.HibernateUtil;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {

		 add(new Label("message", "Hello World!"));
		    SessionFactory sf = HibernateUtil.getSessionFactory();
		    Session session = sf.openSession();
		 
		    List<Event> events = session.createCriteria(Event.class).list();
		    //session.close();
		    
		    for(Event event : events){
		    	System.out.println(event);
		    }
		    
		    add(new BookmarkablePageLink("link", BasePage.class));

    }
}
