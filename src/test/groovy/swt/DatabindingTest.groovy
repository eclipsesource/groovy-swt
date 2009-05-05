/*
 * Created on Feb 25, 2004
 *
 */
package groovy.swt

import groovy.beans.Bindable
import groovy.lang.GroovyClassLoader
import groovy.lang.GroovyObject

import java.io.File

import junit.framework.TestCase

import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.widgets.Shell

@Bindable
public class TestPerson {
	String name
}

/**
 * @author <a href="mailto:ckl@dacelo.nl">Christiaan ten Klooster </a>
 * @version $Revision: 915 $
 */
public class DatabindingTest extends TestCase {
	 
	@Bindable
	String text
	@Bindable
	int number
    @Bindable
	WritableList list 
	
	def swt = new SwtBuilder()
	 
    public void testSimpel() {
		//def t1, t2
        def shell = swt.shell() {
        	text(id:'t1', text: bind(model: this, modelProperty:'text'))
        	text(id:'t2', text: bind(model: this, modelProperty:'number'))
        }
        
        text = "something"
        swt.dataBindingContext.updateTargets()
        assert swt.t1.text == "something"

        swt.t1.text = "something else"
        swt.dataBindingContext.updateModels()
        assert text == "something else"
        shell.dispose()
    }
    
    public void testSimple() {
          def swt = new SwtBuilder()
          swt.shell('title') {
          }
      }
    
    
    public void testBindingToEmptyList() {
    	list = new WritableList(swt.realm)
        def shell = swt.shell() {
    		table(id:'t') {
				tableViewer(input: bind(model:list, modelProperty:['name']))
    		}
        }
    	assert t.itemCount == 0
    	list.add(new TestPerson(name: 'name'))
        swt.dataBindingContext.updateModels()
    	assert t.itemCount == 1
    }
    
}
