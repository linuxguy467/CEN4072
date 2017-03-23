package Homework.homework4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * CRN: 10465
 * Course ID: CEN 4072
 * @author 0031
 * Assignment #4
 */
public class TestDefaultController {
	/**
	 * Global instance variables
	 */
	private DefaultController controller;
	private Request request1, request2;
	private RequestHandler handler1, handler2;
	private Response response1, response2;

	/**
	 * Sets up the testing environment.
	 * Creates DefaultController Object as well as creating
	 * nice mocks for the Request, RequestHandler and the Response
	 * mocks. This uses EasyMock's createNiceMock() static method
	 * to do just that. Also, uses the expect() and replay() methods
	 * to add expected behaviors that the Request and RequestHandler mocks
	 * will perform and makes them ready for use respectively.
	 * Also it adds handlers for the RequestHandlers.
	 *
	 * @throws Exception
	 */
	@Before 
	public void setUp() throws Exception{
		String name = "request1";
		controller = new DefaultController();	//the default controller object to be used
		request1 = createNiceMock(Request.class);	//a mock of a request
		handler1 = createNiceMock(RequestHandler.class);	//mock of requestHandler
		response1 = createNiceMock(Response.class);		//mock of response2
		expect(request1.getName()).andReturn(name);	//adding a behavior or request mock
		replay(request1);	//makes request mock ready for use
		expect(handler1.process(request1)).andReturn(response1);	//adding a behavior to a requestHandelr mock
		replay(handler1);	//makes mock ready for use
		controller.addHandler(request1, handler1); //adds a handler to controller
		request2 = createNiceMock(Request.class);	//another mock of a request
	 	expect(request2.getName()).andReturn("request2");	//adds behavior to request mock
	 	replay(request2); // makes mock ready for use
	 	handler2 = createNiceMock(RequestHandler.class);	//another RequestHandler mock
	 	response2 = createNiceMock(Response.class);	//another Response mock
	 	expect(handler2.process(request2)).andReturn(response2);	//adding behavior to RequestHanderl mock
	 	replay(handler2); 	//makes mock ready for use
	}
	
	@After
	public void tearDown() throws Exception {
		controller = null;
		request1 =null;
		handler1 = null;
		response1 = null;
		request2 = null;
		handler2 = null;
		response2 = null;
	}
	
	//This method checks to see if handler1 is the handler that is responding to
	// the Request of request1
 	@Test
 	public void testGetHandler() throws Exception{
 		assertEquals(handler1, controller.getHandler(request1));
 	}
 	
 	//This method checks that Response response1 is what is processed by the controller
 	// from the Request of request1
 	@Test
 	public void testProcessRequest() throws Exception{	
 		assertEquals(response1, controller.processRequest(request1));
 	}
 	
 	//This method checks to see if an Exception is thrown when the controller
 	//tries to find a handler for a request that has not been added for it. 
 	//request2.addHandler has not been implemented for any requestHandler. Therefore it
 	//throws the RuntimeException
 	@Test(expected=RuntimeException.class)
 	public void testGetHandlerUnDefined(){
 		controller.getHandler(request2);
 	}
 	
 	//This method checks to see if an exception is thrown when the controller tries to 
 	//add another handler on to request that has all ready been added to the requestHanler
 	//map.  request1 is already mapped to RequestHandler handler1.  Therefore when it tries to have
 	//handler2 mapped to it, it throws a RunTimeException.
 	@Test(expected=RuntimeException.class)
 	public void testAddRequestDuplicateName(){
 		controller.addHandler(request1, handler2);
 	}


}
