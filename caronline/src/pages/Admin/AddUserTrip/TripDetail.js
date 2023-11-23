import React from 'react';
import Button from '~/components/Button';
import config from '~/config';
function TripDetail({trip}) {
  const tripDate = new Date(trip.date);


  const formattedDate =
    `${tripDate.getMonth() + 1}/${tripDate.getDate()}/${tripDate.getFullYear()}`;

  
  return (
    <div className=" rounded overflow-hidden shadow-lg">
      <img className="w-full" src={trip.trip.urlimage} alt="Sunset in the mountains" />
      <div className="px-6 py-4">
        <span className="font-bold  mb-2">Địa Điểm Đi: </span><div >{trip.trip.pickupLocation}</div>
        <span className="font-bold  mb-2">Địa Điểm Đến: </span><div >{trip.trip.dropoffLocation}</div>
        <span className="font-bold  mb-2">Ngày Đi: </span><div >{formattedDate}</div>
        <span className="font-bold  mb-2">Xe Đi: </span><div >{trip.car.id}</div>
      </div>
      <div className="px-6 pt-4 pb-2">
      <Button to={`${config.routes.Book}?driverTrip=${trip.id}&idtrip=${trip.trip.id}`} class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
        Đặt
      </Button>
      </div>
    </div>
  );


}

export default TripDetail;
