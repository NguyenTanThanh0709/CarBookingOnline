import React from "react";
import { useNavigate } from 'react-router-dom';

function DoiMatKhau() {
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
            <div className="flex flex-wrap -mx-3 mb-6">
                    <div className="w-full px-3">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-password">
                            Mật Khẩu Hiện Tại:
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-password" type="password"  placeholder="********"/>
                    </div>
                </div>
                <div className="flex flex-wrap -mx-3 mb-6">
                    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-first-name">
                            Mật Khẩu mới:
                        </label>
                        <input  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="password" placeholder="********" />
                    </div>
                    <div className="w-full md:w-1/2 px-3">
                        <label className="block uppercase tracking-wide text-gray-700 font-bold mb-2" htmlFor="grid-last-name">
                            Xác Nhận Mật Khẩu mới:
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="password" placeholder="**********" />
                    </div>
                </div>
                
                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Xác Nhận
                </button>

            </form>
        </>
    );
}

export default DoiMatKhau;
