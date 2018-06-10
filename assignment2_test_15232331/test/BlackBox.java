/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.net.URI;
import net.sourceforge.jwebunit.junit.WebTestCase;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.conn.params.ConnConnectionPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author User
 */
public class BlackBox {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    
    public BlackBox() throws Exception {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test(timeout=5000) 
    public void testingMoveActiveSession() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/move");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri);
        HttpResponse response = httpClient.execute(request);
        
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 404 Not Found"));
    }
    
    @Test(timeout=5000) 
    public void testingStateActiveSession() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/state");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri);
        HttpResponse response = httpClient.execute(request);
        
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 404 Not Found"));
    }
    
    @Test(timeout=5000) 
    public void testingWonActiveSession() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/won");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri); 
        HttpResponse response = httpClient.execute(request);
        
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 404 Not Found"));
    }
    
    @Test(timeout=5000) 
    public void testingPossibleMovesActiveSession() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/possiblemoves");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri);
        HttpResponse response = httpClient.execute(request);
        
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 404 Not Found"));
    }
    
    
    
    
    @Test(timeout=5000)
    public void testingUstartSuccess() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/ustart");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri); 
        HttpResponse response = httpClient.execute(request);
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 200 OK"));
    }
    
    @Test(timeout=5000)
    public void testingIstartSuccess() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/istart");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri);
        HttpResponse response = httpClient.execute(request);
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 200 OK"));
    }
    
    @Test(timeout=5000)
    public void testingMoveParameterFormating() throws Exception {
        
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/move")
                .setParameter("move", "x4y1");
        URI uri = builder.build();
        
        HttpPost request = new HttpPost(uri);  
        HttpResponse response = httpClient.execute(request);
        
        String status = response.getStatusLine().toString();
        
        assertTrue(status.contains("HTTP/1.1 400 Bad Request"));
    }
    
    @Test(timeout=5000) 
    public void testingStateReturnContent() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/state")
                .setParameter("format", "txt");
        URI uri = builder.build();
        
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);
        
        String content = EntityUtils.toString(response.getEntity());
        
        assertTrue(content.contains("_ _ _ \n_ _ _ \n_ _ _ "));
    }
    
    @Test(timeout=5000) 
    public void testingWonReturnContent() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/won");
        URI uri = builder.build();
        
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);
        
        String content = EntityUtils.toString(response.getEntity());
        
        assertTrue(content.contains("user"));
    }
    
    @Test() 
    public void testingPossibleMovesReturnContent() throws Exception {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost("localhost:8084")
                .setPath("/assignment2_server_15232331/possiblemoves");
        URI uri = builder.build();
        
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);
        
        String content = EntityUtils.toString(response.getEntity());
        
        assertTrue(content.contains("0,0\n1,0\n2,0\n0,1\n1,1\n2,1\n0,2\n1,2\n2,2\n"));
    }   
}
