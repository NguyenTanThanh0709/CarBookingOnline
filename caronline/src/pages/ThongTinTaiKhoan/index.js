import React from "react";
import { useNavigate } from 'react-router-dom';


function ThongTinTaiKhoan() {
const navigate = useNavigate();
const quaylai = () => {
    navigate('/');
}

    return ( 
        <>
            <form className="w-full bg-slate-200 m-4 p-4">
            <button onClick={quaylai} class="bg-blue-500 mb-4 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Quay Lại
            </button>
            <div>*Bổ sung đầy đủ thông tin sẽ giúp Vexere hỗ trợ bạn tốt hơn khi đặt vé</div> <br/>

                <div className="flex flex-wrap -mx-3 mb-6">
                    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-first-name">
                            Số Điện Thoại:
                        </label>
                        <input readOnly={true} className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="text" placeholder="phoneNumber" />
                    </div>
                    <div className="w-full md:w-1/2 px-3">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-last-name">
                            Email:
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" placeholder="email" />
                    </div>
                </div>
                <div className="flex flex-wrap -mx-3 mb-6">
                    <div className="w-full px-3">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-password">
                            Họ Và Tên:
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-password" type="text" />
                    </div>
                </div>
                <div className="flex flex-wrap -mx-3 mb-6">
                    <div className="w-full px-3">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-password">
                            Điểm tích lũy:
                        </label>
                        <input readOnly={true} className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-password" type="text" />
                    </div>
                </div>
                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Cập nhật
                </button>

            </form>
        </>
    );
}

export default ThongTinTaiKhoan;
