package Homework.homework4;

import com.googlecode.easymockrule.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * CRN: 10421
 * ID: CEN 4072
 * @author 0031
 * Assignment #4
 */
public class TestDefaultController {
	/**
	 * Global instance variables (Mocks, Rule and the test subject
	 * DefaultController Object).
	 */

	@Rule
	public EasyMockRule easyMockRule = new EasyMockRule(this);

	@TestSubject
	private DefaultController controller;

	@Mock
	private Request request1, request2;

	@Mock
	private RequestHandler handler1, handler2;

	@Mock
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
	 * @throws Exception -- Assertion Exception will be thrown if
	 * incorrect operation is performed
	 */
	@Before 
	public void setUp() throws Exception{
		controller = new DefaultController();
		request1 = createNiceMock(Request.class);
		handler1 = createNiceMock(RequestHandler.class);
		response1 = createNiceMock(Response.class);
		expect(request1.getName()).andReturn("request1");
		replay(request1);
		expect(handler1.process(request1)).andReturn(response1);
		replay(handler1);
		controller.addHandler(request1, handler1);
		request2 = createNiceMock(Request.class);
	 	expect(request2.getName()).andReturn("request2");
	 	replay(request2);
	 	handler2 = createNiceMock(RequestHandler.class);
	 	response2 = createNiceMock(Response.class);
	 	expect(handler2.process(request2)).andReturn(response2);
	 	replay(handler2);
	}

	/**
	 * Tears Down the objects when finished
	 *
	 * @throws Exception -- Assertion Exception will be thrown if
	 * incorrect operation is performed
	 */
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

	/**
	 * Checks to see if handler1 is the handler that is responding to
	 * the Request of request1
	 *
	 * @throws Exception -- Assertion Exception will be thrown if
	 * incorrect operation is performed
	 */
 	@Test
 	public void testGetHandler() throws Exception{
 		assertEquals(handler1, controller.getHandler(request1));
 	}

	/**
	 * Checks that Response response1 is what is processed by the controller
	 * from the Request of request1
	 *
	 * @throws Exception -- Assertion Exception will be thrown if
	 * incorrect operation is performed
	 */
 	@Test
 	public void testProcessRequest() throws Exception{	
 		assertEquals(response1, controller.processRequest(request1));
 	}

	/**
	 * Checks to see if an Exception is thrown when the controller
	 * attempts to find a handler for a request that has not been added for it.
	 * request2.addHandler has not been implemented for any requestHandler. Therefore it
	 * throws the RuntimeException
	 */
 	@Test(expected=RuntimeException.class)
 	public void testGetHandlerRuntime(){
 		controller.getHandler(request2);
 	}

	/**
	 * Checks to see if an exception is thrown when the controller attempts to
	 * add another handler on to request1 that has all ready been added to the requestHandler
	 * map. request1 is already mapped to RequestHandler handler1. Therefore when it tries to have
	 * handler2 mapped to it, it throws a RunTimeException.
	 */
 	@Test(expected=RuntimeException.class)
 	public void testAddRequestRuntime(){
 		controller.addHandler(request1, handler2);
 	}


}
