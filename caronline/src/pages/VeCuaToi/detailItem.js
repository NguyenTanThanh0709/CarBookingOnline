import { Container, Row, Col } from "react-bootstrap";
import { MDBRadio } from 'mdb-react-ui-kit';



function detailItem() {


    return ( 
        <Container fluid={false} className="bg-gray-200">
            <Row className="m-2 p-2 fs-3">
            <div class="flex items-center bg-blue-500 text-white font-bold px-4 py-3" role="alert">
                <svg class="fill-current w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M12.432 0c1.34 0 2.01.912 2.01 1.957 0 1.305-1.164 2.512-2.679 2.512-1.269 0-2.009-.75-1.974-1.99C9.789 1.436 10.67 0 12.432 0zM8.309 20c-1.058 0-1.833-.652-1.093-3.524l1.214-5.092c.211-.814.246-1.141 0-1.141-.317 0-1.689.562-2.502 1.117l-.528-.88c2.572-2.186 5.531-3.467 6.801-3.467 1.057 0 1.233 1.273.705 3.23l-1.391 5.352c-.246.945-.141 1.271.106 1.271.317 0 1.357-.392 2.379-1.207l.6.814C12.098 19.02 9.365 20 8.309 20z"/></svg>
                <p>Phương thức thanh toán đa dạng, bảo mật.</p>
            </div>
            </Row>
            <Row>
                <Col>
                    <h3>Thông tin</h3>
                    <div className="border-2 border-blue-500 border-opacity-75 md:border-opacity-50 m-2 p-2">
                            <div className="m-2 p-2 border-bottom  border-danger mt-2">
                                <h1>Thanh toán thành công   </h1>
                                <span>Chúng tôi đã gửi thông tin chuyến đi đến email nguyentanthanh0709it@gmail.com, bạn hãy kiểm tra thật kĩ nhé!</span>
                            </div>

                            <div className="m-2 p-2 border-bottom  border-danger mt-2">
                                <h1>TThông tin vé</h1>
                                <div>Biển số xe: </div>
                                <div>Thông tin tài xế: </div>
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
                    <button  class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded">
                        Thanh Toán Bảo Mật
                    </button>
                    </div>
                </Col>
                <Col>

                    <h3>Thông tin chuyến đi</h3>
                    <div className="border-2 border-blue-500 border-opacity-75 md:border-opacity-50 m-2 p-2">

                    <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Mã đơn hàng</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Họ Tên</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Số Điện Thoại</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Email</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">CCCD/CMND</span>
                            <p></p>
                        </div>

                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Nhà Xe</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Tuyến đường</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>
                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Loại xe</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>
                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Chỗ ngồi</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Dự kiến đón</span>
                            <p></p>
                        </div>
                        <div className="m-2 p-2 border-bottom  border-danger "></div>

                        <div className="m-2 p-2 ">
                            <span className="text-purple-700 text-opacity-50 fs-4">Dự kiến trả</span>
                            <p></p>
                        </div>

                    </div>

                    <div>
                    <Row className="m-2 p-2">
                        <Col className="">
                            <div className="flex justify-content-between">
                                <span>Giá vé</span>
                                <span>400.000 <span> Đ</span></span>
                            </div>
                            <div className="flex justify-content-between">
                                <span>Tổng Tiền</span>
                                <span>400.000 <span> Đ</span></span>
                            </div>
                            <div className="flex justify-content-between">
                                <span>Trạng thái</span>
                                <span></span>
                            </div>
                        </Col>
                    </Row>
                    </div>
                </Col>
                
            </Row>
            
        </Container>
     );
}

export default detailItem;