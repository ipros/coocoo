package com.coocoo.common.config;

import java.io.Serializable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

/**
 * 资源工厂
 * 加载配置文件
 * @author adam
 *
 */
public class PropertiesFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log  = Logger.getLogger(PropertiesFactory.class);

    private static final String  CONFIG_FILE = "config.properties";

    private static PropertiesConfiguration pc;

    private PropertiesFactory() {
    	
    }
    
    public synchronized static PropertiesConfiguration getInstance() {
        if (pc != null) {
            return pc;
        }
        try {
            pc = new PropertiesConfiguration(CONFIG_FILE);
            pc.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (ConfigurationException cexception) {
            log.error("read properties file error.", cexception);
            throw new RuntimeException(cexception);
        }
        return pc;
    }
	
}
