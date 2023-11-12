package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.auth.AuthenticationService;
import com.carbooking.carbookingonlineserver.auth.RegisterRequest;
import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.reponse.*;
import com.carbooking.carbookingonlineserver.dto.repuest.BookingRequest;
import com.carbooking.carbookingonlineserver.entity.*;
import com.carbooking.carbookingonlineserver.repository.BookingRepository;
import com.carbooking.carbookingonlineserver.repository.DriverTripRepository;
import com.carbooking.carbookingonlineserver.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.*;

@Service
public class ImplBooking implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DriverTripRepository driverTripRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DriverTripService driverTripService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SeatService seatService;
    @Autowired
    private CarService carService;
    @Autowired
    private DetailDropOffLocationService detailDropOffLocationService;
    @Autowired
    private DetailPickUpLocationService detailPickUpLocationService;
    @Autowired
    private  AuthenticationService service;

    private Set<Seat> handleSeats(String seats, DriverTrip driverTrip){
        String[] split = seats.split("-");
        Set<Seat> list = driverTrip.getSeats();
        for(String seat: split){
            Long id = Long.parseLong(seat);
            Seat seat1 = seatService.getByid(id);

            if(seat1 == null){
                return null;
            }
            if(list.contains(seat1)){
                return null;
            }

            list.add(seat1);
        }
        return  list;
    }
    private Set<Seat> handleSeats_(String seats, DriverTrip driverTrip){
        String[] split = seats.split("-");
        Set<Seat> list = new HashSet<>();
        for(String seat: split){
            Long id = Long.parseLong(seat);
            Seat seat1 = seatService.getByid(id);

            if(seat1 == null){
                return null;
            }
            if(driverTrip.getSeats().contains(seat1)){
                return null;
            }

            list.add(seat1);
        }
        return  list;
    }

    private BookingReponse convertEntityToReponseBooking(Booking booking){
        BookingReponse bookingReponse = new BookingReponse();
        bookingReponse.setId(booking.getId());
        bookingReponse.setFareAmount(booking.getFareAmount());
        bookingReponse.setStatus(booking.getStatus());
        bookingReponse.setDescription(bookingReponse.getDescription());
        bookingReponse.setIsProtect(booking.getIsProtect());
        bookingReponse.setDate(booking.getDate());

        if(booking.getPickupLocation() != null){
            LocationDetailReponse locatePickUp = new LocationDetailReponse();
            locatePickUp.setId(booking.getPickupLocation().getId());
            locatePickUp.setDetailLocation(booking.getPickupLocation().getDetailLocation());
            locatePickUp.setTime(booking.getPickupLocation().getTime());
            bookingReponse.setLocationDetailPickUp(locatePickUp);
        }


        if(booking.getDropoffLocation() != null){
            LocationDetailReponse locateDropOff = new LocationDetailReponse();
            locateDropOff.setId(booking.getDropoffLocation().getId());
            locateDropOff.setDetailLocation(booking.getDropoffLocation().getDetailLocation());
            locateDropOff.setTime(booking.getDropoffLocation().getTime());
            bookingReponse.setLocationDetailDropOff(locateDropOff);
        }


        UserReponse user = new UserReponse(
                booking.getUser().getPhone(),
                booking.getUser().getEmail(),
                booking.getUser().getPassword(),
                booking.getUser().getName(),
                booking.getUser().getPoints()
                );
        bookingReponse.setUser(user);

        CarReponse car = new CarReponse(
                booking.getCar().getLicenseplates(),
                booking.getCar().getAvailability(),
                booking.getCar().getTypeCar().getId(),
                booking.getCar().getTypeCar().getName(),
                booking.getCar().getTypeCar().getNumberOfSeats()
                );
        bookingReponse.setCar(car);

        DriverTripReponse drivertrip = new DriverTripReponse(
                booking.getDriverTrip().getId(),
                booking.getDriverTrip().getDate(),
                booking.getDriverTrip().getStatus(),

                booking.getDriverTrip().getTrip().getId(),
                booking.getDriverTrip().getTrip().getPickupLocation(),
                booking.getDriverTrip().getTrip().getDropoffLocation(),
                booking.getDriverTrip().getTrip().getPickupTime(),
                booking.getDriverTrip().getTrip().getDropoffTime(),
                booking.getDriverTrip().getTrip().getPrice()
        );
        bookingReponse.setDrivertrip(drivertrip);

        Set<Seat> seats = booking.getSeats();
        List<SeatReponse> seatReponses = new ArrayList<>();
        for (Seat s : seats){
            SeatReponse seatReponse = new SeatReponse(s.getId(),s.getName());
            seatReponses.add(seatReponse);
        }
        bookingReponse.setSeats(seatReponses);



        return bookingReponse;
    }

    private List<BookingReponse> convertList(List<Booking> list_){
        List<BookingReponse> list = new ArrayList<>();
        for(Booking booking: list_){
            BookingReponse s = this.convertEntityToReponseBooking(booking);
            list.add(s);
        }
        return list;
    }

    @Override
    public BookingReponse addBooking(BookingRequest bookingRequest) {
        Booking booking = mapper.map(bookingRequest,Booking.class);

        User user = userService.findByPhone(bookingRequest.getPhoneUser());
        if(user == null){
            RegisterRequest request = new RegisterRequest("Đặt Ngoài",bookingRequest.getPhoneUser(),bookingRequest.getPhoneUser() + "@gmail.com","password", Role.USER,"password");
            service.register(request);
        }

        DriverTrip driverTrip = driverTripService.getOne(bookingRequest.getIddriverTrip());

        Set<Seat> seats = handleSeats_(bookingRequest.getListIdSeat(), driverTrip);

        Car car = carService.getCarById(bookingRequest.getIdCar());
        user = userService.findByPhone(bookingRequest.getPhoneUser());
        DetailPickUpLocation detailPickUpLocation = null;
        if(bookingRequest.getIdPickUp() != null){
            detailPickUpLocation = detailPickUpLocationService.getDetailByil(bookingRequest.getIdPickUp());
        }
        DetailDropOffLocation detailDropOffLocation = null;
        if(bookingRequest.getIdDropOff() != null){
            detailDropOffLocation = detailDropOffLocationService.getDetailByil(bookingRequest.getIdDropOff());
        }
        if(user == null || driverTrip == null || seats == null || car == null
        ){
            return null;
        }
        Set<Seat> seats_ = new HashSet<>();
        if(seats != null){
            for(Seat seat : seats){
                seats_.add(seatService.getByid(seat.getId()));
            }
        }

        Set<Seat> seat_ = handleSeats(bookingRequest.getListIdSeat(), driverTrip);
        driverTrip.setSeats(seat_);
        driverTrip = driverTripRepository.save(driverTrip);

        booking.setUser(user);
        booking.setCar(car);
        booking.setSeats(seats_);
        booking.setDriverTrip(driverTrip);
        booking.setDropoffLocation(detailDropOffLocation);
        booking.setPickupLocation(detailPickUpLocation);

        return convertEntityToReponseBooking(bookingRepository.save(booking)) ;
    }

    @Override
    public BookingReponse getBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(optionalBooking.isPresent()){
            return  convertEntityToReponseBooking(optionalBooking.get());
        }
        return null;
    }

    @Override
    public List<BookingReponse> getListBookingByDriverTrip(Long iddrivertrip) {
        DriverTrip driverTrip = driverTripService.getOne(iddrivertrip);
        if(driverTrip != null){
            return convertList(bookingRepository.findByDriverTrip(driverTrip)) ;
        }
        return null;
    }

    @Override
    public List<BookingReponse> getListBookingByUser(String phoneUser) {
        User user = userService.findByPhone(phoneUser);
        if(user != null){
            return  convertList(bookingRepository.findByUser(user));
        }
        return null;
    }

    @Override
    public List<BookingReponse> addListBooking(List<BookingRequest> list) {
        List<Booking> bookings = new ArrayList<>();
        for (BookingRequest bookingRequest : list) {
            Booking booking = mapper.map(bookingRequest, Booking.class);
            User user = userService.findByPhone(bookingRequest.getPhoneUser());
            if(user == null){
                RegisterRequest request = new RegisterRequest("Đặt Ngoài",bookingRequest.getPhoneUser(),bookingRequest.getPhoneUser() + "@gmail.com","password", Role.USER,"password");
                service.register(request);
            }
            DriverTrip driverTrip = driverTripService.getOne(bookingRequest.getIddriverTrip());
            Car car = carService.getCarById(bookingRequest.getIdCar());
            user = userService.findByPhone(bookingRequest.getPhoneUser());
            DetailPickUpLocation detailPickUpLocation = null;
            if(bookingRequest.getIdPickUp() != null){
                detailPickUpLocation = detailPickUpLocationService.getDetailByil(bookingRequest.getIdPickUp());
            }
            DetailDropOffLocation detailDropOffLocation = null;
            if(bookingRequest.getIdDropOff() != null){
                detailDropOffLocation = detailDropOffLocationService.getDetailByil(bookingRequest.getIdDropOff());
            }
            Set<Seat> seats = handleSeats(bookingRequest.getListIdSeat(), driverTrip);
            if (user == null || driverTrip == null || seats == null || car == null) {
                return null;
            }
            Set<Seat> seats_ = new HashSet<>();
            if (seats != null) {
                for (Seat seat : seats) {
                    seats_.add(seatService.getByid(seat.getId()));
                }
            }
            booking.setCar(car);
            booking.setUser(user);
            booking.setSeats(seats_);
            booking.setDriverTrip(driverTrip);
            booking.setDropoffLocation(detailDropOffLocation);
            booking.setPickupLocation(detailPickUpLocation);
            bookings.add(booking);
        }
        return convertList(bookingRepository.saveAll(bookings));
    }
    @Override
    public void deleteBookingSeatByBookingId(Long id) {
        bookingRepository.deleteBookingSeatByBookingId(id);
    }
    @Override
    public void deleteByid(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking getBookingById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(optionalBooking.isPresent()){
            return optionalBooking.get();
        }
        return  null;
    }
}
