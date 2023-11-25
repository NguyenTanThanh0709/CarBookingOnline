
import Modal from 'react-modal';
import { Container, Row, Col } from "react-bootstrap";
import { MDBRadio } from 'mdb-react-ui-kit';
import BookingAPI from "~/api/BookingAPI";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from 'react-router-dom';
import Button from "~/components/Button";
import config from "~/config";
import Cookies from "js-cookie";
import Book from '../Admin/AddUserTrip/Book';


function DetailItem() {
    const customStyles = {
        content: {
          width: '400px',
          margin: 'auto',
          
          borderRadius: '10px',
          boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
          textAlign: 'center',
          backgroundColor: '#fff',
          height: '500px',
        },
        overlay: {
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
        },
      };
    const location = useLocation();
  const id = new URLSearchParams(location.search).get('id');
    const [booking, setBooking]  =useState ({})
    const [payment, setPayment]  =useState ({})

    useEffect(() => {
        const fetchData = async () => {
          try {
            const bookingData = await BookingAPI.getone(id);
            setBooking(bookingData);
            console.log(bookingData);
            
            const paymentData = await BookingAPI.getlistPaymentbyIdBooking(id);
            setPayment(paymentData);
            console.log(paymentData);
          } catch (error) {
            console.error('Error fetching data:', error);
            // Handle error, redirect to an error page, or show a user-friendly message
          }
        };
      
        fetchData();
      }, []);
      
      const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
      const openDeleteModal = () => {
        setIsDeleteModalOpen(true);
      };
    
      const [iddelete, setIddelete] = useState(null);
      const handleDelete = (id) => {
        openDeleteModal();
      };
    
      
      
      
      const closeDeleteModal = () => {
        setIsDeleteModalOpen(false);
      };


      

      const handleSubmit = async (event) => {
        event.preventDefault(); // Prevents the default form submission behavior
    
        // Log form data to the console
        console.log({
          bookingid: event.target.elements.bookingid.value,
          orderId: event.target.elements.orderId.value,
          amountt: event.target.elements.amountt.value,
          transDate: event.target.elements.transDate.value,
          user: event.target.elements.user.value,
        });

        try {
          const bookingData = await BookingAPI.refund(event.target.elements.bookingid.value,  event.target.elements.orderId.value, event.target.elements.amountt.value, event.target.elements.transDate.value, event.target.elements.user.value);
          console.log(bookingData);
          if(bookingData == "succsess"){
            alert("Hoàn tiền thành công");
          }else if(bookingData == "wait"){
            alert("Vé Hoàn Tiền Của bạn đang được xem xét vui lòng chờ! khi có phản hồi");
          }else{
            alert("Hoàn tiền thất bại");
          }
        } catch (error) {
          console.error('Error fetching data:', error);
          // Handle error, redirect to an error page, or show a user-friendly message
        }


    
        // You can add your logic for further processing or API calls here
      };


    return ( 
        <Container fluid={false} className="bg-gray-200">
                                <Button  to={config.routes.VeCuaToi} class="bg-blue-500 m-4 mt-4 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded">
                        Quay Lại
                    </Button>
            <Row className="m-2 p-2 fs-3">

            </Row>
            {booking  && 
            
            <Row>
                <Col>
                    <h3>Thông tin</h3>
                    <div className="border-2 border-blue-500 border-opacity-75 md:border-opacity-50 m-2 p-2">
                            <div className="m-2 p-2 border-bottom  border-danger mt-2">
                                <h1>Thanh toán thành công   </h1>
                                <span>Chúng tôi đã gửi thông tin chuyến đi đến email {Cookies.get("email")}, bạn hãy kiểm tra thật kĩ nhé!</span>
                            </div>

                            <div className="m-2 p-2 border-bottom  border-danger mt-2">
                                <h1>TThông tin vé</h1>
                                <div>Biển số xe: {booking.car&& booking.car.licenseplates ? booking.car.licenseplates : 'N/A'}</div>
                                <div>
                                Lưu ý: Mọi thông tin về chuyến đi (bao gồm biển số xe, số điện thoại tài xế,...) sẽ được Vexere thông báo đến quý khách qua email ngay sau khi nhà xe cập nhật thông tin. Thông thường nhà xe sẽ cập nhật thông tin này trễ nhất 15-30 phút trước giờ khởi hành tùy thuộc vào kế hoạch của nhà xe.
Nếu gặp vấn đề khi ra xe, quý khách vui lòng liên hệ theo số Hotline nhà xe.
                                </div>
                            </div>

                            <div className="m-2 p-2 border-bottom  border-danger mt-2">
                            Bạn cần ra điểm đón trước 15 phút, đưa email xác nhận thanh toán của Vexere cho nhân viên văn phòng vé để đổi chứng từ giấy

Khi lên xe, bạn xuất trình chứng từ giấy cho tài xế hoặc phụ xe.
                            </div>
                            
                    </div>
                    <div>
                    {
                      booking.status !== 'Bị Hủy' ? (
                        <button onClick={() => handleDelete()} className="bg-red-500 m-4 mt-4 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded">
                          Hủy chuyến
                        </button>
                      ) : null
                    }
                        

                    </div>
                </Col>
                <Col>

                    <h3>Thông tin chuyến đi</h3>
                    <div className="border-2 border-blue-500 border-opacity-75 md:border-opacity-50 m-2 p-2">

                    <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Mã đơn hàng </span>
                            <p>{booking.id}</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Họ Tên</span>
                            <p>{booking.user && booking.user.name ? booking.user.name : 'N/A'}</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Số Điện Thoại</span>
                            <p>{booking.user && booking.user.name ? booking.user.phone : 'N/A'}</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Email</span>
                            <p>{booking.user && booking.user.name ? booking.user.email : 'N/A'}</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">CCCD/CMND</span>
                            <p></p>
                        </div>

                        <div className="m-2 p-2 border-bottom  border-danger "></div>


                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Tuyến đường</span>
                            <p>{booking.drivertrip && booking.drivertrip.pickupLocation && booking.drivertrip.dropoffLocation
    ? `${booking.drivertrip.pickupLocation} - ${booking.drivertrip.dropoffLocation}`
    : 'N/A'}</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>
                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Loại xe</span>
                            <p>{booking.car&& booking.car.name ? booking.car.name : 'N/A'}</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>
                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Chỗ ngồi</span>
                            <p>
                            {booking.seats&& booking.seats.length > 0 ? (
  <ul>
    {booking.seats.map((seat, index) => (
      <li key={index}>{`${seat.id} - ${seat.name}`}</li>
    ))}
  </ul>
) : (
  <p>No driver trips available</p>
)}
                            </p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Dự kiến đón</span>
                            <p>{booking.locationDetailPickUp? booking.locationDetailPickUp.detailLocation: 'N/A'} - 
{booking.locationDetailPickUp? booking.locationDetailPickUp.time: 'N/A'}
</p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Dự kiến trả</span>
                            <p>
                            {booking.locationDetailDropOff  ? booking.locationDetailDropOff .detailLocation: 'N/A'} - 
{booking.locationDetailDropOff  ? booking.locationDetailDropOff .time: 'N/A'}

                            </p>
                        </div>

                    </div>

                    <div>
                    <Row className="m-2 p-2">
                        <Col className="">
                            <div className="flex justify-content-between">
                                <span>Tổng Tiền</span>
                                <span>{booking.fareAmount ? booking.fareAmount : 'N/A'}<span> Đ</span></span>
                            </div>
                            <div className="flex justify-content-between">
                                <span>Trạng thái</span>
                                <span>{booking.status ? booking.status : 'N/A'}</span>
                            </div>
                        </Col>
                    </Row>
                    </div>
                </Col>
                
            </Row>
            }


<Modal
          isOpen={isDeleteModalOpen}
          onRequestClose={closeDeleteModal}
          style={customStyles}
          contentLabel="Delete Confirmation Modal"
          // Thêm các kiểu và các thuộc tính khác theo mong muốn
        >
            <h2>Xác nhận xóa?</h2>
            <div>
                <form onSubmit={handleSubmit} >
                <div className="mb-4">
                    <label htmlFor="bookingid" className="block text-4xl m-4 font-medium text-gray-600">
                    Mã Booking
                    </label>
                    <input
                    type="number"
                    className="form-input border-2 border-indigo-600 text-2xl m-4  block w-full"
                    id="bookingid"
                    name="bookingid"
                    required    
                    value={booking.id ? booking.id : '0'}
                    />
                </div>
                <div className="mb-4">
                    <label htmlFor="orderId" className="block text-4xl m-4 font-medium text-gray-600">
                    Thông tin đơn hàng: 
                    </label>
                    <input
                    type="text"
                    className="form-input border-2 border-indigo-600 text-2xl m-4 block w-full"
                    id="orderId"
                    name="orderId"
                    required
                    value={payment.orderId ? payment.orderId : 'N/A'}
                    />
                </div>
                <div className="mb-4">
                    <label htmlFor="amountt" className="block text-4xl m-4 font-medium text-gray-600">
                    Số Tiền:
                    </label>
                    <input
                    type="number"
                    className="form-input border-2 border-indigo-600 text-2xl m-4 block w-full"
                    id="amountt"
                    name="amountt"
                    required
                    value={payment.amountt ? payment.amountt : 'N/A'} 

                    />
                </div>
                <div className="mb-4">
                    <label htmlFor="transDate" className="block text-4xl m-4 font-medium text-gray-600">
                    Ngày Thanh Toán:
                    </label>
                    <input
                    type="text"
                    className="form-input border-2 border-indigo-600 text-2xl m-4 block w-full"
                    id="transDate"
                    name="transDate"
                    required
                    value={payment.transDate ? payment.transDate : 'N/A'}

                    />
                </div>

                <div className="mb-4">
                    <label htmlFor="user" className="block text-4xl m-4 font-medium text-gray-600">
                    Số điện thoại:
                    </label>
                    <input
                    type="text"
                    className="form-input border-2 border-indigo-600 text-2xl m-4 block w-full"
                    id="user"
                    name="user"
                    required
                    value={booking.user ? booking.user.phone : 'N/A'}

                    />
                </div>

                <p>Số Tiền bạn được hoàn bằng 70% số tiền thanh toán</p>

                <button type="submit" className="bg-blue-500 m-4  hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Hoàn Tiền
                </button>
                </form>
            </div>
            <div>
              <button onClick={closeDeleteModal} className="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg  px-5 py-2.5 text-center mr-2 mb-2">Cancel</button>
            </div>
      </Modal>


        </Container>
     );
}

export default DetailItem;