function Item() {
    return ( 
        <div className="p-2 bg-red-100 cursor-pointer m-4">
            <div className="m-2 p-2 flex justify-between ">
                <p>CN,27/08/2023</p>
                <p className="text-green-900 fw-bold">Đã thanh toán</p>
            </div>
            <div className="m-2 p-2">17:00</div>
            <div className="m-2 p-2">Loan Sáng</div>
            <div className="m-2 p-2">Đắk Lak - Hồ Chí Minh</div>
            <div className="m-2 p-2">Biển Số Xe: AB123</div>
        </div>
     );
}

export default Item;