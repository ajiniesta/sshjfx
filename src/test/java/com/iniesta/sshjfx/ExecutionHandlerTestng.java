package com.iniesta.sshjfx;

import java.io.IOException;

import com.jcraft.jsch.JSchException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ExecutionHandlerTestng 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExecutionHandlerTestng( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ExecutionHandlerTestng.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws JSchException 
     */
    public void testApp() throws JSchException, IOException
    {
//    	ConnectionData cd = new ConnectionData("antonio", null, "htpc");
//    	Command command = new Command("python ~/apps/torrenthandler/torrenthandler/refresher.py");
//    	ExecutionResult result = ExecHandler.execCommand(cd, command);
//        assertNotNull(result);
//        assertEquals(0, result.getExitCode());
//        System.out.println("Result:\n"+result.getResult());
    	assertTrue(true);
    }
    
    public void testApp2() throws JSchException, IOException
    {
//    	ConnectionData cd = new ConnectionData("antonio", null, "htpc");
//    	Command command = new Command("reboot", true);
//    	ExecutionResult result = ExecHandler.execCommand(cd, command);
//        assertNotNull(result);
//        assertEquals(0, result.getExitCode());
//        System.out.println("Result:\n"+result.getResult());
    	assertTrue(true);
    }
}
