import {React, useState, useEffect} from "react";
import { useNavigate,  } from 'react-router-dom';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import Item from "./Item";
import BookingAPI from "~/api/BookingAPI";
import Cookies from "js-cookie";

function VeCuaToi() {
    const navigate = useNavigate();
    const quaylai = () => {
        navigate('/');
    }
    const handleTabClick = (tabKey) => {
        if (tabKey === "hientai") {

        } else if (tabKey === "dadi") {

        }
        else if (tabKey === "dadahuydi") {

        }
    };

    const [ bookings,setBookings ] = useState([])

    const [ bookingsChuaDi,setBookingsChuaDi ] = useState([])
    const [ bookingsDaDi,setBookingsDaDi ] = useState([])
    const [ bookingBiHuy,setBookingsBiHuy ] = useState([])

    const fectData = (data) => {
        const filtered = data.filter(booking => {
            return (
               ( booking.status.toLowerCase() === 'Đã Thanh Toán, Chưa Đi'.toLowerCase() ||  booking.status.toLowerCase() === 'Chưa Thanh Toán'.toLowerCase())
            );
        });
        setBookingsChuaDi(filtered);
    };

    const fectData_ = (data) => {
        const filtered = data.filter(booking => {
            return (
               ( booking.status.toLowerCase() === 'Đã Thanh Toán'.toLowerCase())
            );
        });
        setBookingsDaDi(filtered);
    };

    const fectData__ = (data) => {
        const filtered = data.filter(booking => {
            return (
               ( booking.status.toLowerCase() === 'Bị Hủy'.toLowerCase())// Kiểm tra ngày
            );
        });
        setBookingsBiHuy(filtered);
    };

    const fectchbooking = async () =>{
        try {
          const data = await BookingAPI.getlistBookingByUser(Cookies.get("phone"));
          setBookings(data)
          fectData(data);
                fectData_(data);
        fectData__(data);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }
      

      useEffect(() => {
        fectchbooking();
      },[]);

      const onClick = (id) => {
        navigate(`/detailitem?id=${id}`);
    }

     console.log(bookingsChuaDi, bookingsDaDi, bookingBiHuy)

    return ( 
        <div className="w-full bg-slate-200 m-4 p-4">
            <button onClick={quaylai} class="bg-blue-500 mb-4 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Quay Lại
            </button>
            <Tabs
            id="uncontrolled-tab-example"
            className="mb-3 "
            onSelect={handleTabClick}

        >
            <Tab eventKey="hientai" title="Hiện Tại">
            {bookingsChuaDi.map((booking, index) => (
                        <div key={index} onClick={() => onClick(booking.id)} className="p-2 bg-red-100 cursor-pointer m-4">
                        <div className="m-2 p-2 flex justify-between ">
                            <p>{booking.drivertrip.date}</p>
                            <p className="text-green-900 fw-bold">{booking.status}</p>
                        </div>
                        <div className="m-2 p-2">{booking.drivertrip.pickupTime}</div>
                        <div className="m-2 p-2">{booking.fareAmount}</div>
                        <div className="m-2 p-2">{booking.drivertrip ? booking.drivertrip.pickupLocation : ''} - {booking.drivertrip ? booking.drivertrip.dropoffLocation : ''}</div>
                        <div className="m-2 p-2">Biển Số Xe: {booking.car&& booking.car.licenseplates ? booking.car.licenseplates : 'N/A'}</div>
                    </div>
                    ))}
            </Tab>
            <Tab eventKey="dadi" title="Đã Đi">
            {bookingsDaDi.map((booking, index) => (
                        <div key={index} onClick={() => onClick(booking.id)} className="p-2 bg-red-100 cursor-pointer m-4">
                        <div className="m-2 p-2 flex justify-between ">
                            <p>{booking.drivertrip.date}</p>
                            <p className="text-green-900 fw-bold">{booking.status}</p>
                        </div>
                        <div className="m-2 p-2">{booking.drivertrip.pickupTime}</div>
                        <div className="m-2 p-2">{booking.fareAmount}</div>
                        <div className="m-2 p-2">{booking.drivertrip.pickupLocation} - {booking.drivertrip.dropoffLocation}</div>
                        <div className="m-2 p-2">Biển Số Xe: {booking.car.licenseplates}</div>
                    </div>
                    ))}
            </Tab>
            <Tab eventKey="dahuy" title="Đã Hủy">
            {bookingBiHuy.map((booking, index) => (
                        <div key={index} onClick={() => onClick(booking.id)} className="p-2 bg-red-100 cursor-pointer m-4">
                        <div className="m-2 p-2 flex justify-between ">
                            <p>{booking.drivertrip.date}</p>
                            <p className="text-green-900 fw-bold">{booking.status}</p>
                        </div>
                        <div className="m-2 p-2">{booking.drivertrip.pickupTime}</div>
                        <div className="m-2 p-2">{booking.fareAmount}</div>
                        <div className="m-2 p-2">{booking.drivertrip.pickupLocation} - {booking.drivertrip.dropoffLocation}</div>
                        <div className="m-2 p-2">Biển Số Xe: {booking.car.licenseplates}</div>
                    </div>
                    ))}
            </Tab>
        </Tabs>


        </div>
     );
}

export default VeCuaToi;