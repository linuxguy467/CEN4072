package Homework.homework4;

public interface Controller {
	Response processRequest(Request request);
	void addHandler(Request request, RequestHandler requestHandler);
}
