import { useState, useEffect } from 'react';
import BookingAPI from '~/api/BookingAPI';

function ModalDetailBooking(props) {
    const [listBooking, setListBooking] = useState([]);
    const oneDriverTrip = props.data
    
    
    const fecthDataBooking = async (id) => {
        try {
            const data = await BookingAPI.getlist(id);
            setListBooking(data)
            console.log(data)
          } catch (error) {
            console.error('Error fetching data:', error);
          }
    }
    useEffect(() => {
        fecthDataBooking(oneDriverTrip.id);
      },[]);
    return ( 
        <div>
            <div className="p-4 bg-white rounded-lg shadow-md">
                <h1 className="text-4xl font-semibold text-green-900">Thông tin chi tiết chuyến đi</h1>
                <div className="flex my-2">
                <p className="w-1/4">Mã chuyến đi:</p>
                <p>{oneDriverTrip.id}</p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Chuyến đi từ:</p>
                <p>
                    {oneDriverTrip.trip.pickupLocation} Đến{" "}
                    {oneDriverTrip.trip.dropoffLocation}
                </p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Ngày đi:</p>
                <p>{oneDriverTrip.date}</p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Giờ đi:</p>
                <p>{oneDriverTrip.trip.pickupTime}</p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Giờ dự kiến đến:</p>
                <p>{oneDriverTrip.trip.dropoffTime}</p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Được đi bởi xe:</p>
                <p>
                    {oneDriverTrip.car.name} với biển số xe {oneDriverTrip.car.licenseplates}
                </p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Giá tiền cho một vé là:</p>
                <p>{oneDriverTrip.trip.price}</p>
                </div>
                <div className="flex my-2">
                <p className="w-1/4">Tổng tiền thu được:</p>
                <p>1000000</p>
                </div>
            </div>
            <div className="p-4 bg-white rounded-lg shadow-md my-4">
                <h1 className="text-4xl font-semibold text-green-900 my-2">Danh sách vé đã đặt</h1>
                <div className="flex my-2">
                <table className="min-w-full">
                    <thead>
                    <tr>
                        <th className="px-4 py-2">ID</th>
                        <th className="px-4 py-2">số điện thoại</th>
                        <th className="px-4 py-2">Giá</th>
                        <th className="px-4 py-2">isProtect</th>
                        <th className="px-4 py-2">Điểm đón</th>
                        <th className="px-4 py-2">Điểm trả</th>
                        <th className="px-4 py-2">Danh sách chỗ</th>
                    </tr>
                    </thead>
                    <tbody>
                    {listBooking.map((item) => (
                        <tr>
                            <td className="border px-4 py-2">{item.id}</td>
                            <td className="border px-4 py-2">{item.user.phone}</td>
                            <td className="border px-4 py-2">{item.fareAmount}</td>
                            <td className="border px-4 py-2">{item.isProtect ? 'Có' : 'Không'}</td>
                            <td className="border px-4 py-2">
                                
                        {item.locationDetailPickUp && (
                             <>{item.locationDetailPickUp.detailLocation}, {item.locationDetailPickUp.time}</>
                        )}

                            </td>
                            <td className="border px-4 py-2">
                            {item.locationDetailDropOff && (
                             <>{item.locationDetailDropOff.detailLocation}, {item.locationDetailDropOff.time}</>
                        )}
                            </td>
                            <td className="border px-4 py-2">{item.seats.map(item1 => item1.name).join(', ')}</td>

                        </tr>


                         ))}
                    </tbody>
                </table>
                </div>
            </div>
        </div>
     );
}

export default ModalDetailBooking;