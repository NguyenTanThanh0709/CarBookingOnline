import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import { useState } from 'react';
import TripCheckbox from './TripCheckbox';
import { Row, Col } from 'react-bootstrap';

const promotions = [
    {
      id: 1,
      code: 'PROMO123',
      description: '50% off on all products',
      discountAmount: '50.00',
      status: 'Active',
      startDay: '2023-10-01',
      endDay: '2023-10-31',
    },
    // Add more promotions as needed
  ];

  const tripsByPromotion = [
    {
      promotionId: 1,
      tripId: 101,
      pickupLocation: 'Location A',
      dropoffLocation: 'Location B',
      // Add trip details as needed
    },
    {
        promotionId: 2,
        tripId: 102,
        pickupLocation: 'Location A',
        dropoffLocation: 'Location B',
        // Add trip details as needed
      },
    // Add more trip details as needed
  ];


  const trips = [
    {
      id: 1,
      location: 'Location A',
      // Add more trip details as needed
    },
    {
      id: 2,
      location: 'Location B',
      // Add more trip details as needed
    },
    // Add more trips as needed
  ];
  

function Promotion() {
    const [selectedTrips, setSelectedTrips] = useState([]);

    const handleCheckboxChange = (event) => {
      const tripId = parseInt(event.target.value, 10);
      if (event.target.checked) {
        setSelectedTrips([...selectedTrips, tripId]);
      } else {
        setSelectedTrips(selectedTrips.filter((id) => id !== tripId));
      }
    };


  return (
    <Tabs
      defaultActiveKey="profile"
      id="uncontrolled-tab-example"
      className="mb-3"
    >
      <Tab eventKey="add" title="Khuyến Mãi">
        <div className="mt-6 mx-4">

            <Row>
                <Col>
                <h2 className="text-2xl font-semibold mb-4">Khuyến mãi</h2>
        <form className="w-full max-w-lg">
            <div className="mb-4">
            <label htmlFor="code" className="block text-gray-700 font-bold mb-2">
                Mã Khuyến mãi (code)
            </label>
            <input
                type="text"
                id="code"
                name="code"
                className="border rounded w-full py-2 px-3"
                placeholder="Enter promotion code"
            />
            </div>
            <div className="mb-4">
            <label htmlFor="description" className="block text-gray-700 font-bold mb-2">
                Mô tả: 
            </label>
            <textarea
                id="description"
                name="description"
                className="border rounded w-full py-2 px-3"
                placeholder="Enter promotion description"
                rows="4"
            />
            </div>
            <div className="mb-4">
            <label htmlFor="discountAmount" className="block text-gray-700 font-bold mb-2">
                Giá giảm khuyến mãi (VNĐ)
            </label>
            <input
                type="number"
                id="discountAmount"
                name="discountAmount"
                className="border rounded w-full py-2 px-3"
                placeholder="Enter discount amount"
            />
            </div>
            <div className="mb-4">
            <label htmlFor="status" className="block text-gray-700 font-bold mb-2">
                Trạng thái
            </label>
            <select
                id="status"
                name="status"
                className="border rounded w-full py-2 px-3"
            >
                <option value="active">Active</option>
                <option value="inactive">Inactive</option>
            </select>
            </div>
            <div className="mb-4">
            <label htmlFor="startDay" className="block text-gray-700 font-bold mb-2">
                Ngày Bắt Đầu
            </label>
            <input
                type="date"
                id="startDay"
                name="startDay"
                className="border rounded w-full py-2 px-3"
            />
            </div>
            <div className="mb-6">
            <label htmlFor="endDay" className="block text-gray-700 font-bold mb-2">
                Ngày Kết thúc
            </label>
            <input
                type="date"
                id="endDay"
                name="endDay"
                className="border rounded w-full py-2 px-3"
            />
            </div>





            <div className="flex justify-center">
            <button
                type="submit"
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            >
                Thêm
            </button>
            </div>
        </form>
                </Col>

                <Col>
                <div className="mb-4">
                    <label className="block text-gray-700 font-bold mb-2">Các Tuyến đường áp dụng khuyến mãi</label>
                    {trips.map((trip) => (
                    <TripCheckbox
                        key={trip.id}
                        trip={trip}
                        selectedTrips={selectedTrips}
                        handleCheckboxChange={handleCheckboxChange}
                    />
                    ))}
                </div>
                </Col>

            </Row>


        </div>

      </Tab>
      <Tab eventKey="see" title="Xem tất cả chương trình khuyến mãi">
      <div className="mt-6 mx-4">
            <h2 className="text-2xl font-semibold mb-4">See All Promotions</h2>
            <table className="min-w-full table-auto">
                <thead>
                <tr>
                    <th className="px-4 py-2">Mã</th>
                    <th className="px-4 py-2">mô tả</th>
                    <th className="px-4 py-2">Giá giảm</th>
                    <th className="px-4 py-2">Ngày bắt đầu</th>
                    <th className="px-4 py-2">Ngày kết thúc</th>
                    <th className="px-4 py-2">Danh sách tuyến</th>
                </tr>
                </thead>
                <tbody>
                {promotions.map((promotion) => (
                    <tr key={promotion.id}>
                    <td className="border px-4 py-2">{promotion.code}</td>
                    <td className="border px-4 py-2">{promotion.description}</td>
                    <td className="border px-4 py-2">{promotion.discountAmount}</td>
                    <td className="border px-4 py-2">{promotion.startDay}</td>
                    <td className="border px-4 py-2">{promotion.endDay}</td>
                    <td className="border px-4 py-2">
                        <select
                        >
                        <option value={null}>danh sách tuyến</option>

                        {tripsByPromotion
                            .map((trip) => (
                            <option key={trip.tripId} value={trip.tripId}>
                                Trip {trip.tripId}
                            </option>
                            ))}
                        </select>
                    </td>
                    </tr>
                ))}
                </tbody>
            </table>
      </div>
      </Tab>
    </Tabs>
  );
}

export default Promotion;