package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class GoToBookingsPageCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToBookingsPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Booking> bookings = null;
		try {
			if (request.getSession().getAttribute(Utility.ROLE).toString().equals(Role.ADMIN.toString())) {
				bookings = new ArrayList<Booking>(ServiceFactory.getInstance().getStaysService().getAllBookings());
			} else {
				String userLogin = (String) request.getSession().getAttribute(Utility.LOGIN);
				bookings = new ArrayList<Booking>(
						ServiceFactory.getInstance().getStaysService().getAllUserBookings(userLogin));
			}
			for (Booking booking : bookings) {
				BigDecimal bookingPrice = ServiceFactory.getInstance().getStaysService().getBookingPrice(booking);
				User user = ServiceFactory.getInstance().getUserService().findUserById(booking.getUserId());
				if(user.getRole().equals(Role.CLIENT)) {
					Client client = ServiceFactory.getInstance().getUserService().findClientByUserId(booking.getUserId());
					if(ServiceFactory.getInstance().getUserService().isRegularCustomer(client.getClientId())) {
						RegularClient regularClient = ServiceFactory.getInstance().getUserService().findRegularClientByClientId(client.getClientId());
						BigDecimal discountValue = bookingPrice.multiply(BigDecimal.valueOf(regularClient.getDiscount())).divide(BigDecimal.valueOf(100));
						bookingPrice = bookingPrice.subtract(discountValue);
					}
				}
				booking.setPrice(bookingPrice);
			}
			Collections.sort(bookings,
					Comparator.comparing(Booking::isApproved, Comparator.nullsFirst(Comparator.naturalOrder())));
			request.setAttribute(Utility.BOOKINGS, bookings);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_BOOKINGS_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while going to bookings page", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
