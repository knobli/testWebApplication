package foo;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ch.grafstal.model.Member;

public class BasePage extends WebPage
{
    private Member selected;

    
    /**
     * Constructor
     */
    public BasePage()
    {
        add(new Label("selectedLabel", new PropertyModel<String>(this, "selectedContactLabel")));
        add(new FeedbackPanel("feedback"));
        
        SortableMemberDataProvider dp = new SortableMemberDataProvider();
        final DataView<Member> dataView = new DataView<Member>("sorting", dp)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<Member> item)
            {
            	Member member = item.getModelObject();
                item.add(new ActionPanel("actions", item.getModel()));
                item.add(new Label("contactid", String.valueOf(member.getId())));
                item.add(new Label("firstname", member.getName()));
                item.add(new Label("lastname", member.getSurname()));

                item.add(AttributeModifier.replace("class", new AbstractReadOnlyModel<String>()
                {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject()
                    {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));
            }
        };

        dataView.setItemsPerPage(8);

        add(new OrderByBorder("orderByFirstName", "firstName", dp)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged()
            {
                dataView.setCurrentPage(0);
            }
        });

        add(new OrderByBorder("orderByLastName", "lastName", dp)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged()
            {
                dataView.setCurrentPage(0);
            }
        });

        add(dataView);

        add(new PagingNavigator("navigator", dataView));        
    }

    /**
     * @return string representation of selected contact property
     */
    public String getSelectedContactLabel()
    {
        if (selected == null)
        {
            return "No Contact Selected";
        }
        else
        {
            return selected.getName();
        }
    }

    /**
     * 
     */
    class ActionPanel extends Panel
    {
        /**
         * @param id
         *            component id
         * @param model
         *            model for contact
         */
        public ActionPanel(String id, IModel<Member> model)
        {
            super(id, model);
            add(new Link("select")
            {
                @Override
                public void onClick()
                {
                    selected = (Member)getParent().getDefaultModelObject();
                }
            });
        }
    }

    /**
     * @return selected contact
     */
    public Member getSelected()
    {
        return selected;
    }

    /**
     * sets selected contact
     * 
     * @param selected
     */
    public void setSelected(Member selected)
    {
        addStateChange();
        this.selected = selected;
    }
    

}
