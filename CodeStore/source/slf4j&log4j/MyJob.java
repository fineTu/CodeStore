package util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.UserDao;

public class MyJob {
	static Logger logger = LoggerFactory.getLogger(MyJob.class.getName());
	public void work() {  
		logger.debug("===================debug!===================");
        System.out.println("date:" + new Date().toString());
    }  
}
