import { useNavigate } from 'react-router-dom';

function Item({ booking }) {
    const navigate = useNavigate();

    const onClick = (id) => {
        navigate(`/detailitem?id=${id}`);
    }

    console.log(booking)

    return ( 
        <div onClick={() => onClick(booking.id)} className="p-2 bg-red-100 cursor-pointer m-4">
            <div className="m-2 p-2 flex justify-between ">
                <p>${booking.drivertrip.date}</p>
                <p className="text-green-900 fw-bold">${booking.status}</p>
            </div>
            <div className="m-2 p-2">${booking.drivertrip.pickupTime}</div>
            <div className="m-2 p-2">${booking.fareAmount}</div>
            <div className="m-2 p-2">${booking.drivertrip.pickupLocation} - ${booking.drivertrip.dropoffLocation}</div>
            <div className="m-2 p-2">Biển Số Xe: ${booking.car.id}</div>
        </div>
    );
}

export default Item;
