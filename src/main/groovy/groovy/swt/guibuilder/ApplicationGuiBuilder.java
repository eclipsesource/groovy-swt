/*
 * Created on Mar 1, 2004
 *
 */
package groovy.swt.guibuilder;

import groovy.jface.JFaceBuilder;
import groovy.jmx.builder.JmxBuilder;
import groovy.util.GroovyScriptEngine;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * WORK IN PROGRESS, don't use this one yet
 * 
 * This will be the main guibuilder
 * 
 * TODO provide entry point for pull tools (e.g. i18n, security) 
 * 
 * @author <a href:ckl at dacelo.nl">Christiaan ten Klooster </a>
 * @version $Revision: 1141 $
 */
public class ApplicationGuiBuilder extends JFaceBuilder {
    /** the logger */
    private Logger log = Logger.getLogger(getClass().getName());

    /** the caching script engine */
    private GroovyScriptEngine scriptEngine;

    /**
     * the constructor
     * 
     * @param rootUrl
     * @throws IOException
     */
    public ApplicationGuiBuilder(String rootUrl) throws IOException {
    	super(true);
        scriptEngine = new GroovyScriptEngine(rootUrl);
    }

    /*
     * @see groovy.swt.SwtBuilder#registerWidgets()
     */
    public void registerApplicationWidgets() {
        registerFactory("rebuild", new RebuildFactory(this));
        registerFactory("runScript", new RunScriptFactory(this));
    }
    
    /**
     * @return Returns the scriptEngine.
     */
    public GroovyScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    
}
