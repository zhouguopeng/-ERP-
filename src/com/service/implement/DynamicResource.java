package com.service.implement;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.Resource;

import com.model.DynamicBean;

public class DynamicResource implements Resource {  
    private DynamicBean dynamicBean;  
      
    public DynamicResource(DynamicBean dynamicBean){  
        this.dynamicBean = dynamicBean;  
    }  
    /* (non-Javadoc) 
     * @see org.springframework.core.io.InputStreamSource#getInputStream() 
     */  
    public InputStream getInputStream() throws IOException {  
        return new ByteArrayInputStream(dynamicBean.getXml().getBytes("UTF-8"));  
    }
    
	@Override
	public long contentLength() throws IOException {
		return 0;
	}
	@Override
	public Resource createRelative(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public File getFile() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public URI getURI() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public URL getURL() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isReadable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public long lastModified() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}  
}  
