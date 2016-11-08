package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.DataOperationProducts;
import model.DataOperationRegLog;
import model.Seller;

@WebServlet(urlPatterns={"/signup","/login","/logout","/deleteSeller"})
public class LoginRegServlet extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		String url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf('/')+1);
		String page = "home.jsp";
		String error;
		String msg = "";
		switch(url)
		{
			case "login" : {
				String email = req.getParameter("email");
				String password = req.getParameter("pass");
				pw.print("here 1,no fail");
					if(email.equalsIgnoreCase("thisisadmin@omg.com") && password.equals("admin"))
					{
						HttpSession hs = req.getSession();
						msg = "Admin session started";
						hs.setAttribute("email_id", "thisisadmin@omg.com");
						hs.setAttribute("user_type", "admin");
						page = "admin/web/index.jsp";
						resp.sendRedirect(page);
						break;
					}
					else
					{
						try{
							
							int c = DataOperationRegLog.validateUser(email, password);
							pw.print(c);
							if(c == 5)
							{
								HttpSession hs = req.getSession();
								pw.print("here2 ,no fail"+c);
								page = "home.jsp";
								hs.setAttribute("email_id",email );
								hs.setAttribute("user_type", "customer");
								hs.setMaxInactiveInterval(-1);
								msg = "Customer session created";
								resp.sendRedirect("home.jsp");
							}														
							else if(c == 10)
							{
								HttpSession hs = req.getSession();
								pw.print("here 3,no fail");
								page = "seller/sellerHome.jsp";
								hs.setAttribute("email_id",email );
								hs.setAttribute("user_type", "seller");
								hs.setMaxInactiveInterval(-1);
								resp.sendRedirect("seller/sellerHome.jsp");
							}
							else
							{
								msg = "Authentication Failure";
								page = "error.jsp";
								pw.print("here ,fail");
								resp.sendRedirect("home.jsp");
								
							}
							
						}
						catch(Exception e)
						{
							error = e.getMessage();
							req.setAttribute("error", error);
							
						}
					}

				
			}
				break;
				
			case "signup" : 
			{
				pw.println("sdasdad");
				String email_id = req.getParameter("email");
				String password = req.getParameter("pass");
				String firstName = req.getParameter("fname");
				String middleName = req.getParameter("mname");
				String lastName = req.getParameter("lname");
				String address = req.getParameter("address");
				String pin = req.getParameter("pin");
				String state = req.getParameter("state");
				String city = req.getParameter("city");
				String regAs = req.getParameter("regAs");
				
				
					if(regAs.equals("seller"))
					{
						Seller s = new Seller();
						pw.print("signup"+regAs);
						s.setFirstName(firstName);
						s.setLastName(lastName);
						s.setAddress(address);
						s.setCity(city);
						s.setEmail_id(email_id);
						s.setMiddleName(middleName);
						s.setPin(pin);
						s.setPassword(password);
						s.setState(state);
						
						
						try{
							if(DataOperationRegLog.insertSeller(s))
							{
								msg = "Successfully added a seller , set to verify";
							}
							else
							{
								msg = "Could not add";
								page = "error.jsp";
							}
							
						}
						catch(Exception e)
						{
							error = e.getMessage();
							req.setAttribute("error", error);
							page = "error.jsp";
							
						}
					
					
						
					}
					else if(regAs.equals("customer"))
					{
						Customer c = new Customer();
						
						c.setFirstName(firstName);
						c.setLastName(lastName);
						c.setAddress(address);
						c.setCity(city);
						c.setEmail_id(email_id);
						c.setMiddleName(middleName);
						c.setPin(pin);
						c.setPassword(password);
						c.setState(state);
						try{
							
							boolean b;
							b = DataOperationRegLog.insertCustomer(c);
							pw.print("<br>"+b);
							if(b)
							{
								msg = "Successfully added a Customer ";
								
							}
							else
							{
								page = "error.jsp";
								msg = "Could not add";
							}
							
						}
						catch(Exception e)
						{
							error = e.getMessage();
							page = "error.jsp";
							req.setAttribute("error", error);
							
						}
						
					}
					else
					{
						page = "home.jsp";
						msg = "Select atleast if you are a seller or customer or whatever !";
					}
					req.setAttribute("msg", msg);
					RequestDispatcher rd = req.getRequestDispatcher(page);
					rd.forward(req, resp);
				
			}	
				break;
				
			case "logout":
			{
				page = "home.jsp";
				msg = "Successfully logged out !";
				HttpSession hs = req.getSession();
				hs.removeAttribute("user_type");
				
				hs.removeAttribute("email_id");
				req.setAttribute("msg", msg);
				RequestDispatcher rddd = req.getRequestDispatcher(page);
				rddd.forward(req, resp);
				break;
				
			}
			case "deleteSeller":
		 	{
		 		System.out.print("Id to delete : "+Integer.parseInt(req.getParameter("idToDelete")));
		 		
		 		try {
					if(DataOperationRegLog.deleteSeller(Integer.parseInt(req.getParameter("idToDelete"))))
							{
								msg = "deleted";
								System.out.print(msg);
								page = "admin/web/index.jsp";
								resp.sendRedirect(page);
							}
				}
				 catch (Exception e) {
					
					msg = "failed";
					System.out.print(msg);
					page = "admin/web/index.jsp";
					e.printStackTrace();
				}
		 		
		 		break;
		 	}
				
		}
		
		
	
		}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
