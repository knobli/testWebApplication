package foo;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch.grafstal.model.HibernateUtil;
import ch.grafstal.model.Member;

public class SortableMemberDataProvider extends SortableDataProvider<Member, String>
{
	
	SessionFactory sf;
	
    /**
     * constructor
     */
    public SortableMemberDataProvider()
    {
	    sf = HibernateUtil.getSessionFactory();
	    
        // set default sort
        setSort("firstName", SortOrder.ASCENDING);
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int, int)
     */
    public Iterator<? extends Member> iterator(long first, long count)
    {
    	Session session = sf.openSession();
   	 
	    Criteria criteria = session.createCriteria(Member.class);
	    criteria.setFirstResult((int) first);
	    criteria.setMaxResults((int) count);
	    Iterator<? extends Member> it = criteria.list().iterator();
	    session.close();
        return it;
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    public long size()
    {
    	Session session = sf.openSession();
      	 
	    Criteria criteria = session.createCriteria(Member.class);
	    List<Member> members = criteria.list();
	    session.close();
	    
    	return members.size();
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    public IModel<Member> model(Member object)
    {
    	return Model.of(object);
    }

}
