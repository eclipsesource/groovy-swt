/*
 * Created on Feb 28, 2004
 *
 */
package groovy.jface.factory;

import groovy.swt.SwtUtils;
import groovy.swt.factory.WidgetFactory;
import groovy.util.FactoryBuilderSupport;

import java.util.Map;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

/**
 * @author <a href:ckl at dacelo.nl">Christiaan ten Klooster </a>
 * @version $Revision: 1557 $
 */
public class ViewerFactory extends WidgetFactory {

    /**
     * @param beanClass
     * @param style
     */
    public ViewerFactory(Class beanClass, int style) {
        super(beanClass, style);
    }

    /**
     * @param class1
     */
    public ViewerFactory(Class beanClass) {
        super(beanClass);
    }

	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
		Object parent = builder.getCurrent();
        Object bean;

        if (beanClass.equals(TableViewer.class) && (parent instanceof Table)) {
            bean = new TableViewer((Table) parent);

        } else if (beanClass.equals(TableTreeViewer.class) && (parent instanceof TableTree)) {
            bean = new TableTreeViewer((TableTree) parent);

        } else if (beanClass.equals(TreeViewer.class) && (parent instanceof Tree)) {
            bean = new TreeViewer((Tree) parent);

        } else if (beanClass.equals(CheckboxTreeViewer.class) && (parent instanceof Tree)) {
            bean = new CheckboxTreeViewer((Tree) parent);

        } else if (beanClass.equals(ComboViewer.class) && (parent instanceof Combo)) {
            bean = new ComboViewer((Combo) parent);

        } else if (beanClass.equals(ListViewer.class) && (parent instanceof List)) {
            bean = new ListViewer((List)parent);

        } else {
            Object parentWidget = SwtUtils.getParentWidget(parent, attributes);
            bean = createWidget(parentWidget);
        }

        setParent(builder, parent, bean);

        return bean;
    }
}