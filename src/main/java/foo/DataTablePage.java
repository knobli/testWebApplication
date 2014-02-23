package foo;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ch.grafstal.model.Member;

public class DataTablePage extends BasePage
{
    /**
     * constructor
     */
    public DataTablePage()
    {
        List<IColumn<Member, String>> columns = new ArrayList<IColumn<Member, String>>();

        columns.add(new AbstractColumn<Member, String>(new Model<String>("Actions"))
        {
            @Override
            public void populateItem(Item<ICellPopulator<Member>> cellItem, String componentId,
                IModel<Member> model)
            {
                cellItem.add(new ActionPanel(componentId, model));
            }
        });

        columns.add(new PropertyColumn(new Model<String>("ID"), "id")
        {
            @Override
            public String getCssClass()
            {
                return "numeric";
            }
        });

        columns.add(new PropertyColumn(new Model<String>("First Name"), "name", "name"));

        columns.add(new PropertyColumn(new Model<String>("Last Name"), "surname", "surname")
        {
            @Override
            public String getCssClass()
            {
                return "last-name";
            }
        });

        add(new DefaultDataTable("table", columns, new SortableMemberDataProvider(), 8));
    }
}
