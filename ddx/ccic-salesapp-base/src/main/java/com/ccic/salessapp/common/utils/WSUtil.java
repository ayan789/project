package com.ccic.salessapp.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WSUtil {

	static	String wsdldir = System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? "d:" : "/tmp";

	static {
		String os=System.getProperty("os.name").toLowerCase();
		if(os.indexOf("windows")>=0)wsdldir=System.getProperty("user.dir");
	}
	
	public static JaxWsPortProxyFactoryBean create(String ServiceEndpointUrl,String serviceName,String portName, Class<?> serviceInterface) throws MalformedURLException {
		Service service = null;
		File dir = new File(wsdldir+"/wsdl"); if(!dir.exists()) { dir.mkdirs(); }

		File file = new File( dir.getPath() +"/"+ serviceName + ".wsdl");
    	if(file.exists()) {
    		file.delete();
    	}
    	log.info(file.getPath());
    	String uri = ServiceEndpointUrl+"?wsdl";
    	GetMethod method = new GetMethod(uri);
    	HttpClient  client=new HttpClient();
    	//client.getHostConfiguration().setProxy("10.1.27.102", 8080);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("mobile", "mobile"));
		log.info("request wsdl:" +uri);
		StringBuffer response= new StringBuffer();  
		try {
 			int statusCode = client.executeMethod(method);
 			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf8"));  
 			String line;  
 			FileOutputStream out = new FileOutputStream(file);
 			while ((line = reader.readLine()) != null) {  
                response.append(line);  
                out.write(line.getBytes());
            }  
            reader.close(); 
            out.flush();
            service = new WSUtil().new Service(response.toString());
			//log.info(" resp{"+response+"}");
		} catch (Exception e) {		
			log.error("http请求异常",e);
		}
    	
        JaxWsPortProxyFactoryBean proxy= new JaxWsPortProxyFactoryBean();
        proxy.setWsdlDocumentUrl(new URL("file:///"+file.getPath()));
        proxy.setEndpointAddress(ServiceEndpointUrl);
        proxy.setServiceName(serviceName);
        proxy.setPortName(portName);
        proxy.setServiceInterface(serviceInterface);
        proxy.setNamespaceUri(service.targetNamespace);
        proxy.setUsername("mobile");
        proxy.setPassword("mobile");
        return proxy;
	}
	
	public static JaxWsPortProxyFactoryBean create(String ServiceEndpointUrl , Class<?> serviceInterface) throws MalformedURLException {
		Service service = null;
		File dir = new File(wsdldir+"/wsdl"); if(!dir.exists()) { dir.mkdirs(); }
		 
		File file = new File( dir.getPath()  +"/"+ ServiceEndpointUrl.substring(ServiceEndpointUrl.lastIndexOf("/")) + ".wsdl");
    	if(file.exists()) {
    		file.delete();
    	}
    	log.info(file.getPath());
    	String uri = ServiceEndpointUrl+"?wsdl";
    	GetMethod method = new GetMethod(uri);
    	HttpClient  client=new HttpClient();
    	//client.getHostConfiguration().setProxy("10.1.27.102", 8080);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("mobile", "mobile"));
		log.info("request wsdl:" +uri);
		StringBuffer response= new StringBuffer();  
		try {
 			int statusCode = client.executeMethod(method);
 			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf8"));  
 			String line;  
 			FileOutputStream out = new FileOutputStream(file);
 			while ((line = reader.readLine()) != null) {  
                response.append(line);  
                out.write(line.getBytes());
            }  
            reader.close(); 
            out.flush();
            service = new WSUtil().new Service(response.toString());
			//log.info(" resp{"+response+"}");
		} catch (Exception e) {		
			log.error("http请求异常",e);
		}
    	
        JaxWsPortProxyFactoryBean proxy= new JaxWsPortProxyFactoryBean();
        proxy.setWsdlDocumentUrl(new URL("file:///"+file.getPath()));
        proxy.setEndpointAddress(ServiceEndpointUrl);
        proxy.setServiceName(service.serviceName);
        proxy.setPortName(service.portName);
        proxy.setServiceInterface(serviceInterface);
        proxy.setNamespaceUri(service.targetNamespace);
        proxy.setUsername("mobile");
        proxy.setPassword("mobile");
        return proxy;
	}
	
	class Service{
		String targetNamespace ;
		String serviceName;
		String bindingAddress;
		String portName;
		List<String> methodNameList = new ArrayList<String>();
		
		Service(String wsdl) throws Exception {
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true);
		    DocumentBuilder builder = factory.newDocumentBuilder();

		    InputStream in = new ByteArrayInputStream(wsdl.getBytes("utf-8"));
		    InputStreamReader isr = new InputStreamReader(in, "utf-8");

		    InputSource is = new InputSource(isr);
		    Document doc = builder.parse(is);

		    log.info("root:" + doc.getFirstChild().getNodeName());

		    NodeList nodeList = doc.getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/", "definitions");
		    Node root = nodeList.item(0);
		    NamedNodeMap map = root.getAttributes();
		    Node targetNs = map.getNamedItem("targetNamespace");
		    targetNamespace = targetNs.getNodeValue();
		    log.info("targetNamespace:" + targetNs.getNodeValue());

		    String wsdlNsUri = "http://schemas.xmlsoap.org/wsdl/";

		    NodeList bindingNodes = doc.getElementsByTagNameNS(wsdlNsUri, "service");
		    Node bindingNode = bindingNodes.item(0);
		    serviceName = bindingNode.getAttributes().getNamedItem("name").getNodeValue();
		    log.info("serviceName:" + serviceName);

		    NodeList portNodes = doc.getElementsByTagNameNS(wsdlNsUri, "port");
			portName = portNodes.item(0).getAttributes().getNamedItem("name").getNodeValue();
			log.info("portName:" + portName);
			 
			
		    
			/*
			 * Node addressNode = portNodes.item(1).getChildNodes().item(1); bindingAddress
			 * = addressNode.getAttributes().getNamedItem("location").getNodeValue();
			 * System.out.println("调用地址:" + bindingAddress);
			 */
		    NodeList portTypeNodes = doc.getElementsByTagNameNS(wsdlNsUri, "portType");
		    Node portType = portTypeNodes.item(0);
		    NodeList opNodes = portType.getChildNodes();
		    for (int i = 0; i < opNodes.getLength(); i++) {
		        Node node = opNodes.item(i);
		        if (node.getNodeType() == Node.ELEMENT_NODE && node.getLocalName().equalsIgnoreCase("operation")) {
		            String methodName = node.getAttributes().getNamedItem("name").getNodeValue();
		            methodNameList.add(methodName);
		            log.info("methodName:" + methodName);
		        }
		    }
		}
	}
	
	
	
}
