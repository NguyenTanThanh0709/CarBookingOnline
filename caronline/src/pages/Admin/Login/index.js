import React, { useState } from 'react';
import AuthenAndAuthorAPI from '~/api/AuthenAndAuthorAPI';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import CompanyAPI from '~/api/CompanyAPI';

function Login() {
    const navigate = useNavigate();
    const [formLogin,setFormLogin] = useState({
        phone:'',
        password :'',
      });
      const handleInputChangeLogin = (e) => {
        const { name, value } = e.target;
        setFormLogin({
          ...formLogin,
          [name]: value,
        });
      };
      const handleSubmit_ = (e) => {
        e.preventDefault();
        AuthenAndAuthorAPI.authenticate(formLogin)
        .then((response) => {
            console.log(response);
            Cookies.set('access_token', response.access_token, { expires: 7});
            Cookies.set('refresh_token', response.refresh_token, { expires: 7 });
            Cookies.set('role', response.role, { expires: 7 });
            Cookies.set('phone', response.phone, { expires: 7 });
            Cookies.set('email', response.email, { expires: 7 });
        
        CompanyAPI.getCompanyByStaff(formLogin.phone)    
        .then((response) => {
            console.log(response)
            const userString = JSON.stringify(response);
            Cookies.set('company', userString, { expires: 7 });
        })
        .catch((error) => {
            
        });
        navigate('/managementstaff/addtriplist');
        // You can handle the response here, e.g., redirect or display a success message.
        })
        .catch((error) => {
            
        });
        
      }
    return ( 
        <div className=" relative flex flex-col justify-center min-h-screen overflow-hidden">
            <div className="w-full p-6 m-auto bg-white rounded-md shadow-md lg:max-w-xl">
                
                <h1 className="fs-1 text-3xl font-semibold text-center text-purple-700 underline">
                   Trang Đăng Nhập Nhà Xe
                </h1>
                <form className="mt-6" onSubmit={handleSubmit_} method="POST">
                    <div className="mb-2">
                        <label
                            for="phone"
                            className="block  font-semibold text-gray-800"
                        >
                            Số điện thoại
                        </label>
                        <input
                            id="phone"
                            name="phone"
                            type="tel"
                            value={formLogin.phone}
                            onChange={handleInputChangeLogin}
                            required
                            className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                        />
                    </div>
                    <div className="mb-2">
                        <label
                            for="password"
                            className="blockfont-semibold text-gray-800"
                        >
                            Mật khẩu
                        </label>
                        <input
                            id="password"
                            name="password"
                            type="password"
                            value={formLogin.password}
                            onChange={handleInputChangeLogin}
                            required
                            className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                        />
                    </div>
                    <a
                        href="#"
                        className=" text-purple-600 hover:underline"
                    >
                        Forget Password?
                    </a>
                    <div className="mt-6">
                    <button type="submit" className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5  font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                        Sign in
                        </button>
                    </div>
                </form>

                <p className="mt-8 text-xs font-light text-center text-gray-700">
                    {" "}
                    Don't have an account?{" "}
        
                </p>
            </div>
        </div>
     );
}

export default Login;